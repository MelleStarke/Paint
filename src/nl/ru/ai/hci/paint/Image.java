package nl.ru.ai.hci.paint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class Image implements Drawable {

	private double x1, y1, x2, y2;
	private BufferedImage image;

	public Image() {
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {

			try {
				image = ImageIO.read(chooser.getSelectedFile());
			} catch (IOException e) {
				System.out.println("error occured while importing the image");
				e.printStackTrace();
			}
			this.x1 = 0.0;
			this.y1 = 0.0;
			this.x2 = image.getWidth();
			this.y2 = image.getHeight();
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(this.image, new AffineTransform(1f, 0f, 0f, 1f, 0, 0), null);

	}

	@Override
	public boolean contains(double x, double y) {
		return x >= this.x1 && x <= this.x2 & y >= this.y1 && y <= this.y2;
	}

	@Override
	public Mode getShapeType() {
		return Mode.image;
	}

	@Override
	public void modAesthetic(int linewidth, Color outline, Color fill, String text) {
	}

	@Override
	public double[] getX1Y1X2Y2WH(){
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
