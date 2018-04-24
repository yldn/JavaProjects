
public class Cup {
	
	private int contents;
	private boolean avaliable = false;

	synchronized public int get() {
		while (!avaliable) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		avaliable = false;
		notifyAll();
		return this.contents;
	}

	synchronized public void put(int value) {
		while (avaliable) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		avaliable = true;
		contents = value;
		notifyAll();
	}
	
	
}
