package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import model.*;


import java.io.IOException;

public class loginController extends Controller{
    @FXML private TextField fieldEmail;
    @FXML private TextField fieldPassword;
    @FXML private TextField fieldUsername;
    private UserInterface userInterface;


    @FXML
    public void initialize() {
        userInterface = new UserSQL();
    }

    @FXML protected void handleLoginButtonAction(ActionEvent event) throws IOException {
        String email = fieldEmail.getText();
        String password = fieldPassword.getText();

        if (userInterface.isValidUser(email, password))
        {
            mainApp.setUser(userInterface.getUser(email, password));
            mainApp.showScene("main");

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid user", ButtonType.OK);
            alert.showAndWait();
        }

    }

    @FXML protected void handleRegisterButtonAction(ActionEvent event) {
        String email = fieldEmail.getText();
        String username = fieldUsername.getText();
        String password = fieldPassword.getText();

        UserInterface userInterface = new UserTester();
        userInterface.addUser(new User(email, username, password, false, false, ""));

        mainApp.showScene("main");
    }

}

