package gossip;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServerWorker implements Runnable {
	private ServerSocket server;
	private Socket client;
	private ObjectInputStream input;

	public ServerWorker(ServerSocket server, Socket client) throws IOException {
		this.server = server;
		this.client = client;
	}

	@Override
	public void run() {
		try {
			client = server.accept();
			input = new ObjectInputStream(client.getInputStream());
			System.out.println("Conexão feita com o cliente " + client.getInetAddress() + " " + client.getPort());
			System.out.println(msgReceiver());

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}

	public String msgReceiver() throws InterruptedException {
		String message = null;

		try {
			message = input.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				int port = client.getPort();

				client.shutdownInput();
				client.shutdownOutput();
				input.close();
				client.close();
				server.close();
				System.out.println("Conexão finalizada");
				int a = 1;
				
				if(a == 1)
					Thread.sleep(3000);
					System.out.println("The socket is connected: " + client.isConnected());  
					Server s = new Server(3805); //precisa ser porta do cliente
					s.startServer();
					a+=1;
			}
			catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}

		if (message != null) {
			return "Mensagem do servidor porta " + server.getLocalPort() + " para cliente porta " + client.getPort()
					+ " : " + message;
		}

		return null;
	}

}