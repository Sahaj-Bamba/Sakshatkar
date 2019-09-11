package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainScreen {

    @FXML
    public AnchorPane topAnchorPane;

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
//            System.out.println("@main anchor pane" + mainAnchorPane.getChildren().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMainAnchorPane(Parent root) {
//        System.out.println(mainAnchorPane);
        mainAnchorPane.getChildren().clear();
        //            mainAnchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("../FXML/ChatWindow_new.fxml")));
        mainAnchorPane.getChildren().add(root);
        //        try {
//            Main.PRIMARYSTAGE.setScene(new Scene(FXMLLoader.load(getClass().getResource("../FXML/MainScreen.fxml"))));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Main.PRIMARYSTAGE.show();
        System.out.println("@ Main Screen");
    }
}

