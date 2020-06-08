package node;

import java.util.ArrayList;

public class BlockChain {
	private boolean accessing = false; 
	private int threadsWaiting = 0;
	private ArrayList<Double> database = new ArrayList<Double>();
	
	public synchronized void acquireLock() throws InterruptedException {
		Thread client = Thread.currentThread(); 
		//System.out.println(client.getName() + " is attempting to acquire a lock!");
		++threadsWaiting;
		while (accessing) { 
			System.out.println(client.getName()+ " waiting to get a lock as someone else is accessing...");
			wait();		
		}
		--threadsWaiting;
		accessing = true;
		System.out.println(client.getName() + " got a lock!");	
	}
	
	public synchronized void releaseLock() {
		// release the lock and tell everyone
		accessing = false;
		notifyAll();
		Thread me = Thread.currentThread(); 
		System.out.println(me.getName() + " released a lock!");
	}
	
	public synchronized void receiveBlockChain(Double chain){
		database.add(chain);
		System.out.println("added to database " + chain);
	}
	
	public synchronized Double result(){
		Double answer = 0.0;
		for (Double blockChain:database) {
			if (blockChain > answer) 
				answer = blockChain;	
		}
		return answer;
	}
	

}
