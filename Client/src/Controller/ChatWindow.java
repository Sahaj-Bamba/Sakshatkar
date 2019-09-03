package Controller;

import Constant.Request;
import DataClasses.Client;
import RequestClasses.FriendRequest;
import RequestClasses.Response;
import Windows.AlertBox;

import java.io.IOException;

import static Main.Main.GAMER;

public class ChatWindow {

	private String user;

	public void initialize(){

	}


	public void setUser(String user){
		this.user = user;
		/*     Set  user name  to the user label      */
	}


	public void friendRequest(){

		try {
			GAMER.send_message(new FriendRequest(this.user));
			Response res = (Response) GAMER.receive_message();

			if (res.getStatus()==0){

				/*    Connection created  */
				new AlertBox("SUCCESS","Friend Request has been successfully sent. ");
				return;
			}else{

				/*      Some Problem    */
				new AlertBox("FAILURE","There was some problem with the server. Please try again latter ");
				return;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}


}
