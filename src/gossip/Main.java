package gossip;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		
		List<Peer> peersList = new ArrayList<Peer>();
		String host = "127.0.0.1";
		int id = Integer.parseInt(args[0]);
		int port = 2800 + id;
		
		peersList.add(new Peer(host, port + 1));
		peersList.add(new Peer(host, port + 2));

		for (Peer peer : peersList) {
			new Client().initiate(host, 2801, peer.getIp(), peer.getPort());
		}

	}
}
