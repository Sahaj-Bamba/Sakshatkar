package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashboardMainScreen {

    @FXML
    private AnchorPane topAnchorPane;

    @FXML
    private AnchorPane leftAnchorPane;

    @FXML
    private AnchorPane rightAnchorPane;

    public void hello(){
        System.out.println("Hello");
    }

    public void initialize() {
        System.out.println("Hello");
        System.out.println(topAnchorPane);
        System.out.println(leftAnchorPane);
        System.out.println(rightAnchorPane);

        AnchorPane root1=null;
        AnchorPane root2=null;
        AnchorPane root3=null;

        try {
            root1 = FXMLLoader.load(getClass().getResource("../FXML/HeaderMain.fxml"));
            root2 = FXMLLoader.load(getClass().getResource("../FXML/LeftView.fxml"));
            root3 = FXMLLoader.load(getClass().getResource("../FXML/RightView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            System.out.println("Hi");
//            topAnchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("../FXML/HeaderMain.fxml")));
//            leftAnchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("../FXML/LeftView.fxml")));
//            rightAnchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("../FXML/RightView.fxml")));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        System.out.println("Die");



        topAnchorPane.getChildren().add(root1);
        leftAnchorPane.getChildren().add(root2);
        rightAnchorPane.getChildren().add(root3);

        System.out.println("Kill");
    }
}
