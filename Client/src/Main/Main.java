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
//	public static Scene MAIN;
	public static Client GAMER;
	public static int HEIGHT = 800;
	public static int WIDTH = 1200;

	@Override
	public void start(Stage primaryStage) throws Exception{

//		System.out.println("Started");
//		GAMER = new Client("localhost",5558,"Sam");
//		System.out.println("Came back to main");
//		PRIMARYSTAGE = primaryStage;


		Parent root = FXMLLoader.load(getClass().getResource("../FXML/DashBoardMainScreen.fxml"));
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		primaryStage.setScene(scene);
		//primaryStage.setFullScreen(true);
		primaryStage.show();
		System.out.println("Basic Welcome Screen ");


//		FXMLLoader fxmlLoader = new FXMLLoader();
//		fxmlLoader.setLocation(getClass().getResource("../FXML/DashBoardMainScreen.fxml"));
//		DashboardMainScreen dashboardMainScreen = fxmlLoader.getController();
//		dashboardMainScreen.hello();
//		Parent root = fxmlLoader.load();
//		Scene scene = new Scene(root);
//		primaryStage.setScene(scene);
//		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
