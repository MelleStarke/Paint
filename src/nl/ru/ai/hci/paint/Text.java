package nl.ru.ai.hci.paint;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JLabel;

public class Text implements Drawable {

	private double x1, y1, x2, y2;
	private String text;
	private JLabel label;

	public Text(String input, double x1, double y1, double x2, double y2) {
		this.text = input;
		this.label = new JLabel(input);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public Text() {
		this.text = "test";
		this.x1 = 100.0;
		this.y1 = 100.0;
		this.x2 = 200.0;
		this.y2 = 200.0;
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawString(this.text, (int) x1, (int) y2);
	}

	@Override
	public boolean contains(double x, double y) {
		return x >= x1 && x <= x2 && y >= y1 && y <= y2;
	}

	@Override
	public Mode getShapeType() {
		return Mode.text;
	}

	@Override
	public void modAesthetic(int linewidth, Color outline, Color fill, String text) {
		this.text = text;
	}

	@Override
	public double[] getX1Y1X2Y2WH() {
		double[] dim = new double[6];
		dim[0] = Math.min(this.x1, this.x2);
		dim[1] = Math.min(this.y1, this.y2);
		dim[2] = Math.max(this.x1, this.x2);
		dim[3] = Math.max(this.y1, this.y2);
		dim[4] = Math.abs(this.x1 - this.y1);
		dim[5] = Math.abs(this.y1 - this.y2);
		return dim;
	}

	@Override
	public void modPos(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;

	}

}
