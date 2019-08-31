package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	public static Stage PRIMARYSTAGE;
	public static Scene MAIN;
	public static Client GAMER;
	public static int HEIGHT = 800;
	public static int WIDTH = 1200;

	@FXML
	AnchorPane a;
	@FXML
	AnchorPane b;

	@FXML
	Button c;

	@Override
	public void start(Stage primaryStage) throws Exception{

//		AnchorPane root1 = FXMLLoader.load(getClass().getResource("../FXML/sample.fxml"));
//		Parent root2 = FXMLLoader.load(getClass().getResource("../FXML/scene1.fxml"));

		AnchorPane root6 = FXMLLoader.load(getClass().getResource("../FXML/sc3.fxml"));

//		this.init();
//		@FXML
//		AnchorPane a;
//		@FXML
//		AnchorPane b;
//
//		@FXML
//		Button c;

//		a = ;
//		b = root5;

//		Button d = new Button();
//
//		System.out.println(d);
//
//		System.out.println(a);
//		System.out.println(b);
//		System.out.println(c);
//
//		for (Node X : root4.getChildren()){
//			System.out.println(X);
////			a.getChildren().add(X);
//		}
//		for (Node X : root5.getChildren()){
//			System.out.println(X);
////			b.getChildren().add(X);
//		}
//
////		Scene scene1 = new Scene(root1,150,300);
////		Scene scene2 = new Scene(root2,150,300);
////		VBox layout = new VBox(10);
////
//		System.out.println(scene1.getRoot());
//		System.out.println(scene2.getRoot());
//		Label a = new Label();
//		Label b = new Label();
//		a.setText("Hi hello");
//		b.setText("Bye ");
//		Parent x = scene1.getRoot();
//		Parent y = scene2.getRoot();
//		al = root1;


//		x.getChildrenUnmodifiable();
//		for (Node X : y.getChildrenUnmodifiable()){
//			System.out.println(X.toString());
//		}



//		layout.getChildren().addAll(b,x,y,a);
//		layout.setAlignment(Pos.CENTER);
//
//		for (Node X : layout.getChildrenUnmodifiable()){
//			System.out.println(X.toString());
//		}
//
//		//Display window and wait for it to be closed before returning
//

		PRIMARYSTAGE = primaryStage;
		MAIN = new Scene(root6, WIDTH, HEIGHT);
		primaryStage.setScene(MAIN);
		primaryStage.show();
		System.out.println("Basic Welcome Screen ");

	}

	public static void main(String[] args) {
		launch(args);
	}

}
