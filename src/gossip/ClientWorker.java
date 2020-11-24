package gossip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientWorker implements Runnable {
	private Socket client;
	private BufferedReader reader;

	public ClientWorker(Socket client) {
		this.client = client;
	}

	@Override
	public void run() {
		try {
			reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

			String msg = reader.readLine();
			System.out.println(msg);
			
			reader.close();
			
			
		} catch (IOException e) {
			System.err.println(e);
		} 
	}

}
