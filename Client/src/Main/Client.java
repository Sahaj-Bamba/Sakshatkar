package Main;

import java.io.*;
import java.net.Socket;

public class Client implements Serializable {

	private Socket socket;
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;
	private String name;

	public Client(String ip, int port, String name) {
		try {
			this.name = name;
			this.socket = new Socket(ip, port);
			System.out.println(name);
			this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			System.out.println(name);
			this.objectInputStream = new ObjectInputStream(socket.getInputStream());
			System.out.println("Input stream created");

			System.out.println("Client created.");

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