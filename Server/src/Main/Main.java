package Main;

import Utilities.SqlQueryExecuter;

public class Main {

	public static SqlQueryExecuter SQLQUERYEXECUTER;

	public static void main(String args[]) {

		SQLQUERYEXECUTER = new SqlQueryExecuter("root", "root", "jdbc:mysql://localhost/sakshatkar");

		new RequestServer().start();

	}

}
