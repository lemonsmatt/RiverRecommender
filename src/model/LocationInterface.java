package model;

import java.util.List;

public interface LocationInterface {

    //Return of list of locations that have been validated. Uses the ID, name, lat, long
    public List<Location> getValidatedLocations();

    // Return a list of locaitons that have been validated. all fields on the Location object but delete are expected to be completed.
    public List<Location> getValidatedLocationsFull();

    // Return a list of locations that have been validated. All fields on the location object but delete are expected to be completed.
    // Additionally only locations that fit the filter requirements should be shown.
    // Latitude and longitude should be within the radius given. 0 radius means perfect match. -1 radius means ignore latitude and longitude requirements.
    // name should be contained in the name of the location ( like "%name%")
    // id should be exact. -1 means anything
    public List<Location> getValidatedLocationsFull(float latitude, float longitude, float radius, String name);

    // Return a list of locations that have not been validated. Uses the ID, name, lat, long
    public List<Location> getUnvalidatedLocations();

    // Validates the loc
    boolean validateLocation(Location loc);

    //Adds a location
    public boolean addLocation(Location loc);

    //Remove a location
    public boolean removeLocation(Location loc);

    //returns filled out gauges that are connect to the given location
    public List<GaugeData> getGauges(Location loc);

    //returns filled out weather stations that are connect to the given location
    public List<WeatherData> getWeatherStations(Location loc);

    //adds a rating for a given location
    public void rateLocation(Location location, Float rating, User user);
}
