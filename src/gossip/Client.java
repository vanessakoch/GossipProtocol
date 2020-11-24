package gossip;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public void initiate(String host, int port, String localAddr, int localPort) {
		try (Socket client = new Socket(host, port, InetAddress.getByName(localAddr), localPort)) {
			System.out.println("Cliente conectou no servidor! Porta: " + client.getLocalPort());
			performTask(client);
		} catch (UnknownHostException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	private void performTask(Socket client) {
		try {
			Thread worker = new Thread(new ClientWorker(client));
			worker.start();
			worker.join();
			
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			System.out.println("Cliente desconectado! Porta: " + client.getLocalPort());
		}
	}

}
