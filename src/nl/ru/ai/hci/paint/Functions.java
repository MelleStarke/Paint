package nl.ru.ai.hci.paint;

import java.util.Random;

public class Functions {
	
	private DrawPanel dp;
	
	public Functions(DrawPanel dp){
		this.dp = dp;
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

}
