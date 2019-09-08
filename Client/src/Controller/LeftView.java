package Controller;

import Constant.Request;
import DataClasses.Client;
import RequestClasses.*;
import Utilities.GetListView;
import View.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;

import static Main.Main.*;

public class LeftView {

	private Object Gamer;

	@FXML
	private TextField searchID;

	@FXML
	AnchorPane anchorPane;

	public void initialize(){

	}

	public void searchIt(){
		System.out.println(searchID.getText());
	}

	public void chattedUsers() {

		try {
			GAMER.send_message(new GetConnectionChat(USER.getName()));
			System.out.println("MESSAGE SENT");
		} catch (IOException e) {
			e.printStackTrace();
		}
			try {

				GetConnectionChat response = (GetConnectionChat) GAMER.receive_message();
				System.out.println("MESSAGE RECEIVED");
				GetListView getListView = new GetListView(response.getClients());
				ListView listView = getListView.generateListView();
				anchorPane.getChildren().add(listView);

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}

		public void callUser()
		{
			try {
				GAMER.send_message(new CallUser(USER.getName()));
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
			GAMER.send_message(new SearchFriends(USER.getName()));
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
			GAMER.send_message(new Notification(USER.getName()));
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


	public void acceptRequest(String name){
		try {
			GAMER.send_message(new AcceptRequest(name));
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}


	public void searchUser() {

		try {
			GAMER.send_message(new SearchUsers(searchID.getText()));
			Object response = GAMER.receive_message();

			if (response.equals(String.valueOf(Request.SEARCHUSERS))){
				ArrayList<Client> res ;
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
