package server;

import java.net.SocketException;

import client.Simulator;

public class Server {
	public static void main(String[] args) throws SocketException {

		Simulator sim = new Simulator();
		sim.start();

		Listener lis = new Listener(sim);
		lis.start();
	}
}
