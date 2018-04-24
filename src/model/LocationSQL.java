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

        String query = "SELECT Name, LID, Lat, Long, Avgrating, RiverRelevantRadius, WeatherRelevantRadius, CreatedBy, " + "ValidatedBy FROM dbo.Location;";
        if (locList.isEmpty()) {

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
    public boolean validateLocation(Location loc, User usr) {
        String query = "UPDATE dbo.Location SET ValidatedBy = '" + usr.getEmail() + "' FROM dbo.Location " +
                "WHERE " +
                "LID = " + loc.getID() + ";";
        db.queryServerMulti(query);
//        query = "SELECT ValidatedBy FROM dbo.Location WHERE LID = " + loc.getID() + ";";
//        ResultSet rs = db.queryServer(query);
        return true;
//
//        try {
//            String test = rs.getString("ValidatedBy");
//            return (test.contains(usr.getEmail()));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
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
    public List<Location> getUnvalidatedLocations() {
        String query = "SELECT LID, Name, Lat, Long, RiverRelevantRadius, WeatherRelevantRadius, CreatedBy " +
                "From dbo.Location WHERE ValidatedBy IS NULL";
        List<Location> list = new ArrayList<>();
        try {
            ResultSet rs = db.queryServer(query);
            while (rs.next()) {
                int LID = rs.getInt("LID");
                String name = rs.getString("Name");
                Float latitude = rs.getFloat("Lat");
                Float longitude = rs.getFloat("Long");
                String email = rs.getString("CreatedBy");
                Float riverRelevantRadius = rs.getFloat("RiverRelevantRadius");
                Float weatherRelevantRadius = rs.getFloat("WeatherRelevantRadius");
                Location location = new Location(name, LID, latitude, longitude, riverRelevantRadius,
                        weatherRelevantRadius, email, false);
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

        //if time allows, change like (https://stackoverflow
        // .com/questions/1812891/java-escape-string-to-prevent-sql-injection)
        String query = "INSERT INTO dbo.Location (LID, Name, Lat, Long, AvgRating, RiverRelevantRadius, " +
                "WeatherRelevantRadius, CreatedBy) VALUES (" + loc.getID() + ", '" + loc.getName() + "', "
                + loc.getLatitude() + ", " + loc.getLongitude() + ", " + (int) loc.getRating() + ", " +
                (int) loc.getRadiusGauge() + ", " + (int) loc.getRadiusWeather() + ", '" + loc.getMadeBy() + "' );";
        db.queryServerMulti(query);
        ResultSet rs;
        query = "SELECT LID, RiverRelevantRadius, WeatherRelevantRadius FROM dbo.Location WHERE LID = " + loc.getID();
        rs = db.queryServer(query);
        boolean proceedToNext = false;
        float riverRadius = loc.getRadiusGauge();
        float weatherRadius = loc.getRadiusWeather();
        try {
            while (rs.next()) {
                Integer id = rs.getInt("LID");
                //riverRadius = rs.getInt("RiverRelevantRadius");
                //weatherRadius = rs.getInt("WeatherRelevantRadius");
                proceedToNext = id == loc.getID() ? true : false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!proceedToNext) {
            return false;
        }

        query = "SELECT * FROM dbo.RiverGauge;";
        rs = db.queryServer(query);
        try {
            while (rs.next()) {
                float temp_lat = rs.getFloat("Lat");
                float temp_long = rs.getFloat("Long");
                int gID = rs.getInt("GID");
                double distance = Math.hypot(loc.getLatitude() - temp_lat, loc.getLongitude() - temp_long);
                if (distance <= riverRadius) {
                    query = "INSERT INTO dbo.RelevantGauge (LID, GID) VALUES (" + loc.getID() + ", " + gID
                            + ");";
                    db.queryServerMulti(query);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        query = "SELECT * FROM dbo.WeatherStation;";
        rs = db.queryServer(query);
        try {
            while (rs.next()) {
                float temp_lat = rs.getFloat("Lat");
                float temp_long = rs.getFloat("Long");
                int wID = rs.getInt("WID");
                double distance = Math.hypot(loc.getLatitude() - temp_lat, loc.getLongitude() - temp_long);
                if (distance <= weatherRadius) {
                    query = "INSERT INTO dbo.RelevantStations (LID, WID) VALUES (" + loc.getID() + ", " + wID
                            + ");";
                    db.queryServerMulti(query);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean removeLocation(Location loc) {
        String removeGauge = "DELETE FROM dbo.RelevantGauge WHERE LID = " + loc.getID() + ";";
        db.queryServerMulti(removeGauge);
        String removeWeather = "DELETE FROM dbo.RelevantStations WHERE LID = " + loc.getID() + ";";
        db.queryServerMulti(removeWeather);
        String query = "DELETE FROM dbo.Location WHERE LID = " + loc.getID() + ";";
        db.queryUpdate(query);
        query = "SELECT LID FROM dbo.Location WHERE LID = " + loc.getID() + ";";
        ResultSet rs = db.queryServer(query);
        try {
            boolean empty = true;
            while (rs.next()) {
                empty = false;
            }
            return !empty;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public List<Location> getValidatedLocationsFull() {
        String query = "SELECT LID, Lat, Long, Name, AvgRating, RiverRelevantRadius, WeatherRelevantRadius," +
                " CreatedBy, " + "ValidatedBy " +
                "From dbo.Location WHERE ValidatedBy IS NOT NULL";
        List<Location> list = new ArrayList<>();
        try {
            ResultSet rs = db.queryServer(query);
            while (rs != null && rs.next()) {
                int LID = rs.getInt("LID");
                Float longitude = rs.getFloat("Long");
                Float latitude = rs.getFloat("Lat");
                String name = rs.getString("Name");
                float avgRating = rs.getFloat("AvgRating");
                String email = rs.getString("CreatedBy");
                Float riverRelevantRadius = rs.getFloat("RiverRelevantRadius");
                Float weatherRelevantRadius = rs.getFloat("WeatherRelevantRadius");
                String validatedBy = rs.getString("ValidatedBy");
                Location location = new Location(name, LID, latitude, longitude, avgRating,
                        riverRelevantRadius,
                        weatherRelevantRadius, email, validatedBy != null);
                list.add(location);
            }
        } catch (SQLException e) {
            return null;
        }
        return list;
    }

    @Override
    public List<Location> getValidatedLocationsFull(float latitude, float longitude, float radius, String name) {
        String query = "SELECT LID, Lat, Long, Name, AvgRating, RiverRelevantRadius, WeatherRelevantRadius," +
                " CreatedBy, " + "ValidatedBy " +
                "From dbo.Location WHERE ValidatedBy IS NOT NULL and Lat BETWEEN " + (latitude - (0.5 *
                radius)) + " AND " + (latitude + (0.5 * radius)) + " and Long BETWEEN " + (longitude - (0.5
                * radius)) + " AND " + (longitude + (0.5) * radius) + " and (Name LIKE '%" + name + "%');";
        List<Location> list = new ArrayList<>();
        try {
            ResultSet rs = db.queryServer(query);
            while (rs != null && rs.next()) {
                int LID = rs.getInt("LID");
                Float varLong = rs.getFloat("Long");
                Float varLat = rs.getFloat("Lat");
                String tempName = rs.getString("Name");
                float avgRating = rs.getFloat("AvgRating");
                String email = rs.getString("CreatedBy");
                Float riverRelevantRadius = rs.getFloat("RiverRelevantRadius");
                Float weatherRelevantRadius = rs.getFloat("WeatherRelevantRadius");
                String validatedBy = rs.getString("ValidatedBy");
                Location location = new Location(tempName, LID, varLat, varLong, avgRating,
                        riverRelevantRadius,
                        weatherRelevantRadius, email, validatedBy != null);
                list.add(location);
            }
        } catch (SQLException e) {
            return null;
        }
        for (Location loc: list) {
            query = "SELECT AVG(RRating) FROM dbo.RelevantGauge INNER JOIN dbo.RiverData ON dbo.RelevantGauge.GID = dbo.RiverData.GID WHERE dbo.LID = " + loc.getID() + ";";
            try {
                ResultSet rs = db.queryServer(query);
                float quality = 0;
                int total = 0;
                
                while (rs != null && rs.next()) {
                    quality += rs.getInt("RRating");
                    total++;
                }
                loc.setQuality(quality/total);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return list;
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
                Date date = rs.getDate("Date");
                GaugeData gauge = new GaugeData(gid, name, latitude, longitude, flowRate, flowLevel, date);
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

                WeatherData weatherData = new WeatherData(wID, name, latitude, longitude, date, precipitation, wind_mph, temperature, visibility);
                weatherDataList.add(weatherData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return weatherDataList;

    }

    @Override
    public void rateLocation(Location location, Float rating, User user) {

        String query = "SELECT LID FROM dbo.[Rating] WHERE Email = '" + user.getEmail() + "';";
        ResultSet rs = db.queryServer(query);

        if (rs == null) {
            query = "INSERT INTO dbo.[Rating] (Email, LID, Rating) VALUES ('" + user.getEmail() + "', " + location.getID() + ", " + rating + ");";
        } else {
            query = "UPDATE dbo.[Rating] SET Rating = " + rating + " WHERE Email = '" + user.getEmail() +
                    "' " +
                    "and LID =" +
                    " " +
                    location.getID() + ";";
        }

        db.queryServerMulti(query);
        String find = "SELECT AVG(Rating) FROM dbo.[Rating] WHERE LID = " + location.getID() + ";";
        rs = db.queryServer(find);
        try {
            if (rs.next()) {
                //System.out.println("avg temp is " + rs.getFloat(1));
                query = "UPDATE dbo.Location SET Avgrating = " + rs.getFloat(1) + " WHERE LID = " +
                        location.getID() + ";";
                db.queryServerMulti(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }
}
