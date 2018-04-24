import javax.swing.*;
import java.awt.*;

public class draw extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int nPoints;
	private int[] xPoints;
	private int[] yPoints;

	public draw() {
		super();
		nPoints = 400;
		this.xPoints = new int[nPoints];
		this.yPoints = new int[nPoints];
		calculatePosition();
	}

	void calculatePosition() {
		double t = 0 ;
		int time = 3;
		for (int i = 0; i < this.nPoints; i++) {
			xPoints[i] =  (int) (16 * Math.pow(Math.sin(t), 3)*time);
			yPoints[i] =  (int) ((13 * Math.cos(t) - 5 * Math.cos(2 * t) 
					- 2 * Math.cos(3 * t) - Math.cos(4 * t))*time);
			t +=2*Math.PI/nPoints;
		} 
		for (int i = 0; i < this.nPoints; i++) {
			yPoints[i]=-yPoints[i];
			xPoints[i] += 500;
			yPoints[i] += 500;
		}
	}
	public void drawing() {
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);

//		 for (int i = 0; i < 400; i++) {
//		 g.fillPolygon(xPoints, yPoints, nPoints);
//		 }
		// int xpoints[] = {25, 145, 25, 145, 25};
		// int ypoints[] = {25, 25, 145, 145, 25};
		// int npoints = 5;
		// g.fillPolygon(xpoints, ypoints, npoints);
		// g.fillRect(10, 15, 100, 20);
		// g.fillOval(0, 0, 200, 200);
		g.fillPolygon(xPoints, yPoints, nPoints);
	}
}
