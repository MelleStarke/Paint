package nl.ru.ai.hci.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JColorChooser;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private InputHandler IH;
	private Color outline = Color.BLACK;
	private Color fill = new Color(0, 0, 0, 0);;
	private int linewidth = 10;
	public ArrayList<Drawable> shapesList = new ArrayList<Drawable>();
	int modCount;

	public DrawPanel() {
		super();
		this.modCount = 0;
		setBackground(Color.WHITE);
	}

	public void addIH(InputHandler IH) {
		this.IH = IH;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		for (int i = 0; i < shapesList.size(); i++) {
			shapesList.get(i).draw(g2d);
		}
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

	public void rectangle(int x1, int y1, int x2, int y2) {
		shapesList.add(new MyRectangle(x1, y1, x2, y2, linewidth, outline, fill));
		this.repaint();
	}

	public void line(int x1, int y1, int x2, int y2) {
		shapesList.add(new MyLine(x1, y1, x2, y2, linewidth, outline, fill));
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

	public void ellipse(int x1, int y1, int x2, int y2) {
		shapesList.add(new MyEllipse(x1, y1, x2, y2, linewidth, outline, fill));
		this.repaint();
	}

	public void delete() {
		int size = shapesList.size();
		if (size != 0)
			shapesList.remove(size - 1);
	}

	public void delete(double x, double y) {
		System.out.println(shapesList.size());
		for (int i = shapesList.size() - 1; i >= 0; i--) {
			if (shapesList.get(i).contains(x, y)) {
				shapesList.remove(i);
				this.repaint();
				break;
			}
		}
	}

	public void setOutlineColor() {
		Color col = JColorChooser.showDialog(this, "Outline Color", outline);

		if (col != null)
			this.outline = col;
	}

	public void setFillColor() {
		Color col = JColorChooser.showDialog(this, "Fill Color", outline);

		if (col != null)
			this.fill = col;
	}

	private int resizeBuffer = 35;

	public void transform(double x, double y, int index) {
		double[] dim = shapesList.get(index).getX1Y1X2Y2WH();

			// if in the middle
		if (between(x, dim[0] + 0.25 * dim[4], dim[2] - 0.25 * dim[4])
				&& between(y, dim[1] + 0.25 * dim[5], dim[3] - 0.25 * dim[3]))
			shapesList.get(index).modPos(x - dim[4] * .5, y - dim[5] * .5, x + dim[4] * .5, y + dim[5] * .5);
		else

			// in top left
		if (between(x, dim[0] - resizeBuffer, dim[0] + .5 * dim[4])
				&& between(y, dim[1] - resizeBuffer, dim[1] + 0.5 * dim[5]))
			shapesList.get(index).modPos(x, y, dim[2], dim[3]);
		else

			// in top right
		if (between(x, dim[2] - 0.5 * dim[4], dim[2] + resizeBuffer)
				&& between(y, dim[1] - resizeBuffer, dim[1] + 0.5 * dim[5]))
			shapesList.get(index).modPos(dim[0], y, x, dim[3]);
		else

			// in bottom left
		if (between(x, dim[0] - resizeBuffer, dim[0] + .5 * dim[4])
				&& between(y, (dim[3] + dim[1]) / 2, dim[3] + resizeBuffer))
			shapesList.get(index).modPos(x, dim[1], dim[2], y);
		else

			// in botton right
		if (between(x, (dim[0] + dim[2]) / 2, dim[2] + resizeBuffer)
				&& between(y, (dim[3] + dim[1]) / 2, dim[3] + resizeBuffer))
			shapesList.get(index).modPos(dim[0], dim[1], x, y);
	}

	private boolean between(double mid, double min, double max) {
		return mid >= min && mid <= max;
	}

	public void toFront(double x, double y) {
		for (int i = 0; i < shapesList.size(); i++)
			if (shapesList.get(i).contains(x, y)) {
				Drawable temp = shapesList.get(i);
				shapesList.remove(i);
				shapesList.add(temp);
				this.repaint();
			}
	}

	public void toBack(double x, double y) {
		for (int i = 0; i < shapesList.size(); i++)
			if (shapesList.get(i).contains(x, y)) {
				Drawable temp = shapesList.get(i);
				shapesList.remove(i);
				shapesList.add(0, temp);
				this.repaint();
			}
	}

	public void modShape(double x, double y) {
		for (int i = shapesList.size() - 1; i >= 0; i--) {
			if (shapesList.get(i).contains(x, y)) {
				shapesList.get(i).modAesthetic(this.linewidth, this.outline, this.fill);
				this.repaint();
				break;
			}
		}
	}

}