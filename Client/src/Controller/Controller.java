package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;

public class Controller {


	public void got_clicked(){

		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("../FXML/scene1.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.PRIMARYSTAGE.setScene(new Scene(root,Main.WIDTH,Main.HEIGHT));

		System.out.println("Screen Changed to scene 1");

//		Main.MAIN.setRoot(root);
//		welcomeClick.getParent().getParent().getScene().getWindow();
//		Stage st = (Stage)(welcomeClick.getParent().getScene().getWindow());
//		Main.PRIMARYSTAGE.setFullScreen(true);

	}

}
