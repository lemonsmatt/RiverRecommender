package application;

import control.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.EmptyStackException;

public class Main extends Application {
    private static Stage mainStage;
    private static  Pane rootLayout;
    private static boolean admin;
    private static boolean user;
    private static String email;
    private static Connection connection;


    @Override
    public void start(Stage primaryStage) throws Exception{
        setupServer();

        ResultSet r = null;
        r = queryServer("SELECT * FROM [User]");

        while (r.next())
        {
            System.out.println(r.getString(1) + " "
                    + r.getString(2));
        }


        admin = false;
        user = false;
        email = "New User";
        mainStage = primaryStage;
        initRootLayout(mainStage);
        showScene("main");

    }

    public void setupServer(){
        String hostName = "riverrecommender.database.windows.net";
        String dbName = "riverrecommender";
        String user = "cascader";
        String password = "waterfall1.";
        String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
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
           return resultSet;
       } catch (Exception e) {
            e.printStackTrace();
       }
       return null;
    }

    public void showScene(String sceneName) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/" + sceneName + ".fxml"));
            Pane root = loader.load();
            mainStage.setScene(new Scene(root));
            mainStage.sizeToScene();

            Controller mainController = loader.getController();
            mainController.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void initRootLayout(Stage mainStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/main.fxml"));
            rootLayout = loader.load();

            Controller mainController = loader.getController();
            mainController.setMainApp(this);

            Scene scene = new Scene(rootLayout);
            mainStage.setTitle("River Recommender");
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isAdmin() {
        return admin;
    }

    public static boolean isUser() {
        return user;
    }

    public static String getEmail() {
        return email;
    }

    public static void setAdmin(boolean admin) {
        Main.admin = admin;
    }

    public static void setEmail(String email) {
        Main.email = email;
    }

    public static void setUser(boolean user) {
        Main.user = user;
    }

    public static void main(String[] args) {
        launch(args);
        try {
            connection.close();
        } catch (SQLException e)
        {

        }
    }
}
