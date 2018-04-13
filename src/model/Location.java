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


}
