package Message.Main;

import Constant.Request;
import DataClasses.Client;
import Main.Main;
import RequestClasses.*;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class HandleClient implements Runnable{

	private Socket socket;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	private Object message;
	private String user;

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
		}else if (req.equals(String.valueOf(Request.USERID))){
			return _userID((UserID) message);
		}else if (req.equals(String.valueOf(Request.REGISTER))){
			return _register((RegisterData) message);
		}else if (req.equals(String.valueOf(Request.SEARCHUSERS))){
			return _searchUsers((SearchUsers) message);
		}else if (req.equals(String.valueOf(Request.PROFILE))){
			return _profile((Profile) message);
		}else if (req.equals(String.valueOf(Request.FRIENDREQUEST))){
			return _friendRequest((FriendRequest) message);
		}else if (req.equals(String.valueOf(Request.ACCEPTREQUEST))){
			return _acceptRequest((AcceptRequest) message);
		}

		return new Response(404,"Invalid Request");

	}

	private Object _acceptRequest(AcceptRequest message) {

//		update connectiontable
//		set status = 1
//		where userid1 = 'c'

		Main.SQLQUERYEXECUTER.update("update connectiontable set status = 1 where userid2 = '"+this.user+"' and userid1 = '"+message.getName()+"' ; ");

		return new Object();

	}

	private Object _friendRequest(FriendRequest message) {

		/*      Do insert query in connection of this.user and message.getName()                */
		/*      -- insert into connectiontable values('a','b')      */

		Main.SQLQUERYEXECUTER.update("insert into connectiontable values('"+user+"' , '"+message.getName()+"' , 0 )");

		/*          Check if that really exist  and return respective response 0 or 1;*/

		return new Object();

	}

	private Object _searchUsers(SearchUsers message) {

		ArrayList<Client> clients = null;
		boolean flag;
		ResultSet res = Main.SQLQUERYEXECUTER.select("select * from user where name like '%"+message.getName()+"%' or  userid like '%"+message.getName()+"%' ; ");
		try{
			while (res.next()){
				clients.add(new Client(res.getString("name"), res.getInt("isonline"),res.getTimestamp("lastonline"),res.getString("userid"),res.getInt("status")));
			}
			return new SearchUsers("",clients);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (clients == null){
			return new Response(1,"No user match the given criteria");
		}
		return new Object();

	}


	private Object _register(RegisterData message) {

		Boolean doesFileExist = new File("/src/ProfilePictures").mkdir();
//		String fileName = "src/ProfilePictures/"+message.getUserName();
//		SavingImage savingImage = new SavingImage(fileName, message.getExtension(), message.getImage());
		String savedImageLocation = "";
		Main.SQLQUERYEXECUTER.update("INSERT INTO user VALUES ( '" + message.getLastOnline()+ "','" + message.getUserID()+ "','" +message.getPhone()+ "','" +message.getUserName()+ "','" + message.getPassword()+ "','" +savedImageLocation + "'," + 0 + "," + 0 + ");");
		return new Response(0,"");

	}


	private Object _userID(UserID message) {
		ResultSet rs = Main.SQLQUERYEXECUTER.select("SELECT userID FROM user WHERE userID = '"+ message.getUserID() + "'");
		try {
			if(rs.next()){
				return new Response(1,"User ID already exists");
			}
			else{
				return new Response(0,"");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Response(500,"Internal RequestServer Error");
	}


	private Object _friendsOnline(FriendsOnline message) {

		ArrayList<Client> clients = null;
		boolean flag;
		ResultSet res = Main.SQLQUERYEXECUTER.select("select * from user where userID in (select userid1 from connectiontable where userid2 = '"+message.getName()+"') or userID in ( select userid2 from connectiontable where userid2 = '"+message.getName()+"' ) ; ");
		try{
			while (res.next()){
				clients.add(new Client(res.getString("name"), res.getInt("isonline"),res.getTimestamp("lastonline"),res.getString("userid"),res.getInt("status")));
			}
			return new GetConnectionChat("",clients);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Object();

	}


	private Object _profile(Profile message) {

		Client clients = null;
		boolean flag;
		ResultSet res = Main.SQLQUERYEXECUTER.select("select * from user where userID = '"+message.getName()+"'; ");
		try{
			if (res.next()){
				clients = (new Client(res.getString("name"), res.getInt("isonline"),res.getTimestamp("lastonline"),res.getString("userid"),res.getInt("status")));
				return new Profile("",clients);
			}else {
				return new Response(1,"User not found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Object();

	}


	private Object _getConnectionChat(GetConnectionChat message) {

		ArrayList<Client> clients = null;
		boolean flag;
		ResultSet res = Main.SQLQUERYEXECUTER.select("select * from user where userID in (select Reciever from messagetable where Sender = '"+message.getName()+"') or userID in ( select Sender from messagetable where reciever = '"+message.getName()+"' ) ; ");
		try{
			while (res.next()){
				clients.add(new Client(res.getString("name"), res.getInt("isonline"),res.getTimestamp("lastonline"),res.getString("userid"),res.getInt("status")));
			}
			return new GetConnectionChat("",clients);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Object();

	}


	private Object _login(Login login){

		boolean flag = false;
		ResultSet res = Main.SQLQUERYEXECUTER.select("select name,password from user where USERID = '"+login.getName()+"' and password = '"+login.getPass()+"'");
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