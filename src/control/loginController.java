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
        userInterface = mainApp.getUserInterface();
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

        if (email.equals("") || username.equals("") || password.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Empty Field", ButtonType.OK);
            alert.showAndWait();
        }
        UserInterface userInterface = new UserSQL();
        if (!userInterface.addUser(new User(email, username, password, false, false, "")))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Duplicate user OR Invalid user", ButtonType.OK);
            alert.showAndWait();
        }
        mainApp.showScene("main");
    }

}

