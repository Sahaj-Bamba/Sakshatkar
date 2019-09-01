package Main;

import Constant.Request;
import DataClasses.Client;
import RequestClasses.*;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class HandleClient implements Runnable{

	private Socket socket;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	private Object message;

	public HandleClient(Socket socket) {
		this.socket = socket;
		try {
			objectInputStream = new ObjectInputStream(socket.getInputStream());
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
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

				/*      Do processing with esult */

				objectOutputStream.writeObject(response);
				objectOutputStream.flush();

				System.out.println("Response sent");

			} catch (IOException e) {
				System.out.println("Client Disconnected");
				break;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}


	}

	private Object process(){

		boolean flag = false;
		String req = message.toString();

		if (req.equals(String.valueOf(Request.LOGIN))){
			return  _login((Login) message);
		}else if (req.equals(String.valueOf(Request.GETCONNECTIONSCHAT))){
			return _getConnectionChat((GetConnectionChat) message);
		}else if (req.equals(String.valueOf(Request.FRIENDSONLINE))){
			return _friendsOnline((FriendsOnline) message);
		}else if (req.equals(String.valueOf(Request.PROFILE))){
			return _profile((Profile) message);
		}

		return new Response(404,"Invalid Request");

	}

	private Object _friendsOnline(FriendsOnline message) {

		ArrayList<Client> clients = null;
		boolean flag;
		ResultSet res = Main.SQLQueryExecuter.select("select * from user where userID in (select userid1 from connectiontable where userid2 = '"+message.getName()+"') or userID in ( select userid2 from connectiontable where userid2 = '"+message.getName()+"' ) ; ");
		try{
			while (res.next()){
				File file = new File(res.getString("pic"));
				clients.add(new Client(res.getString("name"), res.getInt("isonline"), new Image(file.toURI().toURL().toString()),res.getTimestamp("lastonline"),res.getString("userid"),res.getInt("status")));
			}
			return new GetConnectionChat("",clients);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return new Object();

	}


	private Object _profile(Profile message) {

		Client clients = null;
		boolean flag;
		ResultSet res = Main.SQLQueryExecuter.select("select * from user where userID = '"+message.getName()+"'; ");
		try{
			if (res.next()){
				File file = new File(res.getString("pic"));
				clients = (new Client(res.getString("name"), res.getInt("isonline"), new Image(file.toURI().toURL().toString()),res.getTimestamp("lastonline"),res.getString("userid"),res.getInt("status")));
				return new Profile("",clients);
			}else {
				return new Response(404,"User not found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return new Object();

	}


	private Object _getConnectionChat(GetConnectionChat message) {

		ArrayList<Client> clients = null;
		boolean flag;
		ResultSet res = Main.SQLQueryExecuter.select("select * from user where userID in (select Reciever from messagetable where Sender = '"+message.getName()+"') or userID in ( select Sender from messagetable where reciever = '"+message.getName()+"' ) ; ");
		try{
			while (res.next()){
				File file = new File(res.getString("pic"));
				clients.add(new Client(res.getString("name"), res.getInt("isonline"), new Image(file.toURI().toURL().toString()),res.getTimestamp("lastonline"),res.getString("userid"),res.getInt("status")));
			}
			return new GetConnectionChat("",clients);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return new Object();

	}


	private Object _login(Login login){

		boolean flag = false;
		ResultSet res = Main.SQLQueryExecuter.select("select name,password from user where name = '"+login.getName()+"' and password = '"+login.getPass()+"'");
		try{

			flag = false;
			if(res.next()){
				flag = true;
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}

		if (flag){
			return (new Response(0,""));
		}else {
			return (new Response(1,"Invalid username password combination"));
		}

	}

}