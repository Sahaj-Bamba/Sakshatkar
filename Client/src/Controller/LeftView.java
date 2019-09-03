package Controller;

import Constant.Request;
import DataClasses.Client;
import RequestClasses.*;
import View.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

import static Main.Main.GAMER;

public class LeftView {

	private Object Gamer;

	@FXML
	private ListView listView;

	@FXML
	private TextField searchID;

	private ListView<String> chat;
	private ListView<String> call;
	private ListView<String> friends;
	private ListView<String> notification;


	public void initialize(){

	}

	public void searchIt(){
		System.out.println(searchID.getText());
	}

	public void chattedUsers(){

		try {
			GAMER.send_message(new GetConnectionChat(GAMER.get_name()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			GetConnectionChat response = (GetConnectionChat) GAMER.receive_message();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


		/*      Data Got now displaying     */

	}

		public void callUser()
		{
			try {
				GAMER.send_message(new CallUser(GAMER.get_name()));
			}catch (IOException e)
			{
				e.printStackTrace();
			}
			try {
				CallUser response = (CallUser) GAMER.receive_message();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

	public void searchFriends() {
		try {
			GAMER.send_message(new SearchFriends(GAMER.get_name()));
		}catch (IOException  e)
		{
			e.printStackTrace();
		}
		try {
			SearchFriends response = (SearchFriends) GAMER.receive_message();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	public void notification() {
		try {
			GAMER.send_message(new Notification(GAMER.get_name()));
		}catch (IOException e)
		{
			e.printStackTrace();
		}
		try {
			Notification response = (Notification) GAMER.receive_message();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void searchUser() {

		try {
			GAMER.send_message(new SearchUsers(searchID.getText()));
			Object response = GAMER.receive_message();

			if (response.equals(String.valueOf(Request.SEARCHUSERS))){
				ArrayList<Client> res;
				/*      Got the array list of clients in  now do stuff */

			}else{
				/*      No user found meeting the criteria       */

			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
