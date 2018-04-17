package model;

import java.util.ArrayList;
import java.util.List;

public class UserTester implements UserInterface {

    public static List<User> userList = new ArrayList<User>();

    public UserTester() {
        if (userList.isEmpty())
        {
            userList.add(new User("mlemons@gatech.edu", "matt","123", true, false, ""));
            userList.add(new User("qwe@gatech.edu", "chase","123", false, true, "Matt"));
            userList.add(new User("asdf@gatech.edu", "ryan","123", false, false, ""));
            userList.add(new User("zxvxcv@gatech.edu", "eric","123", false, false, ""));
        }
    }


    @Override
    public boolean isAdmin(String email, String password) {
        for (User user: userList) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user.isAdmin();
            }
        }
        return false;
    }

    @Override
    public boolean isValidUser(String email, String password) {
        for (User user: userList) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return !user.getBan();
            }
        }
        return false;
    }

    @Override
    public boolean banUser(User user, String adminUserName) {
        if (userList.contains(user))
        {
            int index = userList.indexOf(user);
            userList.get(index).setBan(true);
            userList.get(index).setBanningAdmin(adminUserName);
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
        return userList.add(user);

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
