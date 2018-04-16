package model;

import application.Main;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSQL implements UserInterface {
	private Database db;
	public UserSQL() {
		db = Main.getDatabase();
	}
	@Override
	public boolean register(String email, String password) {
		String check = "SELECT Email FROM dbo.[User] WHERE Email = '" + email + "';";
		try {
			ResultSet rs = db.queryServer(check);
			while (rs.next()) {
				String ret = rs.getString("Email");
				if (ret.equals(email)) {
					System.out.println("DUPLICATE EMAIL");
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String query = "INSERT INTO dbo.[User] (UserName, Password, Email, IsAdmin) VALUES ('" + email + "', '" + password + "', '" + email + "', " + "0" + ");";
		db.queryServerMulti(query);
		System.out.println("REG SUCCESS");
		return true;
	}

	@Override
	public boolean isAdmin(String email, String password) {
		String query = "SELECT IsAdmin FROM dbo.[User] WHERE Email = '" + email + "' and Password = '" + password + "';";
		boolean ret = false;
		try {
			ResultSet rs = db.queryServer(query);
			while (rs.next()) {
				ret = rs.getBoolean("IsAdmin");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public boolean isValidUser(String email, String password) {
		//SELECT * FROM dbo.[User] WHERE Email = 'Eric@matt' and Password = 'Matt'
		String query = "SELECT UserName, BannedBy FROM dbo.[User] WHERE Email = '" + email + "' and Password = '" + password + "';";
		if ((password.equals("")) || (email.equals(""))) {
			return false;
		}
		String ret;
		try {
			ResultSet rs = db.queryServer(query);
			while (rs.next()) {
				if (!rs.getString("UserName").equals("")) {
					ret = rs.getString("BannedBy");
					// return ret.equals("");
					return ret.equals("Chase");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean banUser(String email, String adminUserName) {
		//Do we need to know admin who banned the user? (BannedBy)
		String checkingQuery = "SELECT UserName, BannedBy FROM dbo.[User] WHERE Email = '" + email + "';";
		try {
			ResultSet rs = db.queryServer(checkingQuery);
			while (rs.next()) {
				if (rs.getString("UserName").equals("")) {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String query = "UPDATE dbo.[User] SET BannedBy = '" + adminUserName + "' WHERE Email = '" + email + "';";
		db.queryServer(query);
		return true;
	}
}
