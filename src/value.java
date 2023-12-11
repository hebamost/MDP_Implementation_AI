import java.awt.*;
import java.util.Arrays;

public class value {

	public String[][] policy = { { "     ", "", "" }, { "", "", "" }, { "", "", "_" } };

	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int RIGHT = 2;
	public static final int LEFT = 3;
	public int count = 0;
	public long time = 0;
	public double epsilon = 0.01;

	public static void copyArray(Double[][] x, Double[][] y) {
		for (int i = 0; i < y.length; i++) {
			x[i] = Arrays.copyOf(y[i], y[i].length);
		}
	}

	public Double[][] optValue(Double[][] util, Double[][] re) {
//		controller c = new controller();
//		System.out.println("init policy: ");
//		c.printPolic(policy);
		
		long start = System.currentTimeMillis();
		
		count = 0;
		Double[][] uDash = new Double[3][3];
		Double[][] u = new Double[3][3];

		copyArray(uDash, util);
		double gamma = 0.99;
		int stop;

		do {
			stop = 0;
			copyArray(u, uDash);
			for (int i = 2; i >= 0; i--) {
				for (int j = 0; j < 3; j++) {
					if (i == 0 && j == 0)
						continue;
					if (i == 0 && j == 2)
						break;
					uDash[i][j] = calValues(u, i, j, re, gamma);
					if(Math.abs(uDash[i][j] - u[i][j]) <= epsilon)
						stop++;
				}
			}
			
//			System.out.println("after iteration: ");
//			c.printPolic(policy);
			count++;
		} while (stop != 7);

		long end = System.currentTimeMillis();
		time = end - start;
		return uDash;
	}

	public static Point[] getPossibleDirections(Point index, int direction) {

		int indicator = 0;

		if (direction == DOWN || direction == RIGHT)
			indicator = 1;
		else if (direction == UP || direction == LEFT)
			indicator = -1;

		Point[] p = new Point[3];
		if (direction == UP || direction == DOWN) {
			if (checkIndex(index.x + indicator)) {
				p[0] = new Point(index.x + indicator, index.y);
			} else {
				p[0] = new Point(index.x, index.y);
			}
			if (checkIndex(index.y + indicator)) {
				p[1] = new Point(index.x, index.y + indicator);
			} else {
				p[1] = new Point(index.x, index.y);
			}
			if (checkIndex(index.y + indicator * -1)) {
				p[2] = new Point(index.x, index.y + indicator * -1);
			} else {
				p[2] = new Point(index.x, index.y);
			}
		} else if (direction == RIGHT || direction == LEFT) {

			if (checkIndex(index.y + indicator)) {
				p[0] = new Point(index.x, index.y + indicator);
			} else {
				p[0] = new Point(index.x, index.y);
			}
			if (checkIndex(index.x + indicator * -1)) {
				p[1] = new Point(index.x + indicator * -1, index.y);
			} else {
				p[1] = new Point(index.x, index.y);
			}
			if (checkIndex(index.x + indicator)) {
				p[2] = new Point(index.x + indicator, index.y);
			} else {
				p[2] = new Point(index.x, index.y);
			}
		}

		return p;
	}

	public static boolean checkIndex(int index) {
		return 0 <= index && index < 3;
	}

	public Double calValues(Double[][] util, int i, int j, Double[][] re, double gamma) {

		double max = -10000;

		// ****calc up direction value****//
		Point[] validDirections = getPossibleDirections(new Point(i, j), UP);
		double v = 0.8
				* (re[validDirections[0].x][validDirections[0].y]
						+ gamma * util[validDirections[0].x][validDirections[0].y])
				+ 0.1 * (re[validDirections[1].x][validDirections[1].y]
						+ gamma * util[validDirections[1].x][validDirections[1].y])
				+ 0.1 * (re[validDirections[2].x][validDirections[2].y]
						+ gamma * util[validDirections[2].x][validDirections[2].y]);

		if (v > max) {
			max = v;
			policy[i][j] = "UP";
		}

		// ****calc left direction value****//
		validDirections = getPossibleDirections(new Point(i, j), LEFT);
		v = 0.8 * (re[validDirections[0].x][validDirections[0].y]
				+ gamma * util[validDirections[0].x][validDirections[0].y])
				+ 0.1 * (re[validDirections[1].x][validDirections[1].y]
						+ gamma * util[validDirections[1].x][validDirections[1].y])
				+ 0.1 * (re[validDirections[2].x][validDirections[2].y]
						+ gamma * util[validDirections[2].x][validDirections[2].y]);

		if (v > max) {
			max = v;
			policy[i][j] = "LEFT";
		}

		// ****calc right direction value****//
		validDirections = getPossibleDirections(new Point(i, j), RIGHT);
		v = 0.8 * (re[validDirections[0].x][validDirections[0].y]
				+ gamma * util[validDirections[0].x][validDirections[0].y])
				+ 0.1 * (re[validDirections[1].x][validDirections[1].y]
						+ gamma * util[validDirections[1].x][validDirections[1].y])
				+ 0.1 * (re[validDirections[2].x][validDirections[2].y]
						+ gamma * util[validDirections[2].x][validDirections[2].y]);

		if (v > max) {
			max = v;
			policy[i][j] = "RIGHT";
		}

		// ****calc down direction value****//
		validDirections = getPossibleDirections(new Point(i, j), DOWN);
		v = 0.8 * (re[validDirections[0].x][validDirections[0].y]
				+ gamma * util[validDirections[0].x][validDirections[0].y])
				+ 0.1 * (re[validDirections[1].x][validDirections[1].y]
						+ gamma * util[validDirections[1].x][validDirections[1].y])
				+ 0.1 * (re[validDirections[2].x][validDirections[2].y]
						+ gamma * util[validDirections[2].x][validDirections[2].y]);

		if (v > max) {
			max = v;
			policy[i][j] = "DOWN";
		}

		return Math.round(max * 100.0) / 100.0;

	}

}