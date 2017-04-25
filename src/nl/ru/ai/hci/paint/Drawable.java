package nl.ru.ai.hci.paint;

import java.awt.Graphics2D;

public interface Drawable {
	
	public void draw(Graphics2D g);
	public boolean contains(double x,double y); 
}
