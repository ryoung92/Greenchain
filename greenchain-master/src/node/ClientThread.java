package node;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread{
	private Socket socket;
	private BlockChain blockChain;
	PrintWriter out = null;
	BufferedReader in = null;
	
	public ClientThread(Socket socket, BlockChain blockChain) {
		this.socket = socket;
		this.blockChain = blockChain;
	}
	
	public void run(){
		Thread thread = Thread.currentThread();
		System.out.println("[ClientThread] conection accepted");
		try {
			ClientHandler clientHandler= new ClientHandler(socket, blockChain);
			clientHandler.start();
			out = new PrintWriter(this.socket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			String inputLine, outputLine;
			while ((inputLine = in.readLine()) != null) {
				try {
					System.out.println("client sent "+inputLine);
					blockChain.acquireLock();
					System.out.println("[ClientThread] locking");
					blockChain.receiveBlockChain(Double.parseDouble(inputLine));
					blockChain.releaseLock();

					//calc result and send client result on enter key
					
					//TODO store client inputs
					//TODO compare client inputs 
					//TODO return results and reward to clients
				
				} catch (InterruptedException e) {
					System.err.println("Failed to get lock when reading:" + e);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
