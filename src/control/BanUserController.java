package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import model.UserInterface;
import model.UserSQL;

import java.io.IOException;

public class BanUserController extends Controller {
    @FXML
    public void initialize() {

    }

    @FXML protected void handleReturnButtonAction(ActionEvent event) throws IOException {
        mainApp.showScene("main");
    }


}
