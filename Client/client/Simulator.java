package client;

import java.util.HashSet;
import java.util.Set;

public class Simulator extends Thread {

	Set<Entity> entities = new HashSet<Entity>();

	private static final double GRAVITY = 0.0001;

	private long lastFrameTime;

	public Simulator() {

		// new Informer(entities, addresses).start();
	}

	@Override
	public void run() {
		lastFrameTime = System.nanoTime();

		while (true) {

			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			long currentTime = System.nanoTime();
			long difference = currentTime - lastFrameTime;

			double delta = difference / 1000000.0;
			lastFrameTime = currentTime;

			simulate(delta);
		}
	}

	private void simulate(double delta) {

		System.out.println("Delta : " + delta);

		for (Entity entity : entities) {

			if (entity.location.y <= 0) {
				entity.velocity.y = -entity.velocity.y;
			}
			if (entity.location.x <= -400 || entity.location.x >= 400) {
				entity.velocity.x = -entity.velocity.x;
			}

			entity.location.x += entity.velocity.x * delta;
			entity.location.y += entity.velocity.y * delta;

			entity.velocity.y -= GRAVITY * delta;

			System.out.println(entity.location);
		}
	}

	public void addPlayer(Slime slime) {
		entities.add(slime);
	}

	public void removePlayer(Slime slime) {
		entities.remove(slime);
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}
}
