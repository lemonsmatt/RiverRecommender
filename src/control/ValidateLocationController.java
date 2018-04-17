package control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Location;
import model.LocationInterface;
import model.LocationTester;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ValidateLocationController extends Controller {

    @FXML public TableColumn<Location, Integer> idCol;
    @FXML public TableColumn<Location, String> nameCol;
    @FXML public TableColumn<Location, Float> latitudeCol;
    @FXML public TableColumn<Location, Float> longitudeCol;
    @FXML public TableColumn<Location, Boolean> validateCol;
    @FXML public TableView<Location> table;

    @FXML
    public void initialize() {
        validateCol.setCellValueFactory( new PropertyValueFactory<>( "validated" ));
        validateCol.setCellFactory( tc -> new CheckBoxTableCell<>());

        idCol.setCellValueFactory( new PropertyValueFactory<>( "id" ));
        nameCol.setCellValueFactory( new PropertyValueFactory<>( "name" ));
        latitudeCol.setCellValueFactory( new PropertyValueFactory<>( "latitude" ));
        longitudeCol.setCellValueFactory( new PropertyValueFactory<>( "longitude" ));



        LocationInterface locationInterface = new LocationTester();
        final ObservableList<Location> items = FXCollections.observableArrayList(locationInterface.getUnvalidatedLocations());

        table.setItems(items);
        table.setEditable(true);

    }

    @FXML protected void handleCancelButtonAction(ActionEvent event) throws IOException {
        mainApp.showScene("main");
    }

    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        LocationInterface locationInterface = new LocationTester();


        List<Location> validate = new ArrayList<Location>();
        for (Location loc: table.getItems()) {
            if (loc.isValidated()) {
                validate.add(loc);
            }
        }
        table.getItems().remove(validate);

        mainApp.showScene("main");
    }
}
