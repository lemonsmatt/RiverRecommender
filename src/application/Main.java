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
import model.*;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.*;
import java.util.EmptyStackException;

public class Main extends Application {
    private static Stage mainStage;
    private static  Pane rootLayout;
    private static User user;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Database database = new Database();
        user = new User("New User", "", "", false, true, "");
        mainStage = primaryStage;
        LocationInterface locationInterface = new LocationTester();
        UserInterface userInterface = new UserTester();
        initRootLayout(mainStage);
        showScene("main");

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

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Main.user = user;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
