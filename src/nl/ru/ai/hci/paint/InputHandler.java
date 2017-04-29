package nl.ru.ai.hci.paint;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;

public class InputHandler implements ActionListener, MouseListener, MouseMotionListener {

	public DrawPanel dp;
	public Mode mode = Mode.def;
	private int tempX;
	private int tempY;
	private boolean BeginDragging;
	public boolean isSelected;
	private int selectedShapeIndex = -1;

	public InputHandler(DrawPanel dp) {
		this.dp = dp;
		this.dp.addMouseListener(this);
		this.dp.addMouseMotionListener(this);
		this.BeginDragging = true;
		this.isSelected = false;
	}

	public void actionPerformed(ActionEvent e) {
		// Random rand = new Random();
		// int randInt = rand.nextInt(13);
		// System.out.println(randInt);
		// this.dp.setColor(randomColor(randInt));
		this.isSelected = false;

		switch (e.getActionCommand()) {
		case "add":
			this.dp.addRandomShape();
			break;
		case "mod":
			this.dp.alterNextShape();
			break;
		case "rectangle":
			this.mode = Mode.rectangle;
			break;
		case "ellipse":
			this.mode = Mode.ellipse;
			break;
		case "line":
			this.mode = Mode.line;
			break;
		case "image":
			this.dp.image();
			break;
		case "text":
			this.mode = Mode.text;
			dp.text();
		case "delete":
			this.mode = Mode.delet;
			break;
		case "undo":
			this.dp.delete();
			break;
		case "outline color":
			this.dp.setOutlineColor();
			break;
		case "fill color":
			this.dp.setFillColor();
			break;
		case "transform":
			this.mode = Mode.transform;
			break;
		case "to back":
			this.mode = Mode.toBack;
			break;
		case "to front":
			this.mode = Mode.toFront;
			break;
		default:
			break;
		}
		this.dp.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {

		switch (this.mode) {
		case line:
			if (BeginDragging) {
				this.dp.line(tempX, tempY, arg0.getX(), arg0.getY());
				this.BeginDragging = false;
				break;
			} else {
				this.dp.delete();
				this.dp.line(tempX, tempY, arg0.getX(), arg0.getY());
				break;
			}

		case rectangle:
			if (BeginDragging) {
				this.dp.rectangle(tempX, tempY, arg0.getX(), arg0.getY());
				this.BeginDragging = false;
				break;
			} else {
				this.dp.delete();
				this.dp.rectangle(tempX, tempY, arg0.getX(), arg0.getY());
			}
			break;
		case ellipse:
			if (BeginDragging) {
				this.dp.ellipse(tempX, tempY, arg0.getX(), arg0.getY());
				this.BeginDragging = false;
				break;
			} else {
				this.dp.delete();
				this.dp.ellipse(tempX, tempY, arg0.getX(), arg0.getY());
				break;
			}
		case transform:
			if (!this.isSelected) {
				for (int i = dp.shapesList.size() - 1; i >= 0; i--)
					if (dp.shapesList.get(i).contains(arg0.getX(), arg0.getY())) {
						this.isSelected = true;
						this.selectedShapeIndex = i;
						break;
					}
			} else
				dp.transform(arg0.getX(), arg0.getY(), this.selectedShapeIndex);
		default:
			break;

		}
		this.dp.repaint();

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int x = arg0.getX();
		int y = arg0.getY();

		switch (this.mode) {
		/*
		 * case ellipse: this.dp.ellipse(x, y); break; case line:
		 * this.dp.line(x, y); break; case rectangle: this.dp.rectangle(x, y);
		 * break;
		 */
		case delet:
			this.dp.delete(x, y);
			break;
		case toFront:
			this.dp.toFront(x, y);
			break;
		case toBack:
			this.dp.toBack(x, y);
			break;
		case transform:
			this.isSelected = false;
			this.selectedShapeIndex = -1;
			for (int i = dp.shapesList.size() - 1; i >= 0; i--)
				if (dp.shapesList.get(i).contains(x, y)) {
					this.isSelected = true;
					this.selectedShapeIndex = i;
					break;
				}
			break;
		default:
			break;
		}
		this.dp.repaint();

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		this.tempX = arg0.getX();
		this.tempY = arg0.getY();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		this.BeginDragging = true;
		switch (this.mode) {
		case line:
			this.dp.delete();
			this.dp.line(tempX, tempY, arg0.getX(), arg0.getY());
			break;
		case rectangle:
			this.dp.delete();
			this.dp.rectangle(tempX, tempY, arg0.getX(), arg0.getY());
			break;
		case ellipse:
			this.dp.delete();
			this.dp.ellipse(tempX, tempY, arg0.getX(), arg0.getY());
			break;
		default:
			break;
		}
		this.dp.repaint();
	}

}
