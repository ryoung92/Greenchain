package miner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

//Initializing connection requirements
public class Networking {
	
	public static final int CONNECT_SUCCESS = 0;
	public static final int CONNECT_FAIL_UNKNOWN_HOST = 1;
	public static final int CONNECT_FAIL_IO_ERROR = 2;
	public static final int DISCONECT_SUCCESS = 3;
	
	private Socket socket = null;
	private String address = "localHost";
	private int port = 7175;
    private PrintWriter outputStream = null;
    private BufferedReader inputStream = null;
	
    public Networking(){
    	
    }
    
    public void setHostAddress(String hostAddress){
    	address = hostAddress;
    }
    public void setHostPort(int hostPort) {
		port = hostPort;
	}
    
    public int connect(){
    	try {
    		System.out.println("[Networking] created communication streams");
    		socket = new Socket(address,port);	
    		outputStream = new PrintWriter(socket.getOutputStream(), true);
			inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			return CONNECT_SUCCESS;
    	} catch(UnknownHostException e) {
			return CONNECT_FAIL_UNKNOWN_HOST;
		} catch(IOException e) {
			return CONNECT_FAIL_IO_ERROR;
		}
    }
    
	public int disconnect() {
		try {
			socket.close();
			inputStream.close();
			outputStream.close();
		} catch(IOException e) {
			
		}	
		return DISCONECT_SUCCESS;
	}
   
	public String read() throws IOException {
		return inputStream.readLine();
	}
	
	public void sendBlockchain(){
		Double transaction = Math.random() + BlockChainData.chain;
		outputStream.println(String.valueOf(transaction));
		System.out.println("Sent transaction: " + transaction);
	}
    
	
	
}
