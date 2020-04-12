using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Collections;
using System.Net;
using System.Net.Sockets;
using System.Threading;

namespace MySocketServer
{
    public partial class FormServer : Form
    {
        public FormServer()
        {
            InitializeComponent();
        }
        //保存与客户相关的信息列表
        ArrayList friends = new ArrayList();
        //负责监听的套接字
        TcpListener listener;
        //只是是否启动了监听
        bool IsStart = false;
        //对控件进行调用委托类型和委托方法
        //在列表中写字符串
        delegate void AppendDelegate(string str);
        AppendDelegate AppendString;
        //在建立列表时，向下拉列表中添加客户信息
        delegate void AddDelegate(MyFriend frd);
        AddDelegate Addfriend;
        //在断开连接时，从下拉列表中删除客户信息
        delegate void RemoveDelegate(MyFriend frd);
        RemoveDelegate Removefriend;
        //在列表中写字符串的委托方法
        private void AppendMethod(string str)
        {
            listBoxStatu.Items.Add(str);
            listBoxStatu.SelectedIndex = listBoxStatu.Items.Count - 1;
            listBoxStatu.ClearSelected();
        }
        //向下拉列表中添加信息的委托方法
        private void AddMethod(MyFriend frd)
        {
            lock (friends)
            {
                friends.Add(frd);
            }
            comboBoxClient.Items.Add(frd.socket.RemoteEndPoint.ToString());
        }
        //从下拉列表中删除信息的委托方法
        private void RemoveMethod(MyFriend frd)
        {
            int i = friends.IndexOf(frd);
            comboBoxClient.Items.RemoveAt(i);
            lock (friends)
            {
                friends.Remove(frd);
            }
            frd.Dispose();
        }

        private void FormServer_Load(object sender, EventArgs e)
        {
            //实例化委托对象，与委托方法关联
            AppendString = new AppendDelegate(AppendMethod);
            Addfriend = new AddDelegate(AddMethod);
            Removefriend = new RemoveDelegate(RemoveMethod);
            //获取本机IPv4地址
            List<string> listIP = getIP();
            if (listIP.Count == 0)
            {
                this.comboBoxIP.Items.Clear();
                this.comboBoxIP.Text = "未能获取IP！";
            }
            else if (listIP.Count == 1)
            {
                this.comboBoxIP.Items.Add(listIP[0]);
                this.comboBoxIP.SelectedIndex = 0;
            }
            else
            {
                foreach (string str in listIP)
                {
                    this.comboBoxIP.Items.Add(str);
                }
                this.comboBoxIP.Text = "请选择IP！";
            }
            //设置默认端口号
            textBoxServerPort.Text = "4567";
        }

        private void buttonStart_Click(object sender, EventArgs e)
        {
            //服务器已在其中监听，则返回
            if (IsStart)
                return;
            //服务器启动侦听
            IPEndPoint localep = new IPEndPoint(IPAddress.Parse(comboBoxIP.Text), int.Parse(textBoxServerPort.Text));
            listener = new TcpListener(localep);
            listener.Start(10);
            IsStart = true;
            listBoxStatu.Invoke(AppendString, string.Format("服务器已经启动监听！端点为：{0}。", listener.LocalEndpoint.ToString()));
            //接受连接请求的异步调用
            AsyncCallback callback = new AsyncCallback(AcceptCallBack);
            listener.BeginAcceptSocket(callback, listener);
            this.buttonStart.Enabled = false;
        }
        private void AcceptCallBack(IAsyncResult ar)
        {
            try
            {
                //完成异步接收连接请求的异步调用
                //将连接信息添加到列表和下拉列表中
                Socket handle = listener.EndAcceptSocket(ar);
                MyFriend frd = new MyFriend(handle);
                comboBoxClient.Invoke(Addfriend, frd);
                AsyncCallback callback;
                //继续调用异步方法接收连接请求
                if (IsStart)
                {
                    callback = new AsyncCallback(AcceptCallBack);
                    listener.BeginAcceptSocket(callback, listener);
                }
                //开始在连接上进行异步的数据接收
                frd.ClearBuffer();
                callback = new AsyncCallback(ReceiveCallback);
                frd.socket.BeginReceive(frd.Rcvbuffer, 0, frd.Rcvbuffer.Length, SocketFlags.None, callback, frd);
            }
            catch
            {
                //在调用EndAcceptSocket方法时可能引发异常
                //套接字Listener被关闭，则设置为未启动侦听状态
                IsStart = false;
            }
        }
        private void ReceiveCallback(IAsyncResult ar)
        {
            MyFriend frd = (MyFriend)ar.AsyncState;
            try
            {
                int i = frd.socket.EndReceive(ar);
                if (i == 0)
                {
                    comboBoxClient.Invoke(Removefriend, frd);
                    return;
                }
                else
                {
                    string data = Encoding.UTF8.GetString(frd.Rcvbuffer, 0, i);
                    data = string.Format("From[{0}]:{1}", frd.socket.RemoteEndPoint.ToString(), data);
                    listBoxStatu.Invoke(AppendString, data);
                    frd.ClearBuffer();
                    AsyncCallback callback = new AsyncCallback(ReceiveCallback);
                    frd.socket.BeginReceive(frd.Rcvbuffer, 0, frd.Rcvbuffer.Length, SocketFlags.None, callback, frd);
                }
            }
            catch
            {
                comboBoxClient.Invoke(Removefriend, frd);
            }
        }

