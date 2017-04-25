package nl.ru.ai.hci.paint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class MyLine implements Drawable {

	final private static double WIDTH = 15.0;
	private double x1, y1, x2, y2;
	private Color outline;
	private Color fill;
	private double linewidth;
	private Line2D shape;

	public MyLine() {
	}

	public MyLine(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		shape = new Line2D.Double(x1, y1, x2, y2);
	}

	@Override
	public void draw(Graphics2D g) {
		g.draw(shape);
	}

	public boolean contains(double x, double y) {
		return this.shape.intersects(x - (WIDTH / 2), y - (WIDTH / 2), WIDTH, WIDTH);
	}
}