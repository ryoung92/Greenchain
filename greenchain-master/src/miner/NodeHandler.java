package miner;

import java.io.IOException;

//receives responses from the node
public class NodeHandler extends Thread{
	private Networking networking;
	
	public NodeHandler (Networking networking){
		this.networking = networking;
	}
	
	public void run(){
		System.out.println("Listening for node responses..");
        try {
        	String inputLine;
        	Double newBlockChain = 0.0;
	        while ((inputLine = networking.read()) != null) {
	        	System.out.println(inputLine);
	        	BlockChainData.chain += Double.parseDouble(inputLine);
	        	System.out.println(BlockChainData.chain);
	        }
        } catch(IOException e) {
        	
        }
	}
	
	
}
