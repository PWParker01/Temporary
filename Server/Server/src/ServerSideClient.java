import java.net.*;
import java.util.*;
import java.io.*;

public class ServerSideClient implements IClient, Runnable{
	private IServer server;
	private boolean running;
	private BufferedReader in;
	private PrintWriter out;
	private Socket clientSocket;
	private String IP;

	public ServerSideClient(IServer server, Socket socket){
		try{
			this.server = server;
			clientSocket = socket;
			IP = clientSocket.getRemoteSocketAddress().toString();
			running = true;
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			out = new PrintWriter(clientSocket.getOutputStream(), true);
		}catch(Exception e){System.out.println("ServerSideClient | ERR: " + e.getStackTrace()[1].getLineNumber());}
	}

	public void send(String data){
		out.println(data);
		out.flush();
	}

	public void run(){
		try{
			String line = in.readLine();
			server.process(line);
			System.out.println(line);
		}catch(Exception e){System.out.println("ServerSideClient.run | ERR: " + e.getStackTrace()[1].getLineNumber());}
	}

	public void stop(){
		running = false;
	}

	public String getIP(){
		return IP;
	}

}
