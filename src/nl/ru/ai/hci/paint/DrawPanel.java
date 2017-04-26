package nl.ru.ai.hci.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JColorChooser;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Color color;
	public ArrayList<Drawable> shapesList = new ArrayList<Drawable>();
	int modCount;

	public DrawPanel() {
		super();
		this.color = Color.BLACK;
		this.modCount = 0;
	}

	public DrawPanel(Color c) {
		super();
		this.color = c;
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		for (int i = 0; i < shapesList.size(); i++)
			shapesList.get(i).draw(g2d);
	}

	public void addRandomShape() {
		Random rand = new Random();

		int x1 = rand.nextInt(420);
		int x2 = rand.nextInt(420 - x1) + x1;
		int y1 = rand.nextInt(290);
		int y2 = rand.nextInt(290 - y1) + y1;

		switch (rand.nextInt(3)) {
		case 0:
			shapesList.add(new MyRectangle(x1, y1, x2, y2));
			break;
		case 1:
			shapesList.add(new MyLine(x1, y1, x2, y2));
			break;
		case 2:
			shapesList.add(new MyEllipse(x1, y1 / 2, x2, y2 / 2));
		default:
			break;
		}
	}

	public void alterNextShape() {
		if (shapesList.size() != 0) {
			Random rand = new Random();

			if (shapesList.size() <= modCount)
				modCount = 0;

			int x1 = rand.nextInt(420);
			int x2 = rand.nextInt(420 - x1) + x1;
			int y1 = rand.nextInt(290);
			int y2 = rand.nextInt(290 - y1) + y1;

			switch (rand.nextInt(3)) {
			case 0:
				shapesList.set(modCount, new MyRectangle(x1, y1, x2, y2));
				modCount++;
				break;
			case 1:
				shapesList.set(modCount, new MyLine(x1, y1, x2, y2));
				modCount++;
				break;
			case 2:
				shapesList.set(modCount, new MyEllipse(x1, y1 / 2, x2, y2 / 2));
				modCount++;
			default:
				break;
			}
		}
	}

	public void setColor(Color c) {
		this.color = c;
		this.repaint();
	}

	public Color getColor() {
		return this.color;
	}

	public void rectangle(int x1, int y1, int x2, int y2) {
		shapesList.add(new MyRectangle(x1, y1, x2, y2));
		this.repaint();
	}

	public void rectangle(int x, int y) {
		shapesList.add(new MyRectangle(x, y, x + 150, y + 100));
		this.repaint();
	}

	public void rectangle() {
		Random rand = new Random();
		int x1 = rand.nextInt(420);
		int x2 = rand.nextInt(420 - x1) + x1;
		int y1 = rand.nextInt(290);
		int y2 = rand.nextInt(290 - y1) + y1;
		shapesList.add(new MyRectangle(x1, y1, x2, y2));

	}

	public void line() {
		Random rand = new Random();
		int x1 = rand.nextInt(420);
		int x2 = rand.nextInt(420 - x1) + x1;
		int y1 = rand.nextInt(290);
		int y2 = rand.nextInt(290 - y1) + y1;
		shapesList.add(new MyLine(x1, y1, x2, y2));

	}

	public void line(int x, int y) {
		shapesList.add(new MyLine(x, y, x + 150, y + 100));
		this.repaint();                                                 
	}

	public void line(int x1, int y1, int x2, int y2) {
		shapesList.add(new MyLine(x1, y1, x2, y2));
		this.repaint();
	}

	public void ellipse() {
		Random rand = new Random();
		int x1 = rand.nextInt(420);
		int x2 = rand.nextInt(420 - x1) + x1;
		int y1 = rand.nextInt(290) / 2;
		int y2 = (rand.nextInt(290 - y1) + y1) / 2;
		shapesList.add(new MyEllipse(x1, y1, x2, y2));

	}

	public void ellipse(int x, int y) {
		shapesList.add(new MyEllipse(x, y, (x + 150), (y - 50)));
		this.repaint();
	}

	public void ellipse(int x1, int y1, int x2, int y2) {
		shapesList.add(new MyEllipse(x1, y1, x2, y2));
		this.repaint();
	}

	public void delete() {
		int size = shapesList.size();
		if (size != 0)
			shapesList.remove(size - 1);
	}

	public void delete(double x, double y) {
		System.out.println(shapesList.size());
		for (int i = 0; i < shapesList.size(); i++) {
			if (shapesList.get(i).contains(x, y)) {
				shapesList.remove(i);
				this.repaint();
			}
		}
	}

	public void colorSelect() {
		Color col = JColorChooser.showDialog(this, "Choose a color", color);

		if (col != null)
			this.color = col;
	}
	
	public void transform(){
	}
	
	public void toFront(double x, double y){
		for (int i = 0; i < shapesList.size(); i++)
			if(shapesList.get(i).contains(x, y)){
				Drawable temp = shapesList.get(i);
				shapesList.remove(i);
				shapesList.add(temp);
				this.repaint();
			}
	}
	
	public void toBack(double x, double y){
		for (int i = 0; i < shapesList.size(); i++)
			if(shapesList.get(i).contains(x, y)){
				Drawable temp = shapesList.get(i);
				shapesList.remove(i);
				shapesList.add(0, temp);
				this.repaint();
			}
	}
	
	
}