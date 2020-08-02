package edu.rasmussen.courseproject;

import android.annotation.SuppressLint;
import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {

    String url = "cop4677c.database.windows.net:1433";
    String db = "course project";
    String un = "matt@cop4677c";
    String pass = "5tgb5tgb%TGB%TGB";

    @SuppressLint("NewApi")
    //connect to database
    public Connection conn(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://"+url+";databaseName="+db+";user="+un+";password="+pass+";encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            connection = DriverManager.getConnection(ConnectionURL);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e2) {
            e2.printStackTrace();
        } catch (Exception e3){
            e3.printStackTrace();
        }
        return connection;
    }
}
