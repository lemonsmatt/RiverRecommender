package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {

    private StringProperty email = new SimpleStringProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private BooleanProperty admin = new SimpleBooleanProperty();
    private BooleanProperty ban = new SimpleBooleanProperty();
    private StringProperty banningAdmin = new SimpleStringProperty();

    public String getBanningAdmin() {
        return banningAdmin.get();
    }

    public StringProperty banningAdminProperty() {
        return banningAdmin;
    }

    public void setBanningAdmin(String banningAdmin) {
        this.banningAdmin.set(banningAdmin);
    }


    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public boolean isAdmin() {
        return admin.get();
    }

    public BooleanProperty adminProperty() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin.set(admin);
    }

    public boolean getBan() {
        return ban.get();
    }

    public BooleanProperty banProperty() {
        return ban;
    }

    public void setBan(boolean ban) {
        this.ban.set(ban);
    }

    public User(String email, String name, String password, boolean admin, boolean ban, String banningAdmin) {
        this.email.set(email);
        this.name.set(name);
        this.password.set(password);
        this.admin.set(admin);
        this.ban.set(ban);
        this.banningAdmin.set(banningAdmin);
    }

    public User() {
        this("New User", "", "", false, true, "");
    }
}
