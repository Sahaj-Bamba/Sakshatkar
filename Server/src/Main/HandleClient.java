package Main;

import Constant.Request;
import RequestClasses.Login;
import RequestClasses.Response;

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
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}


	}

	private Object process(){

		boolean flag = false;
		String req = message.toString();

		if (req.equals(String.valueOf(Request.LOGIN))){

			Login login = (Login)message;

			ResultSet res = Main.SQLQueryExecuter.select("select name,password from user where name = '"+login.getName()+"' and password = '"+login.getPass()+"'");

			try{

				flag = false;
				while(res.next()){
					if (res.getString("name").equals(login.getPass())){
						flag = true;
					}
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

		return new Object();


	}


}
