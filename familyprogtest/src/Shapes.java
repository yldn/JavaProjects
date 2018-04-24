import javax.swing.*;

public class Shapes {

	public static void main(String[] args) {
		 JFrame frame = new JFrame("Test");
		 frame.setVisible(true);
		 frame.setSize(1100, 1150);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 draw heart = new draw();
		 frame.add(heart);
		 heart.drawing();

		// char[] word = {'P','L','X'};
		// char [] word = {'ÆÖ','À½','Ü°'};
		char[] word = "lancy-pu ".toCharArray();
		int i = 0;
		float y, x, a;
		for (y = 1.5f; y > -1.5f; y -= 0.06f) {
			for (x = -1.5f; x < 1.5f; x += 0.025f) {
				a = x * x + y * y - 1;
				float z = a * a * a - x * x * y * y * y;
				System.out.print(z <= 0 ? word[i % word.length] : ' ');
				// System.out.print(z<=0? 'x':' ');
				i++;
			}
			System.out.println();
		}

	}
}
