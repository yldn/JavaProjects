
public class Customthread extends Thread {
	String name ;
	
	Blubb blubb;
	boolean flag;
	public Customthread(String name, Blubb blubb, boolean flag) {
		super();
		this.name = name;
		this.blubb = blubb;
		this.flag = flag;
	}
	public void run (){
		System.out.println("Start Thread: " + name);
		for (int i = 0; i < 5; i++) {
			if (flag)
				blubb.first();
			else
				blubb.second();
		}
		System.out.println("Thread end "+ name );
		
	}
}
