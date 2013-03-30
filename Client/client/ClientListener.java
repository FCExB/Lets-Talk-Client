package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.Map;

public class ClientListener extends Thread {

	private final Map<Byte, Location> players;
	DatagramSocket socket;

	public ClientListener(Map<Byte, Location> players) throws SocketException {
		this.players = players;

		socket = new DatagramSocket(4444);
	}

	@Override
	public void run() {

		while (true) {
			byte[] buf = new byte[256];
			DatagramPacket packet = new DatagramPacket(buf, buf.length);

			try {
				socket.receive(packet);

				byte[] data = packet.getData();

				if (data[0] != 0) {
					ByteBuffer bb = ByteBuffer.wrap(data, 1, data.length - 1);
					
					int x = bb.getInt();
					int y = bb.getInt();

					players.put(data[0], new Location(bb.getInt(), bb.getInt()));
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
