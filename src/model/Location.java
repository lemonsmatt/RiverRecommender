package model;

import javafx.beans.property.*;

public class Location {
    private StringProperty name = new SimpleStringProperty();
    private IntegerProperty id = new SimpleIntegerProperty();
    private FloatProperty latitude = new SimpleFloatProperty();
    private FloatProperty longitude = new SimpleFloatProperty();
    private FloatProperty rating = new SimpleFloatProperty();
    private FloatProperty radius = new SimpleFloatProperty();
    private BooleanProperty validated = new SimpleBooleanProperty();
    private BooleanProperty delete = new SimpleBooleanProperty();
    private StringProperty madeBy = new SimpleStringProperty();
    private FloatProperty quality = new SimpleFloatProperty();
    private StringProperty weather = new SimpleStringProperty();
    private FloatProperty rain = new SimpleFloatProperty();
    private FloatProperty flowDelta = new SimpleFloatProperty();
    private FloatProperty heightDelta = new SimpleFloatProperty();

    public Location(String name, float  latitude, float longitude, float radius, String madeBy)
    {
        this(name, -1, latitude, longitude, -1, radius, madeBy, false);
    }

    public Location(String name, int id, float latitude, float longitude, float rating, float radius, String madeBy, boolean validated) {
        this.name.set(name);
        this.id.set(id);
        this.latitude.set(latitude);
        this.longitude.set(longitude);
        this.rating.set(rating);
        this.radius.set(radius);
        this.madeBy.set(madeBy);
        this.validated.set(validated);
    }

    public int getID() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public float getLatitude() {
        return latitude.get();
    }

    public float getLongitude() {
        return longitude.get();
    }

    public float getRating() {
        return rating.get();
    }

    public float getRadius() {
        return radius.get();

    }

    public String getMadeBy() {
        return madeBy.get();
    }

    public boolean isDelete() {
        return delete.get();
    }

    public StringProperty nameProperty() {return  name;}

    public IntegerProperty idProperty() {
        return id;
    }

    public BooleanProperty deleteProperty() {
        return delete;
    }

    public FloatProperty ratingProperty() {
        return rating;
    }

    public FloatProperty radiusProperty() {
        return radius;
    }

    public FloatProperty latitudeProperty() {
        return latitude;
    }

    public FloatProperty longitudeProperty() {
        return longitude;
    }

    public boolean isValidated() {
        return validated.get();
    }

    public BooleanProperty validatedProperty() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated.set(validated);
    }

    public float getQuality() {
        return quality.get();
    }

    public FloatProperty qualityProperty() {
        return quality;
    }

    public void setQuality(float quality) {
        this.quality.set(quality);
    }

    public String getWeather() {
        return weather.get();
    }

    public StringProperty weatherProperty() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather.set(weather);
    }

    public float getRain() {
        return rain.get();
    }

    public FloatProperty rainProperty() {
        return rain;
    }

    public void setRain(float rain) {
        this.rain.set(rain);
    }

    public float getFlowDelta() {
        return flowDelta.get();
    }

    public FloatProperty flowDeltaProperty() {
        return flowDelta;
    }

    public void setFlowDelta(float flowDelta) {
        this.flowDelta.set(flowDelta);
    }

    public float getHeightDelta() {
        return heightDelta.get();
    }

    public FloatProperty heightDeltaProperty() {
        return heightDelta;
    }

    public void setHeightDelta(float heightDelta) {
        this.heightDelta.set(heightDelta);
    }

    @Override
    public String toString() {
        return "Location{" +
                "name=" + name +
                ", id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", rating=" + rating +
                ", radius=" + radius +
                ", validated=" + validated +
                ", madeBy=" + madeBy +
                '}';
    }
}
