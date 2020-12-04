package sys.set;

import java.util.Random;

public class Constraints {
	protected int upper, lower;
	protected int x, y;
	Random rin = new Random();
	public Constraints(int lower, int upper) throws RuntimeException{
		if (lower > upper) throw new RuntimeException("Limit exception");
		this.lower = lower; this.upper = upper;
		x = rin.nextInt(upper - lower + 1) + lower;
		y = rin.nextInt(upper - lower + 1) + lower;
		if (x < y) {
			int t = x; x = y; y = t;
		}
	}
	public void flush() {
		x = rin.nextInt(upper - lower + 1) + lower;
		y = rin.nextInt(upper - lower + 1) + lower;
		if (x < y) {
			int t = x; x = y; y = t;
		}
	}
	public int getMax() {
		return x;
	}
	public int getMin() {
		return y;
	}
}
