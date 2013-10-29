package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class ClientListener extends Thread {

	private final String serverAddress;

	private final Map<Integer, Entity> players;

	private int numPlayers = 0;

	public ClientListener(String serverAddress, Map<Integer, Entity> players)
			throws IOException {
		this.players = players;
		this.serverAddress = serverAddress;

		URL website = new URL(serverAddress);
		URLConnection yc = website.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				yc.getInputStream()));
		numPlayers = Integer.parseInt(in.readLine());

		in.close();

		// for (int i = 1; i < numPlayers + 1; i++) {
		//
		// website = new URL(serverAddress + "?player=" + i);
		// System.out.println(website.toString());
		// yc = website.openConnection();
		// in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		//
		// int x = Integer.parseInt(in.readLine());
		// int y = Integer.parseInt(in.readLine());
		//
		// in.close();
		//
		// Vector location = new Vector(x, y);
		// players.put(i, location);
		// }

	}

	public int getNumber() {
		return numPlayers;
	}

	@Override
	public void run() {
		while (true) {
			try {

				for (int i = 1; i < numPlayers + 1; i++) {

					URL website = new URL(serverAddress + "?player=" + i);
					URLConnection yc = website.openConnection();
					BufferedReader in = new BufferedReader(
							new InputStreamReader(yc.getInputStream()));

					players.get(i).location.x = Float.parseFloat(in.readLine());
					players.get(i).location.y = Float.parseFloat(in.readLine());

					players.get(i).velocity.x = Float.parseFloat(in.readLine());
					players.get(i).velocity.y = Float.parseFloat(in.readLine());

					System.out.println("From Server: " + players.get(i));

					in.close();
					sleep(5000);
				}

			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
