package Controller;

import RequestClasses.GetConnectionChat;

import java.io.IOException;

import static Main.Main.GAMER;

public class LeftView {


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

}
