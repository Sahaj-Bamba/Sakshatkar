package Controller;

import Main.Main;
import Utilities.FileSystem;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;

public class InitialChatScreen {

    @FXML
    ImageView imageView;

    @FXML
    Label introductoryMessage;

    public void initialize(){
        try {
            File file = Main.FILESYSTEM.getProfilePicture(Main.USER.getUserID()+"."+Main.USER.getExtension());
            Image image = new Image(file.toURI().toURL().toString());
            imageView.setImage(image);
            introductoryMessage.setText("Welcome, "+Main.USER.getName());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
