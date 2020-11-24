package gossip;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerWorker implements Runnable {
	private Socket client;
	private String message;
	private BufferedWriter wr;

	public ServerWorker(Socket client, String message) {
		this.client = client;
		this.message = message;
	}

	@Override
	public void run() {
		try {
			wr = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			wr.write(message);
			wr.flush();
			wr.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
