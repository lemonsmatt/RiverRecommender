package model;

public class UserTester implements UserInterface {

    @Override
    public boolean isAdmin(String email, String password) {
        return false;
    }

    @Override
    public boolean isValidUser(String email, String password) {
        return true;
    }
}