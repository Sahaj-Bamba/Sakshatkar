package Main;

import Utilities.SqlQueryExecuter;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main {

	public static SqlQueryExecuter SQLQUERYEXECUTER;
	public static Stage PRIMARYSTAGE;
	public static Scene MAIN;

	public static void main(String args[]) {

		SQLQUERYEXECUTER = new SqlQueryExecuter("root","root","jdbc:mysql://localhost/sakshatkar");
		new Server().start();

	}

}