package nl.ru.ai.hci.paint;

import java.awt.Color;
import java.awt.Graphics2D;

public interface Drawable {
	
	public void draw(Graphics2D g);
	public boolean contains(double x,double y); 
	public Mode getShapeType();
	public void modAesthetic(int linewidth, Color outline, Color fill);
	public double [] getX1Y1X2Y2WH();
	public void modPos(double x1, double y1, double x2, double y2);
}
