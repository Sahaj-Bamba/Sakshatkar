package Main;

import Constant.Request;
import DataClasses.Call;
import DataClasses.Chat;
import DataClasses.Client;
import RequestClasses.*;
import javafx.util.Pair;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static Main.Main.SQLQUERYEXECUTER;


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

				System.out.println("HELLO @ TOP");
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
		}else if (req.equals(String.valueOf(Request.SEARCHUSER))){
			return _searchUser((SearchUser) message);
		}else if (req.equals(String.valueOf(Request.GETCHATS))){
			return _getChats((GetChats) message);
		}else if (req.equals(String.valueOf(Request.GETMUTUALFRIENDS))){
			return _getMutualFriends((GetMutualFriends) message);
		}else if(req.equals(String.valueOf(Request.ISONLINE))){
			return _isOnline((IsOnline) message);
		}





//		else if(req.equals(String.valueOf(Request.MESSAGE))){
//			return _message((Message) message);
//		}
		//This type of status needs handling


		return new Response(404,"Invalid Request");


	}

//	private Object _message(Message message) {
//		/*       Update table and add message to db                           */
//
//		if (((Response)(_isOnline(new IsOnline(message.getChat().getTo())))).getStatus() == 1)
//		{
//			/*      Send to the other user and store in db with status sent     */
//
//		}else{
//			/*          save in delayed state in db and try latter          */
//		}
//
//	}

	private Object _isOnline(IsOnline message) {
		String userID = message.getUserID();
		boolean isOnline = false;
		String lastOnline = null;
		try {
			ResultSet rs = SQLQUERYEXECUTER.select("SELECT isOnline, lastOnline FROM user WHERE userID = '" + userID + "';");
			while (rs.next()) {
				isOnline = rs.getBoolean("isOnline");
				lastOnline = rs.getString("lastOnline");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		if(isOnline == true){
			return new Response(1,"Active Now");
		}
		else {
			return new Response(0,lastOnline);
		}
	}

	private Object _getMutualFriends(GetMutualFriends message) {
		String userID1 = message.getUserID1();
		String userID2 = message.getUserID2();
		String firstUser = null;
		String secondUser = null;
		HashMap<String,Integer> friendsListofUser1 = new HashMap<>();
		HashMap<String,Integer> friendsListofUser2 = new HashMap<>();
		int mutualFriendsCount = 0;
		ResultSet rs = null;

		try {
			rs = SQLQUERYEXECUTER.select("SELECT * FROM connectiontable WHERE ((Status = 0) AND ((UserID1 = '"+userID1+"' AND UserID2 = '"+userID2+"') OR (UserID1 = '"+userID2+"' AND UserID2 = '"+userID1+"')));");
			while(rs.next()){
				mutualFriendsCount--;	//Subtracting the two friends before hand
			}
			System.out.println(mutualFriendsCount);
			rs = SQLQUERYEXECUTER.select("SELECT UserID1, UserID2 FROM connectiontable WHERE ((Status = 0) AND (UserID1 = '" + userID1 + "' OR UserID2 = '" + userID1 + "'));");
			while (rs.next()) {
				firstUser = rs.getString("UserID1");
				secondUser = rs.getString("UserID2");
				friendsListofUser1.put(((firstUser == userID1) ? secondUser : firstUser), 1);
			}
			System.out.println(friendsListofUser1.size());
			rs = SQLQUERYEXECUTER.select("SELECT UserID1, UserID2 FROM connectiontable WHERE ((Status = 0) AND (UserID1 = '" + userID2 + "' OR UserID2 = '" + userID2 + "'));");
			while (rs.next()) {
				firstUser = rs.getString("UserID1");
				secondUser = rs.getString("UserID2");
				friendsListofUser2.put(((firstUser == userID2) ? secondUser : firstUser), 1);
			}
			for(String X : friendsListofUser1.keySet()){
				if(friendsListofUser2.get(X) != null){
					mutualFriendsCount++;
				}
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return new GetMutualFriends(userID1, userID2, mutualFriendsCount);
	}

	private Object _getChats(GetChats message) {
		String userID1 = message.getUserID1();
		String userID2 = message.getUserID2();
		ArrayList<Chat> chats = new ArrayList<>();
		ResultSet rs = SQLQUERYEXECUTER.select("SELECT Sender, Receiver, Content, Type FROM messagetable WHERE TYPE IN (0,1) AND ((Sender = '"+userID1+"' AND Receiver = '"+userID2+"') OR (Sender = '"+userID2+"' AND Receiver = '"+userID1+"'));");
		try {
			while (rs.next()) {
				String senderUserID = null;
				senderUserID = rs.getString("Sender");
				String receiverUserID = rs.getString("Receiver");
				boolean shouldIntentLeft = (senderUserID == userID1);
				chats.add(new Chat(senderUserID, receiverUserID, 0, rs.getString("Content"), 0, rs.getInt("Type"), shouldIntentLeft));
			}
		}
		catch (SQLException e) {
				e.printStackTrace();
		}
		Collections.reverse(chats);
		return new GetChats(userID1, userID2, chats);
	}

	private Object _notification(Notification message) {
		String userID = message.getUserID();
		ResultSet rs = SQLQUERYEXECUTER.select("SELECT UserID1 FROM connectiontable WHERE Status = 1 AND UserID2 ='" + userID +"';");
		ArrayList<String> userIDArrayList = new ArrayList<>();
		ArrayList<Client> userDetails = new ArrayList<>();
			try {
				while(rs.next()){
					userIDArrayList.add(rs.getString("UserID1"));
				}
				for(String X : userIDArrayList) {
					rs = SQLQUERYEXECUTER.select("SELECT * FROM user WHERE userID = '" +X+ "';");
					while(rs.next()) {
						userDetails.add(new Client(rs.getString("name"), rs.getInt("isOnline"), rs.getString("lastOnline"), rs.getString("userID"), rs.getInt("status"), rs.getString("phoneNumber"), rs.getString("extension"), rs.getString("emailAddress")));
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

		ResultSet rs = SQLQUERYEXECUTER.select("SELECT Sender, Receiver, Type FROM messagetable WHERE Type IN (2,3) AND (Sender = '" + userID + "' OR Receiver = '" + userID + "');");
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
					rs = SQLQUERYEXECUTER.select("SELECT phoneNumber, name, lastOnline, isOnline, extension FROM user WHERE userID = '" + X.getKey() + "';");
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
			ResultSet rs = SQLQUERYEXECUTER.select("SELECT UserID2 from connectiontable WHERE userID1 = '" +userID+ "';");
			while (rs.next()) {
				friendsUserIDs.add(rs.getString("UserID2"));
			}

			rs = SQLQUERYEXECUTER.select("SELECT UserID1 from connectiontable WHERE userID2 = '" +userID+ "';");
			while (rs.next()) {
				friendsUserIDs.add(rs.getString("UserID1"));
			}

			System.out.println(userID);
			for (String X : friendsUserIDs) {
				rs = SQLQUERYEXECUTER.select("SELECT * FROM user WHERE userID = '" + X + "';");
				while(rs.next()){
					friendsDetails.add(new Client(rs.getString("name"), rs.getInt("isOnline"), rs.getString("lastOnline"), rs.getString("userID"), rs.getInt("status"), rs.getString("phoneNumber"), rs.getString("extension"), rs.getString("emailAddress")));
				}
			}
		}

		catch (SQLException e){
			e.printStackTrace();
		}

		return new SearchFriends(userID, friendsDetails);
	}


	private Object _register(RegisterData message) {

		SQLQUERYEXECUTER.update("INSERT INTO user VALUES ( '" + message.getLastOnline()+ "','" + message.getUserID()+ "','" +message.getPhone()+ "','" +message.getUserName()+ "','" + message.getPassword()+ "'," + "NULL" + "," + 0 + "," + 0 + ",'" + message.getEmailID() + ");");
		_login(new Login(message.getUserID(),message.getPassword()));
		return new Response(0,"");

	}

	private Object _login(Login login){

		boolean flag = false;
		ResultSet res = SQLQUERYEXECUTER.select("select name,password from user where USERID = '"+login.getUserID()+"' and password = '"+login.getPass()+"'");
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
		ResultSet res = SQLQUERYEXECUTER.select("select * from user where userID = '"+message.getUserID()+"'; ");
		try{
			if(res.next()){
				clients = new Client(res.getString("name"), res.getInt("isOnline"),res.getString("lastOnline"),res.getString("userID"),res.getInt("status"), res.getString("phoneNumber"), res.getString("extension"), res.getString("emailAddress"));
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
		ResultSet rs = SQLQUERYEXECUTER.select("SELECT userID FROM user WHERE userID = '"+ message.getUserID() + "'");
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

		SQLQUERYEXECUTER.update("update user set isonline = 1 where userid = '"+this.user.getUserID()+"' ;" );

		return new Response(0,"");

	}

	private void _online(){
//		UPDATE user set isonline = 1 where userId = "manas_uni";

		SQLQUERYEXECUTER.update("update user set isonline = 1 where userid = '"+this.user.getUserID()+"' ;" );

	}

	private void _offline(){
//		UPDATE user set isonline = 1 where userId = "manas_uni";

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String lastOnline = dtf.format(now);

		SQLQUERYEXECUTER.update("update user set isonline = 0 and lastonline = '"+lastOnline+"' where userid = '"+this.user.getUserID()+"' ;" );

	}

	public Object _setUser(SetUser message){

		user = message.getClient();
		_online();
		return new Response(0,"");

	}

	private Object _acceptRequest(AcceptRequest message) {

		SQLQUERYEXECUTER.update("update connectiontable set status = 0 where userid2 = '"+this.user+"' and userid1 = '"+message.getName()+"' ; ");

		return new Object();

	}

	private Object _friendRequest(FriendRequest message) {

		/*      Do insert query in connection of this.user and message.getName()                */
		/*      -- insert into connectiontable values('a','b')      */

		ResultSet rs = SQLQUERYEXECUTER.select("Select * from connectiontablel where UserID1 = '"+user.getUserID()+"' and UserID2 =  '"+message.getName()+"' ");

		try {
			if (rs.next()){
				return new Response(1,"Request already sent ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		rs = SQLQUERYEXECUTER.select("Select * from connectiontable where UserID2 = '"+user.getUserID()+"' and UserID1 =  '"+message.getName()+"' ");

		try {
			if (rs.next()){
				return new Response(2," User already Friend ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		SQLQUERYEXECUTER.update("insert into connectiontable values('"+user.getUserID()+"' , '"+message.getName()+"' , 1 )");

		/*          Check if that really exist  and return respective response 0 or 1;*/

		return new Response(0,"Success");

	}

	private Object _searchUser(SearchUser message) {

		System.out.println("Hello");
		ArrayList<Client> clients = new ArrayList<>();
		boolean flag;
		ResultSet res = SQLQUERYEXECUTER.select("select * from user where name like '%"+message.getName()+"%' or  userid like '%"+message.getName()+"%' ; ");
		try {
			while (res.next()) {
				clients.add(new Client(res.getString("name"), res.getInt("isOnline"), res.getString("lastOnline"), res.getString("userID"), res.getInt("status"), res.getString("phoneNumber"), res.getString("extension"), res.getString("emailAddress")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new SearchUser(message.getName(),clients);
	}



	private Object _friendsOnline(FriendsOnline message) {

		ArrayList<Client> clients = null;
		boolean flag;
//		ResultSet res = Main.SQLQUERYEXECUTER.select("select * from user where userID = '"+message.getName()+"'; ");
		ResultSet res = SQLQUERYEXECUTER.select("select * from user where userID in (select userid1 from connectiontable where userid2 = '"+message.getName()+"') or userID in ( select userid2 from connectiontable where userid2 = '"+message.getName()+"' ) ; ");
		try{
			while (res.next()){
				clients.add(new Client(res.getString("name"), res.getInt("isOnline"),res.getString("lastOnline"),res.getString("userID"),res.getInt("status"), res.getString("phoneNumber"), res.getString("extension"), res.getString("emailAddress")));
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
		ResultSet res = SQLQUERYEXECUTER.select("SELECT * FROM user");
		try{
			while (res.next()){
				clients.add(new Client(res.getString("name"), res.getInt("isOnline"),res.getString("lastOnline"),res.getString("userID"),res.getInt("status"), res.getString("phoneNumber"), res.getString("extension"), res.getString("emailAddress")));
			}
			return new GetConnectionChat("",clients);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Object();

	}

}