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

public class Main extends Application {
    private static Stage mainStage;
    private static  Pane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainStage = primaryStage;
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
            loader.setLocation(Main.class.getResource("../view/login.fxml"));
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


    public static void main(String[] args) {
        launch(args);
    }
}
