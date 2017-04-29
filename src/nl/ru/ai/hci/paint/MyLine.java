package nl.ru.ai.hci.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class MyLine implements Drawable {

	final private static double WIDTH = 15.0;
	private double x1, y1, x2, y2;
	private Color outline;
	private Color fill;
	private int linewidth;
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

	public MyLine(double x1, double y1, double x2, double y2, int linewidth, Color outline, Color fill) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.linewidth = linewidth;
		this.outline = outline;
		this.fill = fill;
		shape = new Line2D.Double(x1, y1, x2, y2);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(this.outline);
		g.setStroke(new BasicStroke(this.linewidth));
		g.draw(this.shape);
	}

	public boolean contains(double x, double y) {
		return this.shape.intersects(x - (WIDTH / 2), y - (WIDTH / 2), WIDTH, WIDTH);
	}

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
	public Mode getShapeType() {
		return Mode.line;
	}

	@Override
	public void modAesthetic(int linewidth, Color outline, Color fill, String text) {
		this.linewidth = linewidth;
		this.outline = outline;
		this.fill = fill;
	}

	@Override
	public void modPos(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.shape = new Line2D.Double(x1, y1, x2, y2);

	}
}