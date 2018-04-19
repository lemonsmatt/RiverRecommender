package control;

import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.*;

import java.io.IOException;
import java.util.List;

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

    @FXML public TableColumn<WeatherData, Integer> idWeatherCol;
    @FXML public TableColumn<WeatherData, String> nameWeatherCol;
    @FXML public TableColumn<WeatherData, Float> latitudeWeatherCol;
    @FXML public TableColumn<WeatherData, Float> longitudeWeatherCol;
    @FXML public TableColumn<WeatherData, Float> precipitationWeatherCol;
    @FXML public TableColumn<WeatherData, Float> windWeatherCol;
    @FXML public TableColumn<WeatherData, Float> temperatureWeatherCol;
    @FXML public TableColumn<WeatherData, Float> visibilityWeatherCol;
    @FXML public TableColumn<WeatherData, String> dateWeatherCol;
    @FXML public TableView<WeatherData> weatherTable;


    @FXML public TableColumn<GaugeData, Integer> idGaugeCol;
    @FXML public TableColumn<GaugeData, String> nameGaugeCol;
    @FXML public TableColumn<GaugeData, Float> latitudeGaugeCol;
    @FXML public TableColumn<GaugeData, Float> longitudeGaugeCol;
    @FXML public TableColumn<GaugeData, Float> flowRateGaugeCol;
    @FXML public TableColumn<GaugeData, Float> flowLevelGaugeCol;
    @FXML public TableColumn<GaugeData, String> dateGaugeCol;
    @FXML public TableView<GaugeData> gaugeTable;

    @FXML public Button rateButton;
    @FXML public TextField rateField;


    @FXML
    public void initialize() {
        nameLocCol.setCellValueFactory( new PropertyValueFactory<>( "name" ));
        latitudeLocCol.setCellValueFactory( new PropertyValueFactory<>( "latitude" ));
        longitudeLocCol.setCellValueFactory( new PropertyValueFactory<>( "longitude" ));
        ratingLocCol.setCellValueFactory( new PropertyValueFactory<>( "rating" ));
        qualityLocCol.setCellValueFactory( new PropertyValueFactory<>( "quality" ));

        idWeatherCol.setCellValueFactory( new PropertyValueFactory<>( "wID" ));
        nameWeatherCol.setCellValueFactory( new PropertyValueFactory<>( "name" ));
        latitudeWeatherCol.setCellValueFactory( new PropertyValueFactory<>( "latitude" ));
        longitudeWeatherCol.setCellValueFactory( new PropertyValueFactory<>( "longitude" ));
        precipitationWeatherCol.setCellValueFactory( new PropertyValueFactory<>( "precipitation" ));
        windWeatherCol.setCellValueFactory( new PropertyValueFactory<>( "wind_mph" ));
        temperatureWeatherCol.setCellValueFactory( new PropertyValueFactory<>( "temperature" ));
        visibilityWeatherCol.setCellValueFactory( new PropertyValueFactory<>( "visibility" ));
        dateWeatherCol.setCellValueFactory( new PropertyValueFactory<>( "date" ));

        idGaugeCol.setCellValueFactory( new PropertyValueFactory<>( "gID" ));
        nameGaugeCol.setCellValueFactory( new PropertyValueFactory<>( "name" ));
        latitudeGaugeCol.setCellValueFactory( new PropertyValueFactory<>( "latitude" ));
        longitudeGaugeCol.setCellValueFactory( new PropertyValueFactory<>( "longitude" ));
        flowRateGaugeCol.setCellValueFactory( new PropertyValueFactory<>( "flowRate" ));
        flowLevelGaugeCol.setCellValueFactory( new PropertyValueFactory<>( "flowLevel" ));
        dateGaugeCol.setCellValueFactory( new PropertyValueFactory<>( "date" ));




    }


    @FXML
    protected void handleRateButtonAction(ActionEvent event) {
        LocationInterface locationInterface = new LocationTester();
        float rating = 0;
        try {
            rating = Float.parseFloat(rateField.getText());
            if (rating > 5)
            {
                rating = 5;
            } else if (rating < 0) {
                rating = 0;
            }
        } catch (NumberFormatException e) {
            rateField.clear();
            rateField.setPromptText("Rating must be a numerical value");
            return;
        }
        locationInterface.rateLocation(location, rating, mainApp.getUser());
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

        LocationInterface locationInterface = new LocationTester();

        final ObservableList<WeatherData> itemsWeather = FXCollections.observableArrayList(locationInterface.getWeatherStations(location));
        weatherTable.setItems(itemsWeather);

        final ObservableList<GaugeData> itemsGauge = FXCollections.observableArrayList(locationInterface.getGauges(location));
        gaugeTable.setItems(itemsGauge);
    }
}
