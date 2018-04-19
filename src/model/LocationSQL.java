package model;

import application.Main;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationSQL implements LocationInterface {
	private Database db;
	public static List<Location> locList = new ArrayList<Location>();

	public LocationSQL() {
		this.db = Main.getDatabase();
		String query = "SELECT Name, LID, Lat, Long, Avgrating, RiverRelevantRadius, CreatedBy, ValidatedBy FROM dbo.Location;";
		if (locList.isEmpty())
		{

			String name, madeBy;
			boolean validatedBy;
			Integer LID;
			float latitude, longitude, avgRating, radiusRiver, radiusWeather;
			try {
				ResultSet rs = db.queryServer(query);
				while (rs.next()) {
					name = rs.getString("Name");
					LID = rs.getInt("LID");
					latitude = rs.getFloat("Lat");
					longitude = rs.getFloat("Long");
					avgRating = rs.getFloat("Avgrating");
					radiusRiver = rs.getFloat("RiverRelevantRadius");
					radiusWeather = rs.getFloat("WeatherRelevantRadius");
					madeBy = rs.getString("CreatedBy");
					validatedBy = rs.getBoolean("ValidatedBy");
					locList.add(new Location(name, LID, latitude, longitude, avgRating, radiusRiver, radiusWeather, madeBy, validatedBy));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean validateLocation(Location loc) {
		String query = "UPDATE ValidatedBy ='" + loc.getMadeBy() +"' FROM dbo.Location WHERE LID = " + loc.getID() + ";";
		db.queryServerMulti(query);
		query = "SELECT ValidatedBy FROM dbo.Location WHERE LID = " + loc.getID() + ";";
		ResultSet rs = db.queryServer(query);
		try {
			return (rs.getString("ValidatedBy").equals(loc.getMadeBy()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Location> getValidatedLocations() {
		String query = "SELECT Name, Lat, Long, RiverRelevantRadius, WeatherRelevantRadius, CreatedBy From dbo.Location WHERE ValidatedBy IS NOT NULL";
		List<Location> list = new ArrayList<>();
		try {
			ResultSet rs = db.queryServer(query);
			while (rs != null && rs.next()) {
				String name = rs.getString("Name");
				Float latitude = rs.getFloat("Lat");
				Float longitude = rs.getFloat("Long");
				String email = rs.getString("CreatedBy");
				Float riverRelevantRadius = rs.getFloat("RiverRelevantRadius");
				Float weatherRelevantRadius = rs.getFloat("WeatherRelevantRadius");
				Location location = new Location(name, latitude, longitude, riverRelevantRadius, weatherRelevantRadius ,email);
				//temp
                //weatherRelevantRadius, email);
				list.add(location);
			}
		} catch (SQLException e) {
			return null;
		}
		return list;
	}

	@Override
	public List<Location> getUnvalidatedLocations() {
		String query = "SELECT Name, Lat, Long, RiverRelevantRadius, WeatherRelevantRadius, CreatedBy From dbo.Location WHERE ValidatedBy IS NULL";
		List<Location> list = new ArrayList<>();
		try {
			ResultSet rs = db.queryServer(query);
			while (rs.next()) {
				String name = rs.getString("Name");
				Float latitude = rs.getFloat("Lat");
				Float longitude = rs.getFloat("Long");
				String email = rs.getString("CreatedBy");
				Float riverRelevantRadius = rs.getFloat("RiverRelevantRadius");
				Float weatherRelevantRadius = rs.getFloat("WeatherRelevantRadius");
				Location location = new Location(name, latitude, longitude, riverRelevantRadius, weatherRelevantRadius, email);
				//temp
                //weatherRelevantRadius, email);
				list.add(location);
			}
		} catch (SQLException e) {
			return null;
		}
		return list;
	}

	@Override
	public boolean addLocation(Location loc) {
		String query = "INSERT INTO dbo.Location (LID, Name, Lat, Long, AvgRating, RiverRelevantRadius, WeatherRelevantRadius, CreatedBy) VALUES (" + loc.getID() + ", '" + loc.getName() + "', " +  loc.getLatitude() + ", " + loc.getLongitude() + ", " + loc.getRating() + "', " + loc.getRadiusGauge() + ", " + loc.getRadiusGauge() + ", '" + loc.getMadeBy() + "' );";
		db.queryServer(query);
		ResultSet rs;
		query = "SELECT LID FROM dbo.Location WHERE LID = " + loc.getID();
		rs = db.queryServer(query);
		try {
			while (rs.next()) {
				Integer id = rs.getInt("LID");
				return id == loc.getID();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeLocation(Location loc) {
		String query = "DELETE * FROM dbo.Location WHERE id = " + loc.getID()+ ";";
		db.queryServer(query);
		query = "SELECT LID FROM dbo.Location WHERE id = " + loc.getID() + ";";
		ResultSet rs = db.queryServer(query);
		try {
			return rs.getInt("LID") == 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

    @Override
    public List<Location> getValidatedLocationsFull() {
        return null;
    }

    @Override
    public List<Location> getValidatedLocationsFull(float latitude, float longitude, float radius, String name, int id) {
        return null;
    }

    @Override
	public List<GaugeData> getGauges(Location loc) {
	    //Incomplete
		String query = "SELECT * FROM dbo.Location INNER JOIN dbo.RelevantGauge ON dbo.Location.LID = dbo.RelevantGauge.LID INNER JOIN dbo.RiverData ON dbo.RelevantGauge.GID = dbo.RiverData.GID;";
		List<GaugeData> gaugeDataList = new ArrayList<>();
		ResultSet rs = db.queryServer(query);
		//String riverQuery = "SELECT * FROM dbo.RiverData WHERE GID = " +  + ;"

		try {
			while (rs.next()) {
				int gid = rs.getInt("GID");
				String name = rs.getString("Name");
				float latitude = rs.getFloat("Lat");
				float longitude = rs.getFloat("Long");
				float flowRate = rs.getFloat("FlowRate");
				float flowLevel = rs.getFloat("FlowLevel");
				String date = rs.getString("Date");
				GaugeData gauge = new GaugeData(gid,name, latitude, longitude, flowRate, flowLevel, date);
				gaugeDataList.add(gauge);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gaugeDataList;

	}

	@Override
	public List<WeatherData> getWeatherStations(Location loc) {
	    List<WeatherData> weatherDataList = new ArrayList<>();
		String query = "SELECT * FROM dbo.Location INNER JOIN dbo.RelevantStations ON dbo.Location.LID = dbo.RelevantStations.LID INNER JOIN dbo.WeatherStation ON dbo.RelevantStations.WID = dbo.WeatherStation.WID INNER JOIN dbo.WeatherData ON dbo.WeatherStation.WID = dbo.WeatherData.WID;";

		try {
			ResultSet rs = db.queryServer(query);
			while (rs.next()) {
				int wID = rs.getInt("WID");
				String name = rs.getString("Name");
				float latitude = rs.getFloat("Lat");
				float longitude = rs.getFloat("Long");
				float precipitation = rs.getFloat("Precipitation");
				float wind_mph = rs.getFloat("wind_mph");
				Date date = rs.getDate("Date");
				float temperature = rs.getFloat("Temperature");
				float visibility = rs.getFloat("Visibility");
				WeatherData weatherData = new WeatherData( wID,  name,  latitude,  longitude,  date,  precipitation,  wind_mph,  temperature,  visibility);
				weatherDataList.add(weatherData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

        return weatherDataList;

	}
}
