package model;

import java.util.List;

public class LocationSQL implements LocationInterface {

    @Override
    public List<Location> getValidatedLocations() {
	String query = "SELECT Name, AvgRating From dbo.Location WHERE ValidatedBy != NULL;";
	ResultSet rs = stmt.executeQuery(query);

	List<Location> list = new ArrayList<>();
	Location location;
	while(rs.next()){
		location = new Location();

		String name = rs.getString("Name");
		String latitude = rs.getString("latitude");
		String longitude = rs.getString("longitude");
		String radius = rs.getString("radius");

		location.name = name;
		location.latitude = latitude;
		location.longitude = longitude;
		location.radius = radius;

		list.add(location)
	}

	rs.close();
        return list;
    }

    @Override
    public List<Location> getUnvalidatedLocations() {
	String query = "SELECT Name, AvgRating From dbo.Location WHERE ValidatedBy == NULL;";
	ResultSet rs = stmt.executeQuery(query);

	List<Location> list = new ArrayList<>();
	Location location;
	while(rs.next()){
		location = new Location();

		String name = rs.getString("Name");
		String latitude = rs.getString("latitude");
		String longitude = rs.getString("longitude");
		String radius = rs.getString("radius");

		location.name = name;
		location.latitude = latitude;
		location.longitude = longitude;
		location.radius = radius;

		list.add(location);
	}

	rs.close();
        return list;
    }

    @Override		
    public boolean addLocation(Location loc) {
		
	String LID = loc.id;
	String name = loc.name;
	String radius = loc.radius;		
	String query = "INSERT INTO dbo.Location (LID, Name, RiverRelevantRadius) VALUES (" + loc.id + ", " + loc.name ", " + loc.radius + ");";
	statement.executeUpdate(query);
	//Try and fail Not completed
        return false;
    }

    @Override
    public boolean removeLocation(Location loc) {
	String query = "DELETE * FROM dbo.Location WHERE id = loc.id;";
	statement.executeUpdate(query);
        return false;
    }
}
