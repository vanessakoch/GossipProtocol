package gossip;

import java.io.IOException;
import java.net.UnknownHostException;

public class Main {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		int localPort = 3801;

		Server server1 = new Server(localPort);
		server1.startServer();
		
	
	}

}