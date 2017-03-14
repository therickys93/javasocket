package it.therickys93.javasocket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Connection {

	private String host;
	private int port;
	private String response;
	
	public Connection(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public String host() {
		return this.host;
	}

	public int port() {
		return this.port;
	}
	
	protected Socket createSocket() throws Exception{
		return new Socket(InetAddress.getByName(this.host), this.port);
	}

	public boolean send(byte[] emptyPayload) {
		boolean ok = false;
		
		try {
			int dim = 1024;
			byte[] buffer = new byte[dim];
			Socket socket = createSocket();
			OutputStream out = socket.getOutputStream();
			out.write(emptyPayload);
			InputStream input = socket.getInputStream();
			int readed = input.read(buffer);
			
			this.response = new String(buffer, 0, readed);
			
			socket.close();
			ok = true;
		} catch(Exception e){
			ok = false;
		}
		
		return ok;
	}

	public String response() {
		return this.response;
	}

}
