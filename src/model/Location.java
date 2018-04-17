package model;

import javafx.beans.property.*;

public class Location {
    private StringProperty name = new SimpleStringProperty();
    private IntegerProperty id = new SimpleIntegerProperty();
    private FloatProperty latitude = new SimpleFloatProperty();
    private FloatProperty longitude = new SimpleFloatProperty();
    private FloatProperty rating = new SimpleFloatProperty();
    private FloatProperty radius = new SimpleFloatProperty();
    private BooleanProperty delete = new SimpleBooleanProperty();
    private StringProperty madeBy = new SimpleStringProperty();


    public Location(String name, float  latitude, float longitude, float radius, String madeBy)
    {
        this(name, -1, latitude, longitude, -1, radius, madeBy);
    }

    public Location(String name, int id, float latitude, float longitude, float rating, float radius, String madeBy) {
        this.name.set(name);
        this.id.set(id);
        this.latitude.set(latitude);
        this.longitude.set(longitude);
        this.rating.set(rating);
        this.radius.set(radius);
        this.madeBy.set(madeBy);
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
}
