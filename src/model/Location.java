package model;

public class Location {
    private String name;
    private int id;
    private float latitude;
    private float longitude;
    private float rating;
    private float radius;

    public Location(String name, float latitude, float longitude, float radius) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

    public Location(String name, int id, float latitude, float longitude, float rating, float radius) {
        this.name = name;
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rating = rating;
        this.radius = radius;
    }

    public int getID(Location location) {
        return location.id;
    }

    public String getName(Location location) {
        return location.name;
    }

    public float getLatitude(Location location) {
        return location.latitude;
    }

    public float getLongitude(Location location) {
        return location.longitude;
    }

    public float getRating(Location location) {
        return location.rating;
    }

    public float getRadius(Location location) {
        return location.radius;

    }


}
