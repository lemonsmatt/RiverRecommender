package model;

import java.util.ArrayList;
import java.util.List;

public class LocationTester implements LocationInterface {

    public static List<Location> locList = new ArrayList<Location>();

    public LocationTester() {
        if (locList.isEmpty())
        {
            locList.add(new Location("Utah", 1,32, 71, 3,2, "lemons"));
            locList.add(new Location("Zion", 2,35, 61, 1,9, "lemons"));
            locList.add(new Location("Springdale", 3,32, 11, 3,3, "lemons"));
            locList.add(new Location("Kolob", 4,12, 81, 2,5, "lemons"));
        }
    }

        @Override
    public List<Location> getValidatedLocations() {
        return locList;
    }

    @Override
    public List<Location> getUnvalidatedLocations() {
        return null;
    }

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
}
