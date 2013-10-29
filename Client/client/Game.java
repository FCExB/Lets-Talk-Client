package client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

public class Game extends BasicGame {

	private final Map<Integer, Entity> players = new HashMap<Integer, Entity>();

	private final String serverAddress;

	Simulator sim = new Simulator();

	int radius = 30;
	int controlTime;

	Image semi;

	private final int playerNum;

	public Game(String title, String serverAddress) throws IOException,
			SlickException {
		super(title);
		this.serverAddress = serverAddress;

		ClientListener listener = new ClientListener(serverAddress, players);
		playerNum = listener.getNumber();
		listener.start();

		Simulator sim = new Simulator();

		Entity ball = new Entity(1, 10, new Vector(200, 100));

		sim.addEntity(ball);

		players.put(1, ball);

		sim.start();

	}

	@Override
	public void init(GameContainer container) throws SlickException {
		semi = new Image("new semi.png");
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setColor(Color.blue);
		g.fillRect(0, 0, 800, 600);

		g.setColor(Color.black);
		g.fillRect(0, 550, 800, 50);

		g.setColor(Color.white);
		g.fillRect(398, 500, 4, 50);

		for (Entry<Integer, Entity> entry : players.entrySet()) {

			int x = entry.getValue().location.getXAsInt() + 400;
			int y = 550 - entry.getValue().location.getYAsInt();

			if (entry.getKey() == 1) {
				g.draw(new Circle(x, y, 10));
			} else {

				semi.draw(x - radius, y - radius, radius * 2, radius);
			}

		}

	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {

		controlTime += delta;

		if (controlTime >= 100) {
			Input input = container.getInput();

			byte x = 0, y = 0;

			if (input.isKeyPressed(Input.KEY_SPACE)) {
				y++;
			}

			if (input.isKeyDown(Input.KEY_RIGHT)) {

				x++;
			}

			if (input.isKeyDown(Input.KEY_LEFT)) {

				x--;
			}

			if (x != 0 || y != 0) {
				// try {
				// URL website = new URL(serverAddress + "?player="
				// + playerNum + "&x=" + x + "&y=" + y);
				// website.openConnection();
				// } catch (IOException e) {
				// e.printStackTrace();
				// }
			}

			controlTime = 0;
		}

	}

	@Override
	public boolean closeRequested() {

		return true;
	}
}
