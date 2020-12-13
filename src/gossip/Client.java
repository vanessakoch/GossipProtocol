package gossip;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client extends Thread {
	private String localAddr;
	private int port;
	private String msg;
	private BufferedWriter wr;

	public Client(String localAddr, int port, String msg) {
		this.localAddr = localAddr;
		this.port = port;
		this.msg = msg;
	}

	@Override
	public void run() {
		Socket client;
		
		try {
			client = new Socket(localAddr, port);
			wr = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			wr.write(msg);
			wr.flush();
			wr.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
