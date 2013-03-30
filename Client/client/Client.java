package client;

import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Client {

	public static void main(String[] args) throws SlickException, IOException {

		AppGameContainer app = new AppGameContainer(new Game("Slime!", null));
		app.setDisplayMode(800, 600, false);
		app.start();
	}
}
