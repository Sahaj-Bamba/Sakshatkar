package Main;

import RequestClasses.Response;

import java.net.*;
import java.io.*;

public class MessageClient {

	private String ip;
	private int port;
	private String name;
	private Socket socket;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;

	public MessageClient(String ip, int port, String name) {

		this.port = port;
		this.ip = ip;
		this.name = name;

		try {
			this.socket = new Socket(ip, port);
			System.out.println("Client File socket created");
			this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("Data output stream created");
			this.objectInputStream = new ObjectInputStream(socket.getInputStream());
			System.out.println("Object input stream created");

			System.out.println("Client socket created");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void send_message(Object object) throws IOException {
		objectOutputStream.writeObject(object);
		objectOutputStream.flush();
	}

	public Object receive_message() throws IOException, ClassNotFoundException {
		return (Object) objectInputStream.readObject();
	}

	public String get_name() {
		return name;
	}

}