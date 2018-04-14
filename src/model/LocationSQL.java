package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationSQL implements LocationInterface {
	private Database db;

	private LocationSQL(Database db) {
		this.db = db;
	}

	@Override
	public List<Location> getValidatedLocations() {

		String query = "SELECT Name, AvgRating From dbo.Location WHERE ValidatedBy != NULL;";

		List<Location> list = new ArrayList<>();
		try {
			ResultSet rs = db.queryServer(query);
			while (rs.next()) {
				String name = rs.getString("Name");
				String latitude = rs.getString("latitude");
				String longitude = rs.getString("longitude");
				String radius = rs.getString("radius");
				Location location = new Location(name, Float.parseFloat(latitude), Float.parseFloat(longitude), Float.parseFloat(radius));
				list.add(location);
			}
		} catch (SQLException e) {
			return null;
		}
		return list;
	}

	@Override
	public List<Location> getUnvalidatedLocations() {
		String query = "SELECT Name, AvgRating From dbo.Location WHERE ValidatedBy == NULL;";
		List<Location> list = new ArrayList<>();
		try {
			ResultSet rs = db.queryServer(query);
			while (rs.next()) {
				String name = rs.getString("Name");
				String latitude = rs.getString("latitude");
				String longitude = rs.getString("longitude");
				String radius = rs.getString("radius");

				Location location = new Location(name, Float.parseFloat(latitude), Float.parseFloat(longitude), Float.parseFloat(radius));
				list.add(location);
			}
		} catch (SQLException e) {
			return null;
		}
		return list;
	}

	@Override
	public boolean addLocation(Location loc) {
		String query = "INSERT INTO dbo.Location (LID, Name, RiverRelevantRadius) VALUES (" + loc.getID(loc) + ", " + loc.getName(loc) + ", " + loc.getRadius(loc) + ");";
		ResultSet rs = db.queryServer(query);
		//Try and fail Not completed
		return rs != null;
	}

	@Override
	public boolean removeLocation(Location loc) {
		String query = "DELETE * FROM dbo.Location WHERE id = " + loc.getID(loc)+ ";";
		ResultSet rs = db.queryServer(query);
		return rs != null;
	}
}
