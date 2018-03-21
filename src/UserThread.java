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

	public UserThread(Socket socket, ChatServer server) {
		this.socket = socket;
		this.server = server;
	}

	public void run() {
		try {
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));

			OutputStream output = socket.getOutputStream();
			writer = new PrintWriter(output, true);

			String userName = reader.readLine();
			this.userName = userName;
			server.addUserName(userName);
			
			printUsers();
			
			
			//Perro
			String targetUserName = reader.readLine();
			//server.addTargetUserName(targetUserName);
			server.addChatPair(userName, targetUserName);

			String serverMessage = "New user connected: " + userName;
			server.broadcast(serverMessage, this);

			String clientMessage;
			
			System.out.println("User: " + userName + " Target: " + targetUserName);
			
			do {
				clientMessage = reader.readLine();
				//serverMessage = "[" + userName + "]: " + clientMessage;
				// Send message here
				server.directMessage(clientMessage, targetUserName);

			} while (!clientMessage.equals("bye"));

			server.removeUser(userName, this);
			socket.close();

			serverMessage = userName + " has quitted.";
			server.broadcast(serverMessage, this);

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
			writer.println("Select a user to chat to: " + server.getUserNames());
		} else {
			writer.println("No other users connected. Please wait until another user comes online...");
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