package model;

public interface LoginInterface {

    public boolean isValidUser(String email, String password);

    public boolean isAdmin(String email, String password);
}
