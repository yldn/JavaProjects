
public class Blubb {
	boolean flag = false;
	public synchronized void first() {
		if(flag){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("i'm first");
		flag = true;
		notify();
	}

	public synchronized void second() {
		if(!flag){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("i'm second");
		flag = false;
		notify();
		
	}

}
