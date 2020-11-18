package gossip;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
	private int localPort;
	private String host = "127.0.0.1";
	private ObjectOutputStream output;
	private ServerSocket server = null;

	public Server(int localPort) {
		this.localPort = localPort;
	}

	public void startServer() throws InterruptedException {
		try {
			server = new ServerSocket(localPort);
			
			//while(true) {
			System.out.println("Servidor está ouvindo na porta " + localPort);

			int port = server.getLocalPort();

			Socket client = new Socket(host, server.getLocalPort(), InetAddress.getLocalHost(), (port + 1));

			new Thread(new ServerWorker(server, client)).start();

			
			sendMessage("Mensagem", client);


			//}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String message, Socket client) {

		try {
			output = new ObjectOutputStream(client.getOutputStream());

			output.writeUTF(message);
			output.flush();
			output.close();

		} catch (IOException e) {
			System.out.println("Server está desconectado.");
			e.printStackTrace();
		} 
	}

}