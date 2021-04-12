package server.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketStream {
	private InputStream input = null;
	private BufferedReader reader = null;
	private OutputStream out = null;
	private PrintWriter writer = null;

	public SocketStream(Socket socket) throws IOException {
		input = socket.getInputStream();
		reader = new BufferedReader(new InputStreamReader(input));
		out = socket.getOutputStream();
		writer = new PrintWriter(out, true);
	}

	public void SocketSend(String message) throws IOException {
		//System.out.println("[SocketStream] ===> socketSend : " + message);
		writer.println(message);
	}

	public String getStringRead() throws IOException {
		return reader.readLine();
	}
	
	public int checkReadLength() throws IOException {
		return input.available();
	}

	public void close() {
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		writer.close();
		
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
