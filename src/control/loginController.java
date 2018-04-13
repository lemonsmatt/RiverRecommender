package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class loginController {
    @FXML private TextField fieldEmail;
    @FXML private TextField fieldPassword;


    @FXML protected void handleLoginButtonAction(ActionEvent event) {
        fieldEmail.setText("login");
    }

    @FXML protected void handleRegisterButtonAction(ActionEvent event) {
        fieldEmail.setText("register");
    }

}

