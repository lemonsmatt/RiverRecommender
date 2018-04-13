package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.UserInterface;
import model.UserTester;

import java.io.IOException;

public class loginController extends Controller{
    @FXML private TextField fieldEmail;
    @FXML private TextField fieldPassword;
    private UserInterface userInterface;


    @FXML
    public void initialize() {
        userInterface = new UserTester();
    }

    @FXML protected void handleLoginButtonAction(ActionEvent event) throws IOException {
        String email = fieldEmail.getText();
        String password = fieldPassword.getText();

        if (userInterface.isValidUser(email, password))
        {
            mainApp.setUser(userInterface.isValidUser(email, password));
            mainApp.setAdmin(userInterface.isAdmin(email, password));
            mainApp.setEmail(email);
            mainApp.showScene("main");

        }

    }

    @FXML protected void handleRegisterButtonAction(ActionEvent event) {
        fieldEmail.setText("register");
    }

}

