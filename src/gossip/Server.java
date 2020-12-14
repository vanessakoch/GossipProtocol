package gossip;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {
	private int port;
	private List<Peer> peersList;
	private ServerSocket server;
	private Socket client;
	private BufferedReader reader;
	private List<String> messageList;

	public Server(int port, List<Peer> peersList) {
		super();
		this.port = port;
		this.peersList = peersList;
		this.messageList = new ArrayList<String>();
	}

	@Override
	public void run() {
		try {
			System.out.println("\n======= Servidor rodando na porta " + port + " =======\n");
			server = new ServerSocket(port);

			while (true) {
				client = server.accept();

				reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
				String msg = reader.readLine();
				System.out.println(msg);
				reader.close();

				String[] copyReceive = msg.split(" ");

				String sendMsg = port + " enviou fofoca: " + copyReceive[3];

				if (!isAlreadySent(sendMsg)) {
					for (Peer peer : peersList) {
						new Thread(new Client(peer.getIp(), peer.getPort(), sendMsg)).run();
					}
				}
			}

		} catch (Exception e) {
			System.err.println(e);
		}
	}

	private boolean isAlreadySent(String msg) {
		for (String s : messageList) {
			if (s.equals(msg)) {
				return true;
			}
		}
		messageList.add(msg);
		return false;
	}

}