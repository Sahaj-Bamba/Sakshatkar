package Controller;

import RequestClasses.Response;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

import static Main.Main.GAMER;

public class ChatWindow {



    public void initialize(){

    }

    private TextField userName;
    private PasswordField password;

    //private String name;
    //private String pass;


    public void userProfile() {

        try {
            GAMER.send_message(new RequestClasses.Login(userName.getText(),password.getText()));
//			System.out.println("1");
            Response res = (Response) GAMER.receive_message();
            //System.out.println("2");
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




}
