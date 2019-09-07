package Main;

import Utilities.FileSystem;
import Windows.AlertBox;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static boolean ISONLINE;
	public static FileSystem FILESYSTEM;
	public static Stage PRIMARYSTAGE;
	public static Scene MAIN;
	public static DataClasses.Client USER;
	public static Client GAMER;
	public static FileClient FILEGAMER;
	public static MessageClient MESSAGEGAMER;
	public static int HEIGHT = 800;
	public static int WIDTH = 1400;

	@Override
	public void start(Stage primaryStage) throws Exception{

		FILESYSTEM = new FileSystem("Sakshatkar");

		System.out.println("Started");
		GAMER = new Client("localhost",5555);
		Thread.sleep(200);
		if (Main.ISONLINE) {
			System.out.println("GAMER created");
			FILEGAMER = new FileClient("localhost", 6000);
			Thread.sleep(200);
			System.out.println("File Gamer created");
			MESSAGEGAMER = new MessageClient("localhost", 5701);
			Thread.sleep(200);
			System.out.println("Message GAMER created");
		}

		PRIMARYSTAGE = primaryStage;
		USER = FILESYSTEM.isLoggedIn();
		if (USER == null){
			if (!(Main.ISONLINE)) {
				new AlertBox("Connection timed Out", "You must be connected to internet to login or register.");
				System.exit(0);
			}else{
				Parent root = FXMLLoader.load(getClass().getResource("../FXML/Login.fxml"));
				Scene scene = new Scene(root, WIDTH, HEIGHT);
				primaryStage.setScene(scene);
				primaryStage.show();
				System.out.println("Basic Welcome Screen ");
			}
		}else {
			Parent root = FXMLLoader.load(getClass().getResource("../FXML/MainScreen.fxml"));
			Scene scene = new Scene(root, WIDTH, HEIGHT);
			primaryStage.setScene(scene);
			primaryStage.show();
			System.out.println("Basic Welcome Screen ");
		}

	}

	public static void main(String[] args) {
		launch(args);
	}

}