        private void SendData(MyFriend frd, string data)
        {
            try
            {
                byte[] msg = Encoding.UTF8.GetBytes(data);
                AsyncCallback callback = new AsyncCallback(SendCallback);
                frd.socket.BeginSend(msg, 0, msg.Length, SocketFlags.None, callback, frd);
                data = string.Format("To[{0}]:{1}", frd.socket.RemoteEndPoint.ToString(), data);
                listBoxStatu.Invoke(AppendString, data);
            }
            catch
            {
                comboBoxClient.Invoke(Removefriend, frd);
            }
        }
        private void SendCallback(IAsyncResult ar)
        {
            MyFriend frd = (MyFriend)ar.AsyncState;
            try
            {
                frd.socket.EndSend(ar);
            }
            catch
            {
                comboBoxClient.Invoke(Removefriend, frd);
            }
        }

        private void buttonSendMessage_Click(object sender, EventArgs e)
        {
            if (textBoxMessage.Text.Trim() == "")
            {
                listBoxStatu.Items.Add("不能发送空字符串！");
                textBoxMessage.Focus();
                return;
            }
            if (comboBoxClient.SelectedIndex < 0)
            {
                listBoxStatu.Items.Add("请在列表中选择发送对象！");
                return;
            }
            int i = comboBoxClient.SelectedIndex;
            SendData((MyFriend)friends[i], textBoxMessage.Text.Trim());
        }

        private void buttonStop_Click(object sender, EventArgs e)
        {
            if (!IsStart)
                return;
            listener.Stop();
            IsStart = false;
            listBoxStatu.Invoke(AppendString, "已经结束了服务器的侦听！");
            this.buttonStart.Enabled = true;
        }

        private void buttonClear_Click(object sender, EventArgs e)
        {
            this.listBoxStatu.Items.Clear();
        }

        //获取本机IPv4地址
        public List<string> getIP()
        {
            List<string> listIP = new List<string>();
            try
            {
                string HostName = Dns.GetHostName(); //得到主机名
                IPHostEntry IpEntry = Dns.GetHostEntry(HostName);
                for (int i = 0; i < IpEntry.AddressList.Length; i++)
                {
                    //从IP地址列表中筛选出IPv4类型的IP地址
                    //AddressFamily.InterNetwork表示此IP为IPv4,
                    //AddressFamily.InterNetworkV6表示此地址为IPv6类型
                    if (IpEntry.AddressList[i].AddressFamily == AddressFamily.InterNetwork)
                    {
                        listIP.Add(IpEntry.AddressList[i].ToString());
                    }
                }
                return listIP;
            }
            catch (Exception ex)
            {
                MessageBox.Show("获取本机IP出错:" + ex.Message);
                listIP.Clear();
                return listIP;
            }
        }
    }
}
