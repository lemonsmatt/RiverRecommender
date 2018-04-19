package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import model.*;

import java.io.IOException;

public class createLocationController extends  Controller {
    @FXML
    private TextField fieldName;
    @FXML
    private TextField fieldLongitude;
    @FXML
    private TextField fieldLatitude;
    @FXML
    private TextField fieldGaugeRadius;
    @FXML
    private TextField fieldWeatherRadius;

    private LocationInterface locationInterface;

    @FXML
    public void initialize() {
        locationInterface = mainApp.getLocationInterface();

    }

    @FXML protected void handleCancelButtonAction(ActionEvent event) throws IOException {
        mainApp.showScene("main");
    }

    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        float latitude = 0;
        float longitude = 0;
        float radiusGauge = 0;
        float radiusWeather = 0;
        boolean end = false;
        try {
            latitude = Float.parseFloat(fieldLatitude.getText());
        } catch (NumberFormatException e)
        {
            fieldLatitude.clear();
            fieldLatitude.setPromptText("Must be a numerical value");
            end= true;
        }
        try {
            longitude = Float.parseFloat(fieldLongitude.getText());
        } catch (NumberFormatException e)
        {
            fieldLongitude.clear();
            fieldLongitude.setPromptText("Must be a numerical value");
            end= true;
        }
        try {
            radiusGauge = Float.parseFloat(fieldGaugeRadius.getText());
        } catch (NumberFormatException e)
        {
            fieldGaugeRadius.clear();
            fieldGaugeRadius.setPromptText("Must be a numerical value");
            end= true;
        }
        try {
            radiusWeather = Float.parseFloat(fieldWeatherRadius.getText());
        } catch (NumberFormatException e)
        {
            fieldWeatherRadius.clear();
            fieldWeatherRadius.setPromptText("Must be a numerical value");
            end= true;
        }
        if (end)
        {
            return;
        }
        Location loc = new Location(fieldName.getText(), latitude, longitude, radiusGauge, radiusWeather, mainApp.getUser().getEmail());
        if (locationInterface.addLocation(loc))
        {
            mainApp.showScene("main");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Could not add location", ButtonType.OK);
            alert.showAndWait();
        }
    }
}
