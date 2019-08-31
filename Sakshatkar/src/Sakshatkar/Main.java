package Sakshatkar;

import Controller.RegistrationForm;
import Utilities.SqlQueryExecuter;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static SqlQueryExecuter sqlQueryExecuter;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../FXML/RegistrationForm.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Registration page");
        primaryStage.show();
    }

    public static void main(String[] args) {
//        sqlQueryExecuter = new SqlQueryExecuter("root","","jdbc:mysql://localhost/sakshatkar");
//        System.out.println("HEllo");
        launch(args);
        SQLQueryExecuter = new SqlQueryExecuter("root","MySql","jdbc:mysql://localhost/sakshatkar");
        sqlQueryExecuter = new SqlQueryExecuter("root","root","jdbc:mysql://localhost/sakshatkar");
    }

}
