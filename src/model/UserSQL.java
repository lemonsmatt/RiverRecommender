package model;

import application.Main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static model.UserTester.userList;

public class UserSQL implements UserInterface {
	private Database db;
	static List<User> userList = new ArrayList<User>();

	public UserSQL() {
		db = Main.getDatabase();
        String query = "SELECT Email, UserName, password, IsAdmin, BannedBy FROM dbo.[User];";
		String ret1, ret2, ret3, ret5;
		boolean ret4;
		try {
			ResultSet rs = db.queryServer(query);
			while (rs.next()) {
				ret1 = rs.getString("Email");
				ret2 = rs.getString("UserName");
				ret3 = rs.getString("password");
				ret4 = rs.getBoolean("IsAdmin");
				ret5 = rs.getString("BannedBy");
				userList.add(new User(ret1, ret2,ret3, ret4, (ret5 != null), ret5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
			while (rs != null && rs.next()) {
				return (rs.getString("BannedBy")) == null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public boolean banUser(User user, String adminUserName) {
		//Do we need to know admin who banned the user? (BannedBy)

		String checkingQuery = "SELECT UserName, BannedBy FROM dbo.[User] WHERE Email = '" + user.getEmail() + "';";
		ResultSet rs = db.queryServer(checkingQuery);
		if (rs != null) {
			String query = "UPDATE dbo.[User] SET BannedBy = '" + adminUserName + "' WHERE Email = '" + user.getEmail() + "';";
			db.queryServerMulti(query);
			return true;
		}
		return false;
	}

	@Override
	public List<User> getValidUsers() {
		List<User> out = new ArrayList<User>();
		for (User user: userList) {
			if (!user.getBan()) {
				out.add(user);
			}
		}
		return  out;
	}

	@Override
	public List<User> getBannableUsers() {
		List<User> out = new ArrayList<User>();
		for (User user: userList) {
			if (!user.getBan() && !user.isAdmin()) {
				out.add(user);
			}
		}
		return  out;
	}

	@Override
	public boolean addUser(User user) {
		String check = "SELECT Email FROM dbo.[User] WHERE Email = '" + user.getEmail() + "';";
		try {
			ResultSet rs = db.queryServer(check);
			while (rs.next()) {
				String ret = rs.getString("Email");
				if (ret.equals(user.getEmail())) {
					System.out.println("DUPLICATE EMAIL");
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String query = "INSERT INTO dbo.[User] (UserName, Password, Email, IsAdmin) VALUES ('" + user.getEmail() + "', '" + user.getPassword() + "', '" + user.getEmail() + "', " + "0" + ");";
		db.queryServerMulti(query);
		System.out.println("REG SUCCESS");
		return true;
	}

	@Override
	public User getUser(String email, String password) {
		for (User user: userList) {
			if (user.getEmail().equals(email)) {
				return user;
			}
		}
		return new User();
	}
}
