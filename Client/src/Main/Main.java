package Main;

import Controller.DashboardMainScreen;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static Stage PRIMARYSTAGE;
	public static Scene MAIN;
	public static Client GAMER;
	public static FileClient FILEGAMER;
	public static int HEIGHT = 800;
	public static int WIDTH = 1400;

	@Override
	public void start(Stage primaryStage) throws Exception{

		System.out.println("Started");
		GAMER = new Client("localhost",5558,"Sam");
		FILEGAMER = new FileClient("localhost", 5558,"FileClientSocket");
		System.out.println("Came back to main");
		PRIMARYSTAGE = primaryStage;


		Parent root = FXMLLoader.load(getClass().getResource("../FXML/Login.fxml"));
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		primaryStage.setScene(scene);
		//primaryStage.setFullScreen(true);
		primaryStage.show();
		System.out.println("Basic Welcome Screen ");

	}

	public static void main(String[] args) {
		launch(args);
	}

}
