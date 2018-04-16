package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSQL implements UserInterface {
	private Database db;

	public UserSQL()
	{
		db = new Database();
	}

	@Override
	public boolean isAdmin(String email, String password) {
		String query = "SELECT IsAdmin FROM dbo.[User] WHERE Email = " + email + " Password = " + password + ";";

		boolean ret = false;
		try {
			ResultSet rs = db.queryServer(query);
			while (rs.next()) {
				ret = rs.getBoolean("IsAdmin");
			}

			return ret;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean isValidUser(String email, String password) {
		String query = "SELECT BannedBy FROM [User] WHERE Email = " + email + " Password = " + password + ";";

		String  ret = null;
		try {
			ResultSet rs = db.queryServer(query);
			while (rs.next()) {
				ret = rs.getString("BannedBy");
			}
			return ret != null;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean banUser(String email, String adminUserName) {
		//Do we need to know admin who banned the user? (BannedBy)

		String query = "UPDATE [User] SET BannedBy = " + adminUserName + " WHERE Email = " + email + ";";
		String ret = null;
		try {
			ResultSet rs = db.queryServer(query);
			while (rs.next()) {
				ret = rs.getString("BannedBy");
			}
			return ret != null;
		} catch (SQLException e) {
			return false;
		}
	}
}