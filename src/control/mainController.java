package control;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.Location;
import model.LocationInterface;
import model.LocationTester;
import model.User;

import java.io.IOException;

public class mainController extends Controller {

    @FXML private Label emailLabel;
    @FXML private Button loginButton;
    @FXML private Button logoutButton;
    @FXML private Button createButton;
    @FXML private Button deleteButton;
    @FXML private Button validateButton;
    @FXML private Button banButton;

    @FXML public TextField idField;
    @FXML public TextField nameField;
    @FXML public TextField latitudeField;
    @FXML public TextField longitudeField;
    @FXML public TextField radiusField;
    @FXML public Button filterButton;

    @FXML public TableColumn<Location, Integer> idCol;
    @FXML public TableColumn<Location, String> nameCol;
    @FXML public TableColumn<Location, Float> latitudeCol;
    @FXML public TableColumn<Location, Float> longitudeCol;
    @FXML public TableColumn<Location, Float> ratingCol;
    @FXML public TableColumn<Location, Float> qualityCol;
    @FXML public TableColumn<Location, String> weatherCol;
    @FXML public TableColumn<Location, Float> rainCol;
    @FXML public TableColumn<Location, Float> flowCol;
    @FXML public TableColumn<Location, Float> heightCol;
    @FXML public TableView<Location> table;

    @FXML
    public void initialize() {
        boolean isUser = !mainApp.getUser().getBan();
        boolean isAdmin = mainApp.getUser().isAdmin();
        logoutButton.setDisable(!isUser);
        loginButton.setDisable(isUser);
        createButton.setDisable(!isUser);
        deleteButton.setDisable(!isUser);
        validateButton.setDisable(!isAdmin);
        banButton.setDisable(!isAdmin);
        emailLabel.setText(mainApp.getUser().getEmail());

        idCol.setCellValueFactory( new PropertyValueFactory<>( "id" ));
        nameCol.setCellValueFactory( new PropertyValueFactory<>( "name" ));
        latitudeCol.setCellValueFactory( new PropertyValueFactory<>( "latitude" ));
        longitudeCol.setCellValueFactory( new PropertyValueFactory<>( "longitude" ));
        ratingCol.setCellValueFactory( new PropertyValueFactory<>( "rating" ));

        qualityCol.setCellValueFactory( new PropertyValueFactory<>( "quality" ));
        weatherCol.setCellValueFactory( new PropertyValueFactory<>( "weather" ));
        rainCol.setCellValueFactory( new PropertyValueFactory<>( "rain" ));
        flowCol.setCellValueFactory( new PropertyValueFactory<>( "flowDelta" ));
        heightCol.setCellValueFactory( new PropertyValueFactory<>( "heightDelta" ));


        LocationInterface locationInterface = mainApp.getLocationInterface();
        final ObservableList<Location> items = FXCollections.observableArrayList(locationInterface.getValidatedLocationsFull());

        table.setItems(items);

        table.setOnMouseClicked( event -> {
            if( event.getClickCount() == 2 ) {
                mainApp.showScene("location", table.getSelectionModel().getSelectedItem());
            }});

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
        mainApp.setUser(new User());
        mainApp.showScene("main");
    }

    @FXML protected void handleFilterButtonAction(ActionEvent event) throws  IOException {
        String name = nameField.getText();
        int id = 0;
        float latitude = 0;
        float longitude = 0;
        float radius = 0;
        boolean end = false;
        try {
            latitude = Float.parseFloat(latitudeField.getText());
        } catch (NumberFormatException e)
        {
            latitudeField.clear();
            latitudeField.setPromptText("Must be a numerical value");
            end= true;
        }
        try {
            longitude = Float.parseFloat(longitudeField.getText());
        } catch (NumberFormatException e)
        {
            longitudeField.clear();
            longitudeField.setPromptText("Must be a numerical value");
            end= true;
        }
        try {
            radius = Float.parseFloat(radiusField.getText());
        } catch (NumberFormatException e)
        {
            radiusField.clear();
            radiusField.setPromptText("Must be a numerical value");
            end= true;
        }
        try {
            id = Integer.parseInt(idField.getText());
        } catch (NumberFormatException e)
        {
            idField.clear();
            idField.setPromptText("Must be a numerical value");
            end= true;
        }
        if(end) {
            return;
        }
        LocationInterface locationInterface = mainApp.getLocationInterface();
        final ObservableList<Location> items = FXCollections.observableArrayList(locationInterface.getValidatedLocationsFull(latitude, longitude, radius, name, id));

        table.setItems(items);
    }
}
