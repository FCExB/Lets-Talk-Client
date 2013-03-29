import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {

	public static void main(String args[]) throws IOException {

		DatagramSocket socket = new DatagramSocket(4445);

		while (true) {

			byte[] test = new byte[256];
			DatagramPacket p = new DatagramPacket(test, test.length);
			socket.receive(p);

			InetAddress address = p.getAddress();
			int port = p.getPort();
			p = new DatagramPacket(test, test.length, address, port);
			socket.send(p);

		}

	}
}
