package Utilities;

import Main.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMLInitiator extends Application {

    String FXMLFilePath;

    public FXMLInitiator(String FXMLFilePath) {
        this.FXMLFilePath = FXMLFilePath;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        System.out.println("Hello2");
        primaryStage = Main.PRIMARYSTAGE;
        Parent root = FXMLLoader.load(getClass().getResource(FXMLFilePath));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

}
