package gossip;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	private static ExecutorService configuraThreads;
	public static String message;
	
	public static void main(String[] args) {
    	Scanner t = new Scanner(System.in);

		int port = 2801;

		try {
			System.out.println("======= Servidor rodando na porta " + port + " =======");
			ServerSocket server = new ServerSocket(port);
			configuraThreads = Executors.newWorkStealingPool();
			System.out.println("Digite uma mensagem:");
	    	message = t.next();
	    	
			while (true) {
				waiting(server);
			}

		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public static void waiting(ServerSocket server) {		
		try {
			Socket client = server.accept();
			System.out.println("Novo cliente conectado no servidor! Porta: " + client.getPort());
			ServerWorker worker = new ServerWorker(client, message);
			configuraThreads.execute(worker);
			
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}