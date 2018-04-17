package control;

import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.Location;
import model.LocationInterface;
import model.LocationTester;

import java.io.IOException;

public class LocationController extends Controller{

    private Location location;

    @FXML public Label idText;

    @FXML public TableColumn<Location, Integer> idLocCol;
    @FXML public TableColumn<Location, String> nameLocCol;
    @FXML public TableColumn<Location, Float> latitudeLocCol;
    @FXML public TableColumn<Location, Float> longitudeLocCol;
    @FXML public TableColumn<Location, Float> ratingLocCol;
    @FXML public TableColumn<Location, Float> qualityLocCol;
    @FXML public TableView<Location> locTable;

    @FXML
    public void initialize() {
        nameLocCol.setCellValueFactory( new PropertyValueFactory<>( "name" ));
        latitudeLocCol.setCellValueFactory( new PropertyValueFactory<>( "latitude" ));
        longitudeLocCol.setCellValueFactory( new PropertyValueFactory<>( "longitude" ));
        ratingLocCol.setCellValueFactory( new PropertyValueFactory<>( "rating" ));
        qualityLocCol.setCellValueFactory( new PropertyValueFactory<>( "quality" ));

    }




    @FXML
    protected void handleReturnButtonAction(ActionEvent event) throws IOException {
        mainApp.showScene("main");
    }


    @Override
    public void setHandOff(Object handOff) {
        location = (Location) handOff;
        idText.setText(Integer.toString(location.getID()));

        final ObservableList<Location> items = FXCollections.observableArrayList(location);

        locTable.setItems(items);
    }
}
