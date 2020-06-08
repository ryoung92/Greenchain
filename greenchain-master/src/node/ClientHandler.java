package node;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler extends Thread{
	private Socket socket;
	private BlockChain blockChain;
	private PrintWriter out = null;
	
	public ClientHandler(Socket socket, BlockChain blockChain){
		this.socket = socket;
		this.blockChain = blockChain;
	}
	
	public void run(){
		Double result = 0.0;
		try {
			out = new PrintWriter(this.socket.getOutputStream(),true);
			Scanner reader = new Scanner(System.in);
			System.out.println("Press ENTER to send result to client");
			while(true) {
				String input = reader.nextLine();
				if(input != null) {
					result = blockChain.result();
					out.println(String.valueOf(result));
				}
			}
		} catch (Exception e) {
			
		}
	}
}
