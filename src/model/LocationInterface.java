package model;

import java.util.List;

public interface LocationInterface {

    public List<Location> getValidatedLocations();

    public List<Location> getValidatedLocationsFull();

    public List<Location> getUnvalidatedLocations();

    public boolean addLocation(Location loc);

    public boolean removeLocation(Location loc);

    public List<Gauge> getGauges(Location loc);

    public List<WeatherStation> getWeatherStations(Location loc);
}
