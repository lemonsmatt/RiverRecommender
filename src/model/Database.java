package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public class Database {

    private static Connection connection;

    public Database() {
        if (connection == null) {
            setupServer();
        }
    }

    private void setupServer(){
        String hostName = "riverrecommender.Database.windows.net";
        String dbName = "riverrecommender";
        String user = "cascader";
        String password = "waterfall1.";
        String url = String.format("jdbc:sqlserver://%s:1433;Database=%s;user=%s;password=%s;encrypt=true;hostNameInCertificate=*.Database.windows.net;loginTimeout=30;allowMultiQueries=true;", hostName, dbName, user, password);
        connection = null;

        try {
            connection = DriverManager.getConnection(url);
            String schema = connection.getSchema();
            System.out.println("Successful connection - Schema: " + schema);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet queryServer(String query) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
//            statement.execute(query);
            return resultSet;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void queryServerMulti(String query) {
        try {
            Statement statement = connection.createStatement();
            boolean rs = statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }
}
