import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

	public static void main(String[] args) throws IOException {

		DatagramSocket socket = new DatagramSocket();

		byte[] buf = new byte[256];
		buf = new String("hello world").getBytes();
		InetAddress address = InetAddress.getByName(null);
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address,
				4445);
		socket.send(packet);

		socket.receive(packet);

		System.out.println(new String(packet.getData()));

		socket.close();

		System.out.println(new Float(1.2).byteValue());
	}
}
