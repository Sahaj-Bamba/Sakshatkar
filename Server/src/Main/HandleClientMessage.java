package Main;

import Constant.Request;
import DataClasses.Chat;
import DataClasses.Client;
import RequestClasses.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class HandleClientMessage implements Runnable{

	private Socket socket;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	private Object message;
	private String user;

	public HandleClientMessage(Socket socket) {
		this.socket = socket;
		try {
			objectInputStream = new ObjectInputStream(socket.getInputStream());
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Both streams created for HandleClient");
	}

	/**
	 * When an object implementing interface <code>Runnable</code> is used
	 * to create a thread, starting the thread causes the object's
	 * <code>run</code> method to be called in that separately executing
	 * thread.
	 * <p>
	 * The general contract of the method <code>run</code> is that it may
	 * take any action whatsoever.
	 *
	 * @see Thread#run()
	 */

	@Override
	public void run() {

		boolean flag;

		while (true){

			try {
				message = (Object) objectInputStream.readObject();


				/*      Do processing       */

				Object response = process();

				/*      Do processing with result */

				objectOutputStream.writeObject(response);
				objectOutputStream.flush();

				System.out.println("Response sent");

			} catch (IOException e) {
				System.out.println("Client Message Disconnected");
				Main.MESSAGESENDER.removeUser(user);
				break;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}


	}

	private Object process(){

		boolean flag = false;
		String req = message.toString();
//		System.out.println("dieeeeeee");
		if (message instanceof Chat) {
//			System.out.println(((Chat)(message)).toString());
			return _chatProcess((Chat) message);
		}else if (message instanceof MessageHi) {
//			Main.MESSAGESENDER.addUser(((MessageHi)(message)).getId(),this.objectOutputStream);
			this.user = ((MessageHi)(message)).getId();
			System.out.println("@@@@@"+this.user);
			Main.MESSAGESENDER.addUser(user,this.objectOutputStream);
			return message;
		}

		return new Object();
	}

	private Object _chatProcess(Chat message) {

		int x;

		if(Main.MESSAGESENDER.send(message.getTo(),message)){
			/*      store in db with status sent     */
			x=2;
		}else{
			/*          save in delayed state in db and try latter          */
			x=1;
		}

		Main.SQLQUERYEXECUTER.update("insert into messagetable Values ('"+message.getTo()+"' , '"+message.getFrom()+"' , "+message.getType()+" , '"+message.getContent()+"' , '"+1+"' , "+message.getLevel()+" , "+x+"  )");

		/*       Update table and add message to db                           */

		/*                  Need working for group part                         */

		return new Response(0,"Message successfully sent");

	}


}