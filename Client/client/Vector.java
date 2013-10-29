package client;

public class Vector {
	double x, y;

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public int getXAsInt() {
		return (int) Math.round(x);
	}

	public int getYAsInt() {
		return (int) Math.round(y);
	}

	public float magintude() {
		return (float) Math.sqrt(x * x + y * y);
	}

	public void normalise() {
		float mag = magintude();
		x = x / mag;
		y = y / mag;
	}

	public static Vector sub(Vector one, Vector two) {
		return new Vector(one.x - two.x, one.y - two.y);
	}

	@Override
	public String toString() {
		return "(" + x + " , " + y + ")";
	}
}
