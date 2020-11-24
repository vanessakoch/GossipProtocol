package gossip;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ServerWorker implements Runnable {
	
	private Socket client;
	
	public ServerWorker(Socket client) {
		this.client = client;
	}
	
	@Override
	public void run() {
		try {
			sendMessage();
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendMessage() throws IOException {
		try(Scanner t = new Scanner(System.in)){
			while(t.hasNextLine()) {
				PrintStream write = new PrintStream(client.getOutputStream());
				String message = t.nextLine();
				if(disconnect(message))
					break;
				write.println(message);
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	private boolean disconnect(String message) {
		return message.equals("");
	}

}
