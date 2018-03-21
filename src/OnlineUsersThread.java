import java.io.PrintWriter;

public class OnlineUsersThread extends Thread {
	
	String userName;
	private PrintWriter writer;
	ChatServer server;
	boolean running = true;
	int size;
	
	OnlineUsersThread(String userName, ChatServer server, PrintWriter writer){
		this.userName = userName;
		this.server = server;
		this.writer = writer;
	}
	
	public void run() {
		// Get server to send direct messages to each client each time someone new connects
		// Repeat until connection is made (i.e. till input is given)
		size = server.getUserNames().size();
		System.out.println("**");
		System.out.println(size);
		while(running) {
			
			if(server.getUserNames().size() > size) {
				size +=1;
				writer.println("Online users: " + server.getUserNames());
				System.out.println("***");
			}
		}
	}
	
	public void setRunning(boolean x) {
		this.running = false;
	}
}
