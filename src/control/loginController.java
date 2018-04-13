package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.LoginInterface;
import model.LoginTester;

import java.io.IOException;

public class loginController extends Controller{
    @FXML private TextField fieldEmail;
    @FXML private TextField fieldPassword;
    private LoginInterface loginInterface;


    @FXML
    public void initialize() {
        loginInterface = new LoginTester();
    }

    @FXML protected void handleLoginButtonAction(ActionEvent event) throws IOException {
        String email = fieldEmail.getText();
        String password = fieldPassword.getText();
        if (loginInterface.isValidUser(email, password))
        {
            if (loginInterface.isAdmin(email, password)) {

            } else {
                mainApp.showScene("main");

            }
        }

    }

    @FXML protected void handleRegisterButtonAction(ActionEvent event) {
        fieldEmail.setText("register");
    }

}

