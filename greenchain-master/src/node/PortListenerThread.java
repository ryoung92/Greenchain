package node;

import java.net.ServerSocket;
import java.net.Socket;

public class PortListenerThread extends Thread{
	private ServerSocket serversocket;
	private boolean listening = true;
	private BlockChain blockChain;

	
	public PortListenerThread(ServerSocket serversocket, BlockChain blockChain){
		this.serversocket = serversocket;
		this.blockChain = blockChain;
	}
	
	public void run(){
		System.out.println("[PortListenerThread] Listening for incoming connections");
		while(listening){
			Socket newClient = null;
			try {	
				newClient = this.serversocket.accept();
			} catch (Exception e) {
				System.out.println("[PortListenerThread] Error accepting connetion"+ e.getMessage());
			}
			new ClientThread(newClient, blockChain).start();
		}
	}
}
