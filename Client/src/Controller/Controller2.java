package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import sample.Main;

import java.io.IOException;

public class Controller2 {

//	Button welcomeClick;

	public void create_room(){

		System.out.println("Room Joining initiated");

		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("../FXML/scene3_1.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.PRIMARYSTAGE.setScene(new Scene(root,Main.WIDTH,Main.HEIGHT));

	}

	public void join_room(){
		System.out.println("Room Creating Initiated");

		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("../FXML/scene3_2.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.PRIMARYSTAGE.setScene(new Scene(root,Main.WIDTH,Main.HEIGHT));

	}

}
