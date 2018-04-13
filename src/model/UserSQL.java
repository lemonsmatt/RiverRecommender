package model;

public class UserTester implements UserInterface {

    @Override
    public boolean isAdmin(String email, String password) {
		String query = "SELECT IsAdmin FROM dbo.[User] WHERE Email = " + email + " Password = " + password + ";";  
		statement.executeUpdate(query);
		
		boolean ret = false;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				ret = rs.getString("IsAdmin");
			}
			
			return ret == "True" ? true : false;
		}
        return false;
    }

    @Override
    public boolean isValidUser(String email, String password) {
		String query = "SELECT BannedBy FROM dbo.[User] WHERE Email = " + email + " Password = " + password + ";";  
		statement.executeUpdate(query);
		
		boolean ret = false;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				ret = rs.getString("BannedBy");
			}
			
			return ret == null ? true : false;
		}
        return false;
    }

    @Override
    public boolean banUser(String email, String adminUserName) {
		//Do we need to know admin who banned the user? (BannedBy) 
		
		String query = "UPDATE dbo.[User] SET BannedBy = " + adminUserName + " WHERE Email = " + email = ";";  
		statement.executeUpdate(query);
		
		boolean ret = false;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				ret = rs.getString("BannedBy");
			}
			
			return ret != null ? true : false;
		}
        return false;
    }
}
