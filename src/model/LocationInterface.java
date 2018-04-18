package model;

import java.util.List;

public interface LocationInterface {

    public List<Location> getValidatedLocations();

    public List<Location> getValidatedLocationsFull();

    public List<Location> getUnvalidatedLocations();

    boolean validateLocation(Location loc);

    public boolean addLocation(Location loc);

    public boolean removeLocation(Location loc);

    public List<GaugeData> getGauges(Location loc);

    public List<WeatherData> getWeatherStations(Location loc);
}
