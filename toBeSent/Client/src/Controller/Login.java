package Controller;

import RequestClasses.Response;
import Utilities.SceneSetter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

import static Main.Main.GAMER;

public class Login {

	@FXML
	private TextField userName;

	@FXML
	private PasswordField password;

	@FXML
	public void Login(){
		try {
			GAMER.send_message(new RequestClasses.Login(userName.getText(),password.getText()));
//			System.out.println("1");
			Response res = (Response) GAMER.receive_message();
			System.out.println("2");
			if (res.getStatus()==0){
				System.out.println("Login Successful");
			}else{
				System.out.println("Login failed due to following error");
				System.out.println(res.getErrorMessage());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}


	public void SignUp(ActionEvent actionEvent) throws IOException {
		new SceneSetter("../FXML/RegisterData.fxml");
	}
}
