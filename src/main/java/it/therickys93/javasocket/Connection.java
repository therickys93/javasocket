package it.therickys93.javasocket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * this class create a socket connection
 * @author therickys93
 *
 */
public class Connection {

	private String host;
	private int port;
	private String response;
	
	/**
	 * construct of the connection 
	 * @param host the host that you want to connect
	 * @param port the port of the host
	 */
	public Connection(String host, int port) {
		this.host = host;
		this.port = port;
	}

	/**
	 * return the host
	 * @return the host
	 */
	public String host() {
		return this.host;
	}

	/**
	 * return the port
	 * @return the port
	 */
	public int port() {
		return this.port;
	}
	
	protected Socket createSocket() throws Exception{
		return new Socket(InetAddress.getByName(this.host), this.port);
	}

	/**
	 * send the array of bytes given as argument and return true or false is something goes wrong
	 * @param emptyPayload the array of bytes
	 * @return the success or the failure of the send
	 */
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

	/**
	 * return the response as a string
	 * @return the response
	 */
	public String response() {
		return this.response;
	}

}
