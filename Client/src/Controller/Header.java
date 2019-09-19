package Controller;


import Main.Main;
import RequestClasses.*;
import Utilities.FXMLInitiator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
//import java.io.Serializable;
import static Main.Main.*;


public class Header {


    public void initialize() {

    }

    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;

//    @FXML
//    public void logIn() {
//
//        try {
//            GAMER.send_message(new RequestClasses.Login(userNameLabel.getText(), password.getText()));
//			System.out.println("1");
//            Response res = (Response) GAMER.receive_message();
//            System.out.println("2");
//            if (res.getStatus() == 0) {
//                System.out.println("Login Successful");
//            } else {
//                System.out.println("Login failed due to following error");
//                System.out.println(res.getMessage());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    public void logOut(ActionEvent event) {
        try {
            Main.GAMER.send_message(new LogOut(USER.getUserID()));
            Response response = (Response) GAMER.receive_message();
            FILESYSTEM.logout();
            new FXMLInitiator("../FXML/Login.fxml").start(PRIMARYSTAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
