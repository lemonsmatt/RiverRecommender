package model;

import java.util.ArrayList;
import java.util.List;

public class LocationTester implements LocationInterface {

    public static List<Location> locList = new ArrayList<Location>();

    public LocationTester() {
        if (locList.isEmpty())
        {
            locList.add(new Location("Utah", 1,32, 71, 4, 3,2, "lemons", false));
            locList.add(new Location("Zion", 2,35, 4, 61, 1,9, "lemons", false));
            locList.add(new Location("Springdale", 3,32,4 ,11, 3,3, "lemons", true));
            locList.add(new Location("Kolob", 4,12, 81,44, 2,5, "lemons", false));
        }
    }

        @Override
    public List<Location> getValidatedLocations() {
        List<Location> out = new ArrayList<Location>();
        for (Location loc: locList) {
            if (loc.isValidated()) {
                out.add(loc);
            }
        }
        return out;
    }

    @Override
    public List<Location> getUnvalidatedLocations() {
        List<Location> out = new ArrayList<Location>();
        for (Location loc: locList) {
            if (!loc.isValidated()) {
                out.add(loc);
            }
        }
        return out;    }

    @Override
    public boolean addLocation(Location loc) {
        if (loc.getName() !=null){
            return  true;
        }
        return false;
    }

    @Override
    public boolean removeLocation(Location loc) {
        return locList.remove(loc);
    }

    @Override
    public List<Location> getValidatedLocationsFull() {
        return getValidatedLocations();
    }

    @Override
    public boolean validateLocation(Location loc, User user) {
        return false;
    }

    @Override
    public List<GaugeData> getGauges(Location loc) {
        return null;
    }

    @Override
    public List<WeatherData> getWeatherStations(Location loc) {
        return null;
    }

    @Override
    public void rateLocation(Location location, Float rating, User user) {

    }

    @Override
    public List<Location> getValidatedLocationsFull(float latitude, float longitude, float radius, String name) {
        return null;
    }
}
