package it.therickys93.javasocket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class SocketTest {

	@Test
	public void testOne() {		
		Connection connection = new Connection("host", 9090);
		assertEquals("host", connection.host());
		assertEquals(9090, connection.port());
	}
	
	@Test
	public void testTwo() throws IOException {
		byte[] emptyPayload = new byte[1024];
		
		final Socket socket = mock(Socket.class);
		final ByteArrayOutputStream output = new ByteArrayOutputStream();
		when(socket.getOutputStream()).thenReturn(output);
		final ByteArrayInputStream input = new ByteArrayInputStream("hello".getBytes());
		when(socket.getInputStream()).thenReturn(input);

		Connection connection = new Connection("host", 9090){
			@Override
			protected Socket createSocket() {
				return socket;
			}
		};
		assertTrue(connection.send(emptyPayload));
	}
	
	@Test
	public void testThree() throws IOException {
		final Socket socket = mock(Socket.class);
		final ByteArrayOutputStream output = new ByteArrayOutputStream();
		when(socket.getOutputStream()).thenReturn(output);
		final ByteArrayInputStream input = new ByteArrayInputStream("hello".getBytes());
		when(socket.getInputStream()).thenReturn(input);
		
		Connection connection = new Connection("host", -1){
			@Override
			protected Socket createSocket() {
				return socket;
			}
		};
		assertTrue(connection.send("hello".getBytes()));
		assertEquals("hello", connection.response());
	}
	
	@Test
	public void testFour() {
		Connection connection = new Connection("localhost", 9090);
		assertFalse(connection.send("hello".getBytes()));
	}
	
}
