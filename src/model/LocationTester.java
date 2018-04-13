package model;

import java.util.List;

public class LocationTester implements LocationInterface {

    @Override
    public List<Location> getValidatedLocations() {
        return null;
    }

    @Override
    public List<Location> getUnvalidatedLocations() {
        return null;
    }

    @Override
    public boolean addLocation(Location loc) {
        return false;
    }

    @Override
    public boolean removeLocation(Location loc) {
        return false;
    }
}
