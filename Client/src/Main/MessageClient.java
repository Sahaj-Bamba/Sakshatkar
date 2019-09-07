package Main;

import RequestClasses.Response;

import java.net.*;
import java.io.*;

public class MessageClient {

	private String ip;
	private int port;
	private Socket socket;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;

	public MessageClient(String ip, int port) {

		this.port = port;
		this.ip = ip;

		try {

			this.socket = new Socket(ip, port);
			this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			this.objectInputStream = new ObjectInputStream(socket.getInputStream());
			System.out.println("Message Client socket created");

		} catch (IOException e) {

		}

	}

	public void send_message(Object object) throws IOException {
		objectOutputStream.writeObject(object);
		objectOutputStream.flush();
	}

	public Object receive_message() throws IOException, ClassNotFoundException {
		return (Object) objectInputStream.readObject();
	}

}