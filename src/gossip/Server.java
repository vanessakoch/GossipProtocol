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

	private int countMyRequest = 0;
	private boolean isIt = false;
	private String lastRequest[];
	
	private List<String> sendMsgs = new ArrayList<String>();

	public Server(int port, List<Peer> peersList) {
		super();
		this.port = port;
		this.peersList = peersList;
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
				
//				System.out.println("countMyReq: " + countMyRequest);
//				System.out.println("main i: " + Main.i);
				
				if (!isSent(sendMsg)) {
					for (Peer peer : peersList) {
						System.out.println(countMyRequest);
						new Thread(new Client(peer.getIp(), peer.getPort(), sendMsg)).run();
					}
				} 
				 
			}
			
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	private boolean isSent(String msg) {
		for(String s : sendMsgs) {
			if(s.equals(msg)) {
				countMyRequest++;
				return true;
			}
		}
		sendMsgs.add(msg);
		return false;
	}


}