
public class Producer implements Runnable {

	private Cup cup;
	private int number;

	public Producer(Cup c, int number) {
		cup = c;
		this.number = number;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			cup.put(i);
			System.out.println("Producer #" + this.number + " put: " + i);
			try {
//				Thread.sleep((long) (100 * Math.random()));
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
