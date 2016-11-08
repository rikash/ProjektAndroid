package databaseconnector;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by admin on 08.11.2016.
 */

public class DatabaseConnector {

    private String url;
    private String user;
    private String password;
    private Statement statement;
    private ResultSetMetaData resultSetMetaData;

    public DatabaseConnector(){

    }

    public DatabaseConnector(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
        createConnection();
    }

    public ResultSet select(String word,String tableName){
        ResultSet resultSet = null;
        try {
            resultSet = this.statement.executeQuery("SELECT " +word + " FROM "+tableName);
            this.resultSetMetaData = resultSet.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet executeQuery(String query){
        ResultSet resultSet = null;
        try {
            resultSet = this.statement.executeQuery(query);
            this.resultSetMetaData = resultSet.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    private void createConnection(){
        try {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,user,password);
            String result = "Database connect successfull";
            this.statement = con.createStatement();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
