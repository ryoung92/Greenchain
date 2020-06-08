package node;

public class Main {
	public static void main(String[] args) {
		System.out.println("===================================");
		System.out.println("          Node Server");
		System.out.println("===================================\n");
		
		BlockChain blockChain = new BlockChain();
		
		System.out.println("[Main] Started PortListener");
		PortListener portlistener = new PortListener(Config.PORT_NUMBER, blockChain);
		portlistener.PortListenerStart();
		System.out.println("[Main] intialisation complete");
		
	}
}
