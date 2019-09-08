package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainScreen {

    @FXML
    private AnchorPane topAnchorPane;

    @FXML
    private AnchorPane leftAnchorPane;

    @FXML
    private AnchorPane rightAnchorPane;

    public void initialize() {

        try {
            topAnchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("../FXML/Header.fxml")));
            leftAnchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("../FXML/MainScreenLeftView.fxml")));
            rightAnchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("../FXML/MainScreenRightPart.fxml")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
