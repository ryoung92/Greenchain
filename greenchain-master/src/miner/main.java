package miner;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
		
		System.out.println("===================================");
		System.out.println("          Miner Client");
		System.out.println("===================================\n");
    	
    	Networking networking = new Networking();
		networking.setHostAddress("localHost");
		networking.setHostPort(7175);
		
		System.out.println("Connecting to node...");
		
		int connectStatus = networking.connect();
		if(connectStatus == Networking.CONNECT_SUCCESS) {
			System.out.println("Connected to server!");
		}
		if(connectStatus == Networking.CONNECT_FAIL_UNKNOWN_HOST) {
			System.out.println("Failed to connect to server: Unknown host");
			System.exit(1);
		}      
		if(connectStatus == Networking.CONNECT_FAIL_IO_ERROR) {
			System.out.println("Failed to connect to server: IO error");
			System.exit(1);
		}
		
		NodeHandler nodeHandler = new NodeHandler(networking);
		nodeHandler.start();
		
		try {
			Scanner reader = new Scanner(System.in);
			System.out.println("Press ENTER to send proof of work to node");
			while(true) {
				String input = reader.nextLine();
				if(input != null) {
					networking.sendBlockchain();
				}
			}
		} catch (Exception e) {
			
		}

		
        networking.disconnect();
        System.out.println("Disconnected from the server.");
	}
	//get blocks
    //make string of blocks (makes a chain)
    //submit for proof of work
    //receive reward from node
	
	//main node
	//miner
	//transaction client
	//work
	
	//receives transactions data
	//adds this to a block
	// pushes this to a node
    
    
    
    
}
