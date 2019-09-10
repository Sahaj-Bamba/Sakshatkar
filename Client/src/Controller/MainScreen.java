package Controller;

import Main.Main;
import RequestClasses.GetChats;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import javax.swing.text.html.ListView;
import java.io.IOException;

public class MainScreen {

    @FXML
    private AnchorPane topAnchorPane;

    @FXML
    private AnchorPane leftAnchorPane;

    @FXML
    private AnchorPane rightAnchorPane;

    @FXML
    private AnchorPane mainAnchorPane;

    public void initialize() {

        try {
            topAnchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("../FXML/Header.fxml")));
            leftAnchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("../FXML/MainScreenLeftView.fxml")));
            rightAnchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("../FXML/MainScreenRightPart.fxml")));
            mainAnchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("../FXML/InitialChatScreen.fxml")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showChats(){

        try {
            Main.GAMER.receive_message();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


}
