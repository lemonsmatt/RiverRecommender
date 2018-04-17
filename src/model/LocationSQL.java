package model;

import application.Main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationSQL implements LocationInterface {
	private Database db;

	public LocationSQL() {
		this.db = Main.getDatabase();
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
				Float RiverRelevantRadius = rs.getFloat("RiverRelevantRadius");
				Float WeatherRelevantRadius = rs.getFloat("WeatherRelevantRadius");
				String email = rs.getString("Email");
				float radius = 0.f;
				if (RiverRelevantRadius == -1) {
					radius = WeatherRelevantRadius;
				} else {
					radius = RiverRelevantRadius;
				}
				Location location = new Location(name, latitude, longitude, radius, email);
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
				Float RiverRelevantRadius = rs.getFloat("RiverRelevantRadius");
				Float WeatherRelevantRadius = rs.getFloat("WeatherRelevantRadius");
				String email = rs.getString("Email");
				float radius = 0;
				if (RiverRelevantRadius == -1) {
					radius = WeatherRelevantRadius;
				} else {
					radius = RiverRelevantRadius;
				}
				Location location = new Location(name, latitude, longitude, radius, email);
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
}
