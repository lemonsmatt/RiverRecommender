package model;
import application.Main;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLunittester {

    Main main = new Main();
    String email;
    String password;

    public String query(String input, String email, String password) {
        return "SELECT " + input + " FROM dbo.[User] WHERE Email = '" + email + "' and Password = '" + password + "';";
    }

    public String query(String adminUserName, String email) {
        return "UPDATE dbo.[User] SET BannedBy = '" + adminUserName + "' WHERE Email = '" + email + "';";

    }

    /*
    @Test
    public void testSomething() {
        email = "Water";
        password = "Water";
        Database db = new Database();
        Assert.assertNull(db.queryServer(query(email, password)));
    }
    */


    @Test
    public void testSomethingr1() {
        email = "Eric@matt";
        password = "Matt";
        Database db = new Database();
        Assert.assertNotNull(db.queryServer(query("UserName", email, password)));
        ResultSet test1 = db.queryServer(query("UserName", email, password));

        String test = null;
        try {
            test = test1.getString("UserName");
            System.out.println(test);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(test);
    }

    @Test
    public void testSomethingr() {
        email = "Eric@matt";
        password = "Matt";
        Database db = new Database();
        ResultSet test1 = db.queryServer(query("Eric", "Chase"));

        String test = null;
        try {
            test = test1.getString("UserName");
            System.out.println(test);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(test);
    }
}

