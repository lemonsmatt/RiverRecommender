package model;

import java.util.List;

public interface UserInterface {

    public boolean isValidUser(String email, String password);

    public boolean isAdmin(String email, String password);

    public boolean banUser(User user, String adminUserName);

    public List<User> getValidUsers();

    public  List<User> getBannableUsers();

    public boolean addUser(User user);

    public User getUser(String email, String password);
}
