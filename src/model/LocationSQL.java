package model;

import application.Main;

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
			float latitude, longitude, avgRating, radius;
			try {
				ResultSet rs = db.queryServer(query);
				while (rs.next()) {
					name = rs.getString("Name");
					LID = rs.getInt("LID");
					latitude = rs.getFloat("Lat");
					longitude = rs.getFloat("Long");
					avgRating = rs.getFloat("Avgrating");
					radius = rs.getFloat("RiverRelevantRadius");
					madeBy = rs.getString("CreatedBy");
					validatedBy = rs.getBoolean("ValidatedBy");
					locList.add(new Location(name, LID, latitude, longitude, avgRating, radius, madeBy, validatedBy));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public List<Location> getValidatedLocations() {
		String query = "SELECT Name, Lat, Long, RiverRelevantRadius, WeatherRelevantRadius, Email From dbo.Location WHERE ValidatedBy IS NOT NULL";
		List<Location> list = new ArrayList<>();
		try {
			ResultSet rs = db.queryServer(query);
			while (rs.next()) {
				String name = rs.getString("Name");
				Float latitude = rs.getFloat("Lat");
				Float longitude = rs.getFloat("Long");
				String email = rs.getString("Email");
				Float riverRelevantRadius = rs.getFloat("RiverRelevantRadius");
				Float weatherRelevantRadius = rs.getFloat("WeatherRelevantRadius");
				Location location = new Location(name, latitude, longitude, riverRelevantRadius, email);
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
		String query = "SELECT Name, Lat, Long, RiverRelevantRadius, WeatherRelevantRadius, Email From dbo.Location WHERE ValidatedBy IS NULL";
		List<Location> list = new ArrayList<>();
		try {
			ResultSet rs = db.queryServer(query);
			while (rs.next()) {
				String name = rs.getString("Name");
				Float latitude = rs.getFloat("Lat");
				Float longitude = rs.getFloat("Long");
				String email = rs.getString("Email");
				Float riverRelevantRadius = rs.getFloat("RiverRelevantRadius");
				Float weatherRelevantRadius = rs.getFloat("WeatherRelevantRadius");
				Location location = new Location(name, latitude, longitude, riverRelevantRadius, email);
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
		String query = "INSERT INTO dbo.Location (LID, Name, Lat, Long, AvgRating, RiverRelevantRadius, WeatherRelevantRadius, CreatedBy) VALUES (" + loc.getID() + ", '" + loc.getName() + "', " +  loc.getLatitude() + ", " + loc.getLongitude() + ", " + loc.getRating() + "', " + loc.getRadius() + ", " + loc.getRadius() + ", '" + loc.getMadeBy() + "' );";
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
	public List<GaugeData> getGauges(Location loc) {
	    //Incomplete
		String query = "SELECT * FROM dbo.RiverData INNER JOIN dbo.RiverGauge ON dbo.RiverData.GID = dbo.RiverData.GID " +
				"WHERE dbo.RiverData.GID = dbo.RiverData.GID;";
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
		return null;
	}
}
