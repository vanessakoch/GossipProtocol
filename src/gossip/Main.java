package gossip;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
		
    public static void main(String[] args) throws IOException {
    	Scanner t = new Scanner(System.in);
    	
    	List<Peer> peersList = new ArrayList<Peer>();
    	String host = "127.0.0.1";
    	int id = Integer.parseInt(args[0]);
    	int port = 2800 + id;
    	String peerName = "Peer " + port;
    	
    	peersList.add(new Peer(host, port + 1));	
    	peersList.add(new Peer(host, port + 2));	

    	//System.out.println("Digite uma mensagem:\n");
    	//String message = t.next();        
       
    	
    	for(Peer peer : peersList) {
    		
    		new Client().initiate(host, port, peer.getIp(), peer.getPort());		

    		//System.out.println(peerName + " está enviando mensagem: " + message + " para porta " + peer.getPort());
    	}
    	
    }
}
