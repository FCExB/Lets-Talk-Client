package client;

public class Entity {
	final int id;

	Vector location = new Vector(0, 0);
	int radius;
	Vector velocity = new Vector(0, 0);

	public Entity(int id, int radius) {
		this.id = id;
		this.radius = radius;
	}

	public Entity(int id, int radius, int x, int y) {
		this.id = id;
		this.radius = radius;

		location = new Vector(x, y);
	}

	public Entity(int i, int radius, Vector loc) {
		this.id = i;
		this.radius = radius;

		location = loc;
	}

	public Vector collidesWith(Entity that) {
		Vector vec = Vector.sub(this.location, that.location);

		float distance = Math.abs(vec.magintude());

		if (distance > this.radius + that.radius) {
			return null;
		}

		return vec;
	}
}
