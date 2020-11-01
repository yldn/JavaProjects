import java.io.*;

public class StreamTutorial {

    //console raad char
    static class BRread{
        public static void main(String[] args) throws IOException {
            char c = '\0';
            BufferedReader br = new BufferedReader( new InputStreamReader(System.in));

            System.out.println("print q to stop ");

            while ( c !='q'){
                c = (char) br.read();
                System.out.println(c);
            }

        }
    }

    //console read line
    static class BRreadLines {
        public static void main(String[] args) throws IOException{
            // 使用 System.in 创建 BufferedReader
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String str;
            System.out.println("Enter lines of text.");
            System.out.println("Enter 'end' to quit.");
            do {
                str = br.readLine();
                System.out.println(str);
            } while (!str.equals("end"));


        }

    }

    //IO to file
    static class FileStreamTest {

        static void test1() throws Exception{
            File f = new File(".\\test1.txt");
            OutputStream os = new FileOutputStream(f);

            byte bWrite[] = {11, 21, 3, 40, 5};
            for (int i = 0; i < bWrite.length ; i++) {
                os.write(bWrite[i]);
            }
            os.close();


            InputStream is = new FileInputStream(f);
            int size = is.available();

            for (int i = 0 ; i < size ; i++){
                System.out.println((char) is.read() + " ");
            }
            is.close();
        }

        static void test2() throws Exception{
            File f = new File(".\\test2.txt");
            FileOutputStream fop = new FileOutputStream(f);
            OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");



            writer.append("中文输入");
            writer.append("\r\n");
            writer.append("English");
            writer.close();
            fop.close();


            FileInputStream fip = new FileInputStream(f);
            InputStreamReader reader = new InputStreamReader(fip, "UTF-8");

            StringBuffer sb = new StringBuffer();
            while (reader.ready()) {
                sb.append((char) reader.read());
            }
            System.out.println(sb.toString());
            reader.close();
            fip.close();

        }


        static void createDir () {
            String dirname = ".\\tmp\\user\\java\\bin";
            File d = new File(dirname);
            d.mkdirs();
        }

        static void readDir(){

            String dirname = "./tmp";
            File f1 = new File(dirname);
            if (f1.isDirectory()) {
                System.out.println("目录 " + dirname);
                String s[] = f1.list();
                for (int i = 0; i < s.length; i++) {
                    File f = new File(dirname + "/" + s[i]);
                    if (f.isDirectory()) {
                        System.out.println(s[i] + " 是一个目录");
                    } else {
                        System.out.println(s[i] + " 是一个文件");
                    }
                }
            } else {
                System.out.println(dirname + " 不是一个目录");
            }
        }

        public static void deleteDir(File folder) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.isDirectory()) {
                        deleteDir(f);
                    } else {
                        f.delete();
                    }
                }
            }
            folder.delete();
        }



        public static void main(String[] args) throws Exception{
            createDir();
            readDir();


        }


    }





}
