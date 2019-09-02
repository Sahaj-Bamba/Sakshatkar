package Main;

import Constant.Request;
import RequestClasses.Login;
import RequestClasses.RegisterData;
import RequestClasses.Response;
import RequestClasses.UserID;
import Utilities.SavingImage;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;


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
				System.out.println("Client Disconnected");
				break;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}


	}

	private Object process() throws SQLException {

		boolean flag = false;
		String req = message.toString();

		if (req.equals(String.valueOf(Request.LOGIN))){

			Login login = (Login)message;

			ResultSet res = Main.SQLQUERYEXECUTER.select("select name,password from user where name = '"+login.getName()+"' and password = '"+login.getPass()+"'");

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

		else if(req.equals(Request.USERID.toString())){
			UserID userID = (UserID)message;
			ResultSet rs = Main.SQLQUERYEXECUTER.select("SELECT userID FROM user WHERE userID = '"+ userID.getUserID() + "'");
			if(rs.next()){
				return new Response(1,"User ID already exists");
			}
			else{
				return new Response(0,"");
			}
		}

		else if(req.equals(Request.REGISTER.toString())){
			RegisterData registerData = (RegisterData)message;

			Boolean doesFileExist = new File("/src/ProfilePictures").mkdir();
			String fileName = "src/ProfilePictures/"+registerData.getUserName();
			SavingImage savingImage = new SavingImage(fileName, registerData.getExtension(), registerData.getImage());
//			String savedImageLocation = savingImage.save();
			String savedImageLocation = "";
			Main.SQLQUERYEXECUTER.update("INSERT INTO user VALUES ( '" + registerData.getLastOnline()+ "','" + registerData.getUserID()+ "','" +registerData.getPhone()+ "','" +registerData.getUserName()+ "','" + registerData.getPassword()+ "','" +savedImageLocation + "'," + 0 + "," + 0 + ");");
			return new Response(0,"");
		}

		return new Object();
		}

}
