package Controller;

import RequestClasses.*;
import javafx.event.ActionEvent;

import java.io.IOException;

import static Main.Main.GAMER;

public class LeftView {


	//private Object Gamer;

	public void initialize(){

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
			GAMER.send_message(new SearchUser(GAMER.get_name()));
		}catch (IOException e)
		{
			e.printStackTrace();
		}
		try {
			SearchUser response = (SearchUser) GAMER.receive_message();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}

	}
}
