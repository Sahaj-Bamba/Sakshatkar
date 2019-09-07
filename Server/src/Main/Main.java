package Main;

import Utilities.SqlQueryExecuter;

public class Main {

	public static SqlQueryExecuter SQLQUERYEXECUTER;

	public static void main(String args[]) {

		SQLQUERYEXECUTER = new SqlQueryExecuter("root", "root", "jdbc:mysql://localhost/sakshatkar");

		//t1 for server
		Thread t1 = new Thread(new RequestServer());
		//t2 for file server
		Thread t2 = new Thread(new FileServer());
		//t3 for messaging
		Thread t3 = new Thread(new MessageServer());
		t1.start();
		t2.start();
		t3.start();

	}

}
