package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;

public class Cnt3 {

	@FXML
	AnchorPane a;

	@FXML
	AnchorPane b;

	@FXML
	Button c;

	public void initialize()
	{
		AnchorPane root4 = null;
		AnchorPane root5 = null;

		try {
			root4 = FXMLLoader.load(getClass().getResource("../FXML/sc1.fxml"));
			root5 = FXMLLoader.load(getClass().getResource("../FXML/sc2.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}


		a.getChildren().add(root4);
		b.getChildren().add(root5);
	}


}
