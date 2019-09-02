package Windows;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.awt.*;

class TestingResizing extends Application {

	/**
	 * The main entry point for all JavaFX applications.
	 * The start method is called after the init method has returned,
	 * and after the system is ready for the application to begin running.
	 *
	 * <p>
	 * NOTE: This method is called on the JavaFX Application Thread.
	 * </p>
	 *
	 * @param primaryStage the primary stage for this application, onto which
	 *                     the application scene can be set. The primary stage will be embedded in
	 *                     the browser if the application was launched as an applet.
	 *                     Applications may create other stages, if needed, but they will not be
	 *                     primary stages and will not be embedded in the browser.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
//		fxmlLoader.setLocation(".fxml");
		Parent root = fxmlLoader.load();
		Label lbl = new Label();
		lbl.setText("Hello how are you?");
//		lbl.

	}
}
