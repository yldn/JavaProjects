
public class Main {
	public static void main (String [] args ){
		Blubb b = new Blubb();
		
		Customthread t1 = new Customthread ("first thread " , b , true);
		Customthread t2 = new Customthread ("second thread " , b , false);
		
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			System.out.println("Program end !");
		}
		
		
	}
}
