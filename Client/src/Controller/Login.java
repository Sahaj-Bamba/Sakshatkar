package Controller;

import RequestClasses.Profile;
import RequestClasses.Response;
import Utilities.FXMLInitiator;
import Windows.AlertBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.io.IOException;

import static Main.Main.*;

public class Login {

	@FXML
	private TextField userName;

	@FXML
	private PasswordField password;

	@FXML
	public void Login(){
		try {
			GAMER.send_message(new RequestClasses.Login(userName.getText(),password.getText()));
			Response res = (Response) GAMER.receive_message();
			if (res.getStatus()==0){
				System.out.println("Login Successful");
				GAMER.send_message(new RequestClasses.Profile(userName.getText()));
				Object result = (Object) GAMER.receive_message();
				if (result instanceof Profile){
					USER = ((Profile) result).getClient();
					FILESYSTEM.login(USER);
					FILESYSTEM.newUser(USER);
				}
			}else{
				System.out.println("Login failed due to following error");
				System.out.println(res.getMessage());
				new AlertBox("Login Error","Invalid username and password combination").start();
				password.setText("");
				userName.setText("");
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}

		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("../FXML/MainScreen.fxml"));
		Parent root = null;
		try {
			root = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		MAINSCREENCONTROLLER = fxmlLoader.getController();

//		try {
//			root = FXMLLoader.load(getClass().getResource("../FXML/MainScreen.fxml"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		Main.MAIN = new Scene(root);
//		PRIMARYSTAGE.setScene(MAIN);

		PRIMARYSTAGE.setScene(new Scene(root));
		PRIMARYSTAGE.show();

	}


	public void SignUp(ActionEvent actionEvent) {
		try {
			FXMLInitiator fxmlInitiator = new FXMLInitiator("../FXML/Register.fxml");
			fxmlInitiator.start(PRIMARYSTAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
