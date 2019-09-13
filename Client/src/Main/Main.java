package Main;

import Controller.ChatWindow_new;
import Controller.LeftView;
import Controller.MainScreen;
import RequestClasses.SetUser;
import Utilities.FXMLInitiator;
import Utilities.FileSystem;
import Windows.AlertBox;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
	public static MainScreen MAINSCREENCONTROLLER = null;
	public static ChatWindow_new CHATWINDOWCONTROLLER = null;

	@Override
	public void start(Stage primaryStage) throws Exception {

		FILESYSTEM = new FileSystem("Sakshatkar");
		System.out.println("Started");
		GAMER = new Client("localhost",5555);
		Thread.sleep(100);
		if (Main.ISONLINE) {
			System.out.println("GAMER created");
			FILEGAMER = new FileClient("localhost", 6000);
			Thread.sleep(100);
			System.out.println("File Gamer created");
			MESSAGEGAMER = new MessageClient("localhost", 5701);
			Thread.sleep(100);
			System.out.println("Message GAMER created");
		} else {

			/* Write code to do everything from file */

		}

		PRIMARYSTAGE = primaryStage;
		USER = FILESYSTEM.isLoggedIn();
		if (USER == null){
			if (!(Main.ISONLINE)) {
				new AlertBox("Connection timed Out", "You must be connected to internet to login or register.").start();
				System.exit(0);
			}else{
				new FXMLInitiator("../FXML/Login.fxml").start(PRIMARYSTAGE);
				System.out.println("Basic Welcome Screen ");
			}
		}else {
			if ((Main.ISONLINE)) {
				GAMER.send_message(new SetUser(USER));
				GAMER.receive_message();
			}
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("../FXML/MainScreen.fxml"));
			Parent root = fxmlLoader.load();
			MAINSCREENCONTROLLER = (MainScreen) fxmlLoader.getController();
			PRIMARYSTAGE.setScene(new Scene(root, 1500,1000));
			//Code contributed by chits
//			primaryStage.initStyle(StageStyle.UNDECORATED);
			PRIMARYSTAGE.show();
//			new FXMLInitiator("../FXML/MainScreen.fxml").start(PRIMARYSTAGE);
			System.out.println("Basic Welcome Screen ");
		}

//		PRIMARYSTAGE = primaryStage;
//		Parent root = FXMLLoader.load(getClass().getResource("../FXML/Login.fxml"));
//		GAMER = new Client("localhost",5555);
//		FILEGAMER = new FileClient("localhost", 6000);
//		Scene scene = new Scene(root,HEIGHT,WIDTH);
//		primaryStage.setScene(scene);
//		primaryStage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}



}
