package nl.ru.ai.hci.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class MyEllipse implements Drawable {

	private double x1, y1, x2, y2;
	private Color outline;
	private Color fill;
	private int linewidth;
	private Ellipse2D shape;

	public MyEllipse() {
	}

	public MyEllipse(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		double x = getStartX();
		double y = getStartY();
		double width = getWidth();
		double height = getHeight();
		shape = new Ellipse2D.Double(x, y, width, height);
	}

	public MyEllipse(double x1, double y1, double x2, double y2, int linewidth, Color outline, Color fill) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.linewidth = linewidth;
		this.outline = outline;
		this.fill = fill;
		double x = getStartX();
		double y = getStartY();
		double width = getWidth();
		double height = getHeight();
		shape = new Ellipse2D.Double(x, y, width, height);
	}

	@Override
	public void draw(Graphics2D g) {
		// double x = getStartX();
		// double y = getStartY();
		// double width = getWidth();
		// double height = getHeight();
		// Rectangle2D r = new Rectangle2D.Double(x, y, width, height);
		// Ellipse2D e = new Ellipse2D.Double(x,y,width,height);
		g.setColor(this.outline);
		g.setStroke(new BasicStroke(this.linewidth));
		g.draw(this.shape);
		g.setColor(this.fill);
		g.fill(this.shape);
	}

	private double getWidth() {
		return Math.abs(x1 - x2);
	}

	private double getHeight() {
		return Math.abs(y1 - y2);
	}

	private double getStartX() {
		return Math.min(x1, x2);
	}

	private double getStartY() {
		return Math.min(y1, y2);
	}

	private double getEndY() {
		return Math.max(y1, y2);
	}

	private double getEndX() {
		return Math.max(x1, x2);
	}
	
	public double [] getX1Y1X2Y2WH(){
		double [] dim = new double [6];
		dim[0] = getStartX();
		dim[1] = getStartY();
		dim[2] = getEndX();
		dim[3] = getEndY();
		dim[4] = getWidth();
		dim[5] = getHeight();
		return dim;
	}

	public boolean contains(double x, double y) {
		// Ellipse2D temp = new
		// Ellipse2D.Double(getStartX(),getStartY(),getWidth(),getHeight());
		return this.shape.contains(x, y);
	}

	@Override
	public Mode getShapeType() {
		return Mode.ellipse;
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
		this.shape = new Ellipse2D.Double(getStartX(), getStartY(), getWidth(), getHeight());
	}

}
