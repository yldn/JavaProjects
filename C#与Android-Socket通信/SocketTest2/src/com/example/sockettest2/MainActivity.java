package com.example.sockettest2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textReceive = null;
	private EditText textSend = null;
	private Button btnConnect = null;
	private Button btnSend = null;
	private static final String ServerIP = "192.168.1.100";
	private static final int ServerPort = 4567;
	private Socket socket = null;
	private String strMessage;
	private boolean isConnect = false;
	private OutputStream outStream;
	private Handler myHandler = null;
	private ReceiveThread receiveThread = null;
	private boolean isReceive = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textReceive = (TextView)findViewById(R.id.textViewReceive);
		textSend = (EditText)findViewById(R.id.editTextSend);
		btnConnect = (Button)findViewById(R.id.buttonConnect);
		btnSend = (Button)findViewById(R.id.buttonSend);		

		//���Ӱ�ť�ļ�����
		btnConnect.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!isConnect){
					new Thread(connectThread).start();
				}
			}
		});

		//���Ͱ�ť�ļ�����
		btnSend.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				strMessage = textSend.getText().toString();
				new Thread(sendThread).start();
			}
		});
		myHandler = new Handler(){
			@Override
			public void handleMessage(Message msg){
				textReceive.append((msg.obj).toString());
			}
		};
	}
	//���ӵ��������Ľӿ�
	Runnable connectThread = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				//��ʼ��Scoket�����ӵ�������
				socket = new Socket(ServerIP, ServerPort);
				isConnect = true;
				//���������߳�
				isReceive = true;
				receiveThread = new ReceiveThread(socket);
				receiveThread.start();
				System.out.println("----connected success----");
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("UnknownHostException-->" + e.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("IOException" + e.toString());
			}
		}
	};
	//������Ϣ�Ľӿ�
	Runnable sendThread = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			byte[] sendBuffer = null;
			try {
				sendBuffer = strMessage.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				outStream = socket.getOutputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				outStream.write(sendBuffer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	//�����߳�
	private class ReceiveThread extends Thread{
		private InputStream inStream = null;
		
		private byte[] buffer;
		private String str = null;
		
		ReceiveThread(Socket socket){
			try {
				inStream = socket.getInputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		@Override
		public void run(){
			while(isReceive){
				buffer = new byte[512];
				try {
					inStream.read(buffer);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					str = new String(buffer,"UTF-8").trim();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Message msg = new Message();
				msg.obj = str;
				myHandler.sendMessage(msg);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		if(receiveThread != null){
			isReceive = false;
			receiveThread.interrupt();
		}
	}

}
