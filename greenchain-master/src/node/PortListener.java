
package node;

import java.io.IOException;
import java.net.ServerSocket;

public class PortListener {
	private ServerSocket serversocket;
	private PortListenerThread portListenerThread;
	private int portNumber;
	private BlockChain blockChain;
	
	public PortListener(int portNumber, BlockChain blockChain){
		this.portNumber = portNumber;
		this.blockChain = blockChain;
	}
	
	public boolean PortListenerStart(){
		try {
			this.serversocket = new ServerSocket(this.portNumber);
		} catch (IOException e) {
			return false;
		}
		System.out.println("[PortLisenter] Created server socket successfully - starting listen thread.");
		this.portListenerThread = new PortListenerThread(this.serversocket, blockChain);
		this.portListenerThread.start();
		System.out.println("[PortListner] created thread for listener");
		return true;
	}
}
