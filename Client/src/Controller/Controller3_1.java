package Controller;

import Request.GroupPass;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

import static sample.Main.GAMER;

public class Controller3_1 {

	@FXML
	TextField name;

	@FXML
	TextField password;

	public void submit(){
		System.out.println("3_1 Hi");

		try {
			GAMER.send_message(new GroupPass(password.getText(),name.getText(),GAMER.get_name()));
			System.out.println("Group Data Sent");
			GAMER.set_group(name.getText());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
