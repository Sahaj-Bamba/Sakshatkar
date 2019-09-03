package Controller;

import Constant.Request;
import DataClasses.Client;
import RequestClasses.FriendRequest;

import java.io.IOException;

import static Main.Main.GAMER;

public class Profile {

	private String user;


	public void initialize(){

	}

	public void update(String name){

		if (name == this.user){
			return;
		}

		try {
			GAMER.send_message(new RequestClasses.Profile(name));
			Object res = GAMER.receive_message();

			if (res.equals(String.valueOf(Request.PROFILE))){
				Client user = ((RequestClasses.Profile) res).getClient();

				/*    got client data in user now do stuff  */

			}else{

				/*      no client found with given id    */

			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}


	public void friendRequest(){

		try {
			GAMER.send_message(new FriendRequest(this.user));
			Object res = GAMER.receive_message();

			if (res.equals(String.valueOf(Request.PROFILE))){
				Client user = ((RequestClasses.Profile) res).getClient();

				/*    got client data in user now do stuff  */

			}else{

				/*      no client found with given id    */

			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}


}
