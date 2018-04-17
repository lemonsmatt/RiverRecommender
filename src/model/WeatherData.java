package model;

import javafx.beans.property.*;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class WeatherData {

    private IntegerProperty wID = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private FloatProperty latitude = new SimpleFloatProperty();
    private FloatProperty longitude = new SimpleFloatProperty();
    private StringProperty date = new SimpleStringProperty();
    private FloatProperty precipitation = new SimpleFloatProperty();
    private FloatProperty wind_mph = new SimpleFloatProperty();
    private FloatProperty temperature = new SimpleFloatProperty();
    private FloatProperty visibility = new SimpleFloatProperty();

    public int getwID() {
        return wID.get();
    }

    public IntegerProperty wIDProperty() {
        return wID;
    }

    public void setwID(int wID) {
        this.wID.set(wID);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public float getLatitude() {
        return latitude.get();
    }

    public FloatProperty latitudeProperty() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude.set(latitude);
    }

    public float getLongitude() {
        return longitude.get();
    }

    public FloatProperty longitudeProperty() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude.set(longitude);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public float getPrecipitation() {
        return precipitation.get();
    }

    public FloatProperty precipitationProperty() {
        return precipitation;
    }

    public void setPrecipitation(float precipitation) {
        this.precipitation.set(precipitation);
    }

    public float getWind_mph() {
        return wind_mph.get();
    }

    public FloatProperty wind_mphProperty() {
        return wind_mph;
    }

    public void setWind_mph(float wind_mph) {
        this.wind_mph.set(wind_mph);
    }

    public float getTemperature() {
        return temperature.get();
    }

    public FloatProperty temperatureProperty() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature.set(temperature);
    }

    public float getVisibility() {
        return visibility.get();
    }

    public FloatProperty visibilityProperty() {
        return visibility;
    }

    public void setVisibility(float visibility) {
        this.visibility.set(visibility);
    }

    public WeatherData(int wID, String name, float latitude, float longitude, Date date, float precipitation, float wind_mph, float temperature, float visibility) {
        this.wID.set(wID);
        this.name.set(name);
        this.latitude.set(latitude);
        this.longitude.set(longitude);
        DateFormat dateFormat = new SimpleDateFormat("YYYYMMDD");
        this.date.set(dateFormat.format(date));
        this.precipitation.set(precipitation);
        this.wind_mph.set(wind_mph);
        this.temperature.set(temperature);
        this.visibility.set(visibility);
    }
}
