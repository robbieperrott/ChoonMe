import java.io.*;
import java.net.*;
import java.util.*;

/**
 * This thread handles connection for each connected client, so the server
 * can handle multiple clients at the same time.
 *
 * @author www.codejava.net
 */
public class UserThread extends Thread {
	
	private Socket socket;
	private ChatServer server;
	private PrintWriter writer;
	private String userName;
	String targetUserName = "refresh";
	boolean done = false;
	private int currentSize;
	private String clientMessage;

	public UserThread(Socket socket, ChatServer server, int currentSize) {
		this.socket = socket;
		this.server = server;
		this.currentSize = currentSize;
	}

	public void run() {
		try {
			
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));

			OutputStream output = socket.getOutputStream();
			writer = new PrintWriter(output, true);

			// Get userName
			String userName = reader.readLine();
			this.userName = userName;
			server.addUserName(userName);
			
			// Refresh list of online users
			while(targetUserName.equals("refresh")) {
				if(ChatServer.size > currentSize) {
					currentSize +=1;
					writer.println("\nOnline users: " + server.getUserNames());
					targetUserName = reader.readLine();
				}
			}
			
			// Request to chat
			server.directMessage("\nHi " + targetUserName + ", " + userName + " would like to chat."
					+ "\nType \"" + userName + "\" to accept.", targetUserName);
			 
			// Chat
			do {
				clientMessage = "[" + userName + "]: " + reader.readLine();
				server.directMessage(clientMessage, targetUserName);

			} while (!clientMessage.equals("bye"));

			server.removeUser(userName, this);
			socket.close();
			
			//System.out.println(targetUserName + " does not want to chat :(");
			

		} catch (IOException ex) {
			System.out.println("Error in UserThread: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * Sends a list of online users to the newly connected user.
	 */
	void printUsers() {
		if (server.hasUsers()) {
			writer.println("Online users: " + server.getUserNames());
		} else {
			writer.println("No online users. Please wait...");
		}
	}

	/**
	 * Sends a message to the client.
	 */
	void sendMessage(String message) {
		writer.println(message);
	}
	
	String getUserName() {
		return userName;
	}
}