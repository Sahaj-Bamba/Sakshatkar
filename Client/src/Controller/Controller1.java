package Controller;

import Request.WhoIAm;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import sample.Client;
import sample.Main;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class Controller1 {

	@FXML
	TextField ip;

	@FXML
	TextField port;

	@FXML
	TextField name;

	public void submit(){
		System.out.println("1");
		System.out.println(ip.getText());

		Main.GAMER = new Client(ip.getText() ,parseInt(port.getText()), name.getText());
		System.out.println("Client connection Created");

		try {
			Main.GAMER.send_message(new WhoIAm(name.getText()));
			System.out.println("Name sent to server");
		} catch (IOException e) {
			e.printStackTrace();
		}

		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("../FXML/scene2.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.PRIMARYSTAGE.setScene(new Scene(root,Main.WIDTH,Main.HEIGHT));

	}

}
