package model;

import javafx.beans.property.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class GaugeData {

    IntegerProperty gID = new SimpleIntegerProperty();
    StringProperty name = new SimpleStringProperty();
    FloatProperty latitude = new SimpleFloatProperty();
    FloatProperty longitude = new SimpleFloatProperty();
    FloatProperty flowRate = new SimpleFloatProperty();
    FloatProperty flowlevel = new SimpleFloatProperty();
    StringProperty date = new SimpleStringProperty();

    public int getgID() {
        return gID.get();
    }

    public IntegerProperty gIDProperty() {
        return gID;
    }

    public void setgID(int gID) {
        this.gID.set(gID);
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

    public float getFlowRate() {
        return flowRate.get();
    }

    public FloatProperty flowRateProperty() {
        return flowRate;
    }

    public void setFlowRate(float flowRate) {
        this.flowRate.set(flowRate);
    }

    public float getFlowlevel() {
        return flowlevel.get();
    }

    public FloatProperty flowlevelProperty() {
        return flowlevel;
    }

    public void setFlowlevel(float flowlevel) {
        this.flowlevel.set(flowlevel);
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

    public GaugeData(int gID, String name, float latitude, float longitude, float flowRate, float flowlevel, String date) {
        this.gID.set(gID);
        this.name.set(name);
        this.latitude.set(latitude);
        this.longitude.set(longitude);
        this.flowRate.set(flowRate);
        this.flowlevel.set(flowlevel);
        DateFormat dateFormat = new SimpleDateFormat("YYYYMMDD");
        this.date.set(dateFormat.format(date));
    }
}
