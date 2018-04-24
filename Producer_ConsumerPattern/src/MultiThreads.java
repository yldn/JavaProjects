
public class MultiThreads {
	private int balance = 0;

	public static void main(String[] args) {
		MultiThreads g = new MultiThreads();
		g.goingThroughLife();
	}

	public void goingThroughLife() {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("t1 start");
				for (int i = 0; i < 100; i++) {
					add();
					System.out.println("add " + i + " = " + balance);
				}
				System.out.println("t1 end");
			}

		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("t2 start");
				for (int i = 0; i < 100; i++) {

					try {
						t1.join();
					} catch (Exception e) {
					}
					sub();
					System.out.println("sub " + i + " = " + balance);
				}
				System.out.println("t2 end");
			}

		});

		t1.start();
		// try {
		// t1.join();
		// } catch (InterruptedException e1) {
		// e1.printStackTrace();
		// }
		t2.start();

//		try {
//			// t1.join();
//			t2.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		// System.out.println(balance);

	}

	synchronized protected void sub() {
		balance--;
	}

	synchronized protected void add() {
		balance++;
	}
}
