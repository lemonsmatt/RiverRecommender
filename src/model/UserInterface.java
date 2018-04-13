package model;

public interface UserInterface {

    public boolean isValidUser(String email, String password);

    public boolean isAdmin(String email, String password);

    public boolean banUser(String email);
}
