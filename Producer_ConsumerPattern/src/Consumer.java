
public class Consumer implements Runnable {

	private Cup cup;
	private int number;

	public Consumer(Cup c, int number) {
		cup = c;
		this.number = number;
	}

	@Override
	public void run() {
		int value = 0;

		for (int i = 0; i < 10; i++) {

			try {
//				Thread.sleep((long) (200 * Math.random()));
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			value = cup.get();
			System.out.println("Consumer #" + this.number + " get: " + value);

		}
	}

}
