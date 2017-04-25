package nl.ru.ai.hci.paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InputHandler implements ActionListener, MouseListener, MouseMotionListener {

	public DrawPanel dp;
	public Mode mode = Mode.def;
	private int tempX;
	private int tempY;
	private boolean BeginDragging;

	public InputHandler(DrawPanel dp) {
		this.dp = dp;
		this.dp.addMouseListener(this);
		this.dp.addMouseMotionListener(this);
		this.BeginDragging = true;
	}

	public void addDP(DrawPanel dp) {
		this.dp = dp;
		dp.addMouseListener(this);
		dp.addMouseMotionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		// Random rand = new Random();
		// int randInt = rand.nextInt(13);
		// System.out.println(randInt);
		// this.dp.setColor(randomColor(randInt));

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
		case "delete":
			this.mode = Mode.delet;
			break;
		case "undo":
			this.dp.delete();
			break;
		case "color":
			this.dp.colorSelect();
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
		case def:
			break;
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
		 * this.dp.line(x, y); break;
		 */case rectangle:
			this.dp.rectangle(x, y);
			break;
		case delet:
			this.dp.delete(x, y);
		case toFront:
			this.dp.toFront(x, y);
		case toBack:
			this.dp.toBack(x, y);
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
