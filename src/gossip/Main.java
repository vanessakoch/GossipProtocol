package gossip;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int firstClient = 0;
	static int i = 0;

	public static void main(String[] args) throws IOException {
		Scanner t = new Scanner(System.in);

		List<Peer> peersList = new ArrayList<Peer>();
		String host = "127.0.0.1";
		int id = Integer.parseInt(args[0]);
		int port = 2800 + id;

		if (port == 2806) {
			peersList.add(new Peer(host, 2801));
		} else if (port == 2805) {
			peersList.add(new Peer(host, 2806));
		} else {
			peersList.add(new Peer(host, port + 1));
			peersList.add(new Peer(host, port + 2));
		}

		new Thread(new Server(port, peersList)).start();

		while (true) {
			t = new Scanner(System.in);
			String msg = t.next();

			if (msg != null) {
				for (Peer peer : peersList) {
					String sendMsg = port + " enviou fofoca: " + msg;
					new Thread(new Client(peer.getIp(), peer.getPort(), sendMsg)).run();

				}
			}
		}

	}
}
