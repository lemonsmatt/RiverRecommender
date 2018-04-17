package control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class BanUserController extends Controller {

    @FXML public TableColumn<String, String> emailCol;
    @FXML public TableColumn<String, Boolean> banCol;
    @FXML public TableView<User> table;

    @FXML
    public void initialize() {
        banCol.setCellValueFactory( new PropertyValueFactory<>( "ban" ));
        banCol.setCellFactory(tc -> new CheckBoxTableCell<>());

        emailCol.setCellValueFactory( new PropertyValueFactory<>( "email" ));

        UserInterface userInterface= new UserTester();
        final ObservableList<User> items = FXCollections.observableArrayList(userInterface.getBannableUsers());

        table.setItems(items);
        table.setEditable(true);

    }

    @FXML protected void handleCancelButtonAction(ActionEvent event) throws IOException {
        mainApp.showScene("main");
    }

    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        UserInterface userInterface= new UserTester();


        List<User> del = new ArrayList<User>();
        for (User user: table.getItems()) {
            if (user.getBan()) {
                del.add(user);
            }
        }
        table.getItems().remove(del);
        for (User user: del) {
            userInterface.banUser(user, mainApp.getUser().getEmail());
        }

        mainApp.showScene("main");
    }
}
