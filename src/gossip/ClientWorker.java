package gossip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientWorker implements Runnable {
	
	private Socket client;
	
	public ClientWorker(Socket client) {
		this.client = client;
	}

	@Override
	public void run() {
		try {
			InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
            String msg = reader.readLine();
            System.out.println(msg);
            
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}

