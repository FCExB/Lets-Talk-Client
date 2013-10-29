package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.newdawn.slick.AppGameContainer;

public class Client {

	public static void main(String[] args) throws Exception {

		AppGameContainer app = new AppGameContainer(new Game("Slime!",
				"http://fcexb.com/games/slime/server.php"));
		app.setDisplayMode(800, 600, false);
		app.start();
	}

	public static String getServer() throws Exception {
		URL website = new URL("http://fcexb.com/input.php");
		URLConnection yc = website.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				yc.getInputStream()));
		String inputLine;

		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		in.close();

		return inputLine;
	}

}
