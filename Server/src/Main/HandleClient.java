package Main;

import Constant.Request;
import DataClasses.Call;
import DataClasses.Client;
import RequestClasses.*;
import javafx.util.Pair;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;


public class HandleClient implements Runnable{

	private Socket socket;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	private Object message;
	private Client user;

	public HandleClient(Socket socket) {
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

		while (true){

			try {

				message = (Object) objectInputStream.readObject();

				/*      Do processing       */

				System.out.println("Response received");
				Object response = process();

				/*      Do processing with result */

				objectOutputStream.writeObject(response);
				objectOutputStream.flush();

				System.out.println("Response sent");

			} catch (IOException e) {
				System.out.println("Client Disconnected");
				_offline();
				break;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}


	}

	private Object process(){

		String req = message.toString();

		if (req.equals(String.valueOf(Request.LOGIN))){
			return  _login((Login) message);
		}else if (req.equals(String.valueOf(Request.GETCONNECTIONSCHAT))){
			return _getConnectionChat((GetConnectionChat) message);
		}else if (req.equals(String.valueOf(Request.FRIENDSONLINE))){
			return _friendsOnline((FriendsOnline) message);
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
		}else if (req.equals(String.valueOf(Request.ONLINE))){
			return _online((Online) message);
		}else if (req.equals(String.valueOf(Request.SETUSER))){
			return _setUser((SetUser) message);
		}else if (req.equals(String.valueOf(Request.SEARCHFRIENDS))){
			return _searchFriends((SearchFriends) message);
		}else if (req.equals(String.valueOf(Request.CALLDETAILS))){
			return _callDetails((CallDetails) message);
		}else if (req.equals(String.valueOf(Request.NOTIFICATION))){
			return _notification((Notification) message);
		}

		//This type of status needs handling
		return new Response(404,"Invalid Request");


	}

	private Object _notification(Notification message) {
		String userID = message.getUserID();
		ResultSet rs = Main.SQLQUERYEXECUTER.select("SELECT userID1, userID2 FROM connectiontable WHERE (Status = 1 AND (UserID1 = '"+userID+"' OR UserID2 = '"+userID+"'));");
		ArrayList<String> userIDArrayList = new ArrayList<>();
		ArrayList<Client> userDetails = new ArrayList<>();
			try {
				while(rs.next()){
					String userID1 = rs.getString("userID1");
					String userID2 = rs.getString("userID2");
					userIDArrayList.add(userID.equals(userID1)?userID2:userID1);
				}
				for(String X : userIDArrayList) {
					rs = Main.SQLQUERYEXECUTER.select("SELECT * FROM user WHERE userID = '" +X+ "';");
					while(rs.next()) {
						userDetails.add(new Client(rs.getString("name"), rs.getInt("isOnline"), rs.getString("lastOnline"), rs.getString("userID"), rs.getInt("status"), rs.getString("phoneNumber"), rs.getString("extension")));
					}
				}
			}
		catch (SQLException e) {
				e.printStackTrace();
			}
		Collections.reverse(userDetails);
		return new Notification(userID,userDetails);
	}

	private Object _callDetails(CallDetails message){

		String userID = message.getUserID();
		ArrayList<Call> callInformation = new ArrayList<>();
		ArrayList<Pair<String,Integer>> userInformation = new ArrayList<>();

		ResultSet rs = Main.SQLQUERYEXECUTER.select("SELECT Sender, Receiver, Type FROM messagetable WHERE Type IN (2,3) AND (Sender = '" + userID + "' OR Receiver = '" + userID + "');");
			try {
				while (rs.next()) {
					String fromUserID = rs.getString("Sender");
					String toUserID = rs.getString("Receiver");
					int type = rs.getInt("Type");
					int isCallReceivedCalledMissed = 0;
					String userIDToFetchTheDataFor = null;

					//All outgoing calls
					if (fromUserID.equals(userID) == true) {
						isCallReceivedCalledMissed = Call.callTypeEnum.OUTGOING.getValue();
						userIDToFetchTheDataFor = toUserID;
					} else if (toUserID.equals(userID) == true) {
						//handling incoming calls
						userIDToFetchTheDataFor = fromUserID;
						if (type == 2) {
							isCallReceivedCalledMissed = Call.callTypeEnum.INCOMING.getValue();
						} else if (type == 3) {
							isCallReceivedCalledMissed = Call.callTypeEnum.MISSED.getValue();
						}
					}
					userInformation.add(new Pair(userIDToFetchTheDataFor, isCallReceivedCalledMissed));
				}
				for(Pair<String,Integer> X: userInformation) {
					rs = Main.SQLQUERYEXECUTER.select("SELECT phoneNumber, name, lastOnline, isOnline, extension FROM user WHERE userID = '" + X.getKey() + "';");
					while (rs.next()) {
						callInformation.add(new Call(X.getKey(), rs.getString("name"), rs.getString("phoneNumber"), rs.getString("lastOnline"), rs.getString("isOnline"), X.getValue(), rs.getString("extension")));
					}
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return new CallDetails(userID, callInformation);
	}

	private Object _searchFriends(SearchFriends message) {

		System.out.println("Into searchFriends");
		ArrayList<String> friendsUserIDs = new ArrayList<String>();
		ArrayList<Client> friendsDetails = new ArrayList<Client>();
		String userID = message.getUserID();

		try {
			ResultSet rs = Main.SQLQUERYEXECUTER.select("SELECT UserID2 from connectiontable WHERE userID1 = '" +userID+ "';");
			while (rs.next()) {
				friendsUserIDs.add(rs.getString("UserID2"));
			}

			rs = Main.SQLQUERYEXECUTER.select("SELECT UserID1 from connectiontable WHERE userID2 = '" +userID+ "';");
			while (rs.next()) {
				friendsUserIDs.add(rs.getString("UserID1"));
			}

			System.out.println(userID);
			for (String X : friendsUserIDs) {
				rs = Main.SQLQUERYEXECUTER.select("SELECT * FROM user WHERE userID = '" + X + "';");
				while(rs.next()){
					friendsDetails.add(new Client(rs.getString("name"), rs.getInt("isOnline"), rs.getString("lastOnline"), rs.getString("userID"), rs.getInt("status"), rs.getString("phoneNumber"), rs.getString("extension")));
				}
			}
		}

		catch (SQLException e){
			e.printStackTrace();
		}

		return new SearchFriends(userID, friendsDetails);
	}


	private Object _register(RegisterData message) {

		Main.SQLQUERYEXECUTER.update("INSERT INTO user VALUES ( '" + message.getLastOnline()+ "','" + message.getUserID()+ "','" +message.getPhone()+ "','" +message.getUserName()+ "','" + message.getPassword()+ "'," + "NULL" + "," + 0 + "," + 0 + ");");
		_login(new Login(message.getUserID(),message.getPassword()));
		return new Response(0,"");

	}

	private Object _login(Login login){

		boolean flag = false;
		ResultSet res = Main.SQLQUERYEXECUTER.select("select name,password from user where USERID = '"+login.getUserID()+"' and password = '"+login.getPass()+"'");
		try{

			flag = false;
			if(res.next()){
				flag = true;
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}

		if (flag){
			user = ((Profile)_profile(new Profile(login.getUserID()))).getClient();
			_online();
			return (new Response(0,""));
		}else {
			return (new Response(1,"Invalid username password combination"));
		}

	}

	private Object _profile(Profile message) {

		Client clients = null;
		boolean flag;
		ResultSet res = Main.SQLQUERYEXECUTER.select("select * from user where userID = '"+message.getUserID()+"'; ");
		try{
			if(res.next()){
				clients = new Client(res.getString("name"), res.getInt("isOnline"),res.getString("lastOnline"),res.getString("userID"),res.getInt("status"), res.getString("phoneNumber"), res.getString("extension"));
				return new Profile(clients);
			}else {
				return new Response(1,"User not found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Object();

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

	private Object _online(Online message){
//		UPDATE user set isonline = 1 where userId = "manas_uni";

		Main.SQLQUERYEXECUTER.update("update user set isonline = 1 where userid = '"+this.user.getUserID()+"' ;" );

		return new Response(0,"");

	}

	private void _online(){
//		UPDATE user set isonline = 1 where userId = "manas_uni";

		Main.SQLQUERYEXECUTER.update("update user set isonline = 1 where userid = '"+this.user.getUserID()+"' ;" );

	}

	private void _offline(){
//		UPDATE user set isonline = 1 where userId = "manas_uni";

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String lastOnline = dtf.format(now);

		Main.SQLQUERYEXECUTER.update("update user set isonline = 0 and lastonline = '"+lastOnline+"' where userid = '"+this.user.getUserID()+"' ;" );

	}

	public Object _setUser(SetUser message){

		user = message.getClient();
		_online();
		return new Response(0,"");

	}

	private Object _acceptRequest(AcceptRequest message) {

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
				clients.add(new Client(res.getString("name"), res.getInt("isOnline"),res.getString("lastOnline"),res.getString("userID"),res.getInt("status"), res.getString("phoneNumber"), res.getString("extension")));
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



	private Object _friendsOnline(FriendsOnline message) {

		ArrayList<Client> clients = null;
		boolean flag;
//		ResultSet res = Main.SQLQUERYEXECUTER.select("select * from user where userID = '"+message.getName()+"'; ");
		ResultSet res = Main.SQLQUERYEXECUTER.select("select * from user where userID in (select userid1 from connectiontable where userid2 = '"+message.getName()+"') or userID in ( select userid2 from connectiontable where userid2 = '"+message.getName()+"' ) ; ");
		try{
			while (res.next()){
				clients.add(new Client(res.getString("name"), res.getInt("isOnline"),res.getString("lastOnline"),res.getString("userID"),res.getInt("status"), res.getString("phoneNumber"), res.getString("extension")));
			}
			return new GetConnectionChat("",clients);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Object();

	}

	private Object _getConnectionChat(GetConnectionChat message) {


		System.out.println("Hello");
		ArrayList<Client> clients = new ArrayList<Client>();
		System.out.println("SENDING MESSAGE");
//		ResultSet res = Main.SQLQUERYEXECUTER.select("select * from user where userID in (select Reciever from messagetable where Sender = '"+message.getName()+"') or userID in ( select Sender from messagetable where reciever = '"+message.getName()+"' ) ; ");
		ResultSet res = Main.SQLQUERYEXECUTER.select("SELECT * FROM user");
		try{
			while (res.next()){
				clients.add(new Client(res.getString("name"), res.getInt("isOnline"),res.getString("lastOnline"),res.getString("userID"),res.getInt("status"), res.getString("phoneNumber"), res.getString("extension")));
			}
			return new GetConnectionChat("",clients);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Object();

	}

}