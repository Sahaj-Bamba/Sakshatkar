/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */

public class SqlQueryExecuter {
    
    private String user ;
    private String pass ;
    private String url;
    private Connection conn ;
    private Statement stm ;
    private ResultSet rs = null;
    
    public SqlQueryExecuter(String user , String pass , String url ) {
        this.user = user;
        this.pass = pass;
        this.url = url;
        try {
//            con = DriverManager.getConnection("jdbc:mysql://localhost/test", user, pass);
            this.conn = java.sql.DriverManager.getConnection(this.url , this.user , this.pass );
            this.stm = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(SqlQueryExecuter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet select(String sql){
        System.out.println(sql);
        try {
            return this.stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(SqlQueryExecuter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void update(String sql) {
        System.out.println(sql);
        try {
            this.stm.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(SqlQueryExecuter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void close(){
        try {
            this.stm.close();
            this.conn.close();
            this.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SqlQueryExecuter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
