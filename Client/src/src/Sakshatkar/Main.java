package src.Sakshatkar;

//import Utilities.SqlQueryExecuter;

import src.Utilities.SqlQueryExecuter;

public class Main {

    public static SqlQueryExecuter SQLQueryExecuter;


    public static void main(String[] args) {


        SQLQueryExecuter = new SqlQueryExecuter("root","root","jdbc:mysql://localhost/sakshatkar");






    }

}
