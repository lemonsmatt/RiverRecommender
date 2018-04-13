package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.io.IOException;

public class mainController extends Controller {

    @FXML private Label emailLabel;
    @FXML private Button loginButton;
    @FXML private Button logoutButton;
    @FXML private Button createButton;
    @FXML private Button deleteButton;
    @FXML private Button validateButton;
    @FXML private Button banButton;

    @FXML
    public void initialize() {
        boolean isUser = mainApp.isUser();
        boolean isAdmin = mainApp.isAdmin();
        logoutButton.setDisable(!isUser);
        createButton.setVisible(isUser);
        deleteButton.setVisible(isUser);
        validateButton.setVisible(isAdmin);
        banButton.setVisible(isAdmin);
        emailLabel.setText(mainApp.getEmail());
    }

    @FXML protected void handleLoginButtonAction(ActionEvent event) throws IOException {
        mainApp.showScene("login");
    }

    @FXML protected void handleCreateButtonAction(ActionEvent event) throws IOException {
        mainApp.showScene("createLocation");
    }

    @FXML protected void handleDeleteButtonAction(ActionEvent event) throws IOException {
        mainApp.showScene("deleteLocation");
    }

    @FXML protected void handleValidateButtonAction(ActionEvent event) throws IOException {
        mainApp.showScene("validateLocation");
    }

    @FXML protected void handleBanButtonAction(ActionEvent event) throws IOException {
        mainApp.showScene("banUser");
    }
    @FXML protected  void handleLogoutButtonAction(ActionEvent event) throws IOException {
        mainApp.setAdmin(false);
        mainApp.setUser(false);
        mainApp.setEmail("New User");
        mainApp.showScene("main");
    }
}
