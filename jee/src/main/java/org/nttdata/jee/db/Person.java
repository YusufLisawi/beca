package org.nttdata.jee.db;

import java.sql.SQLException;

public class Person extends DatabaseManager {
    private String firstName;
    private String lastName;
    private String password;

    public Person() {}

    public Person(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.toUpperCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean save() {
        if (exists()) {
            return false;
        } else {
            runExecuteUpdate("INSERT INTO person (firstname, lastname, pws) VALUES ('" + firstName + "', '" + lastName + "', '" + password + "')");
            return true;
        }
    }

    public void delete() {
        runExecuteUpdate("DELETE FROM person WHERE firstname = '" + firstName + "' AND lastname = '" + lastName + "'");
    }

    private boolean exists() {
        try {
            return runExecuteQuery("SELECT * FROM person WHERE firstname = '" + firstName + "' AND lastname = '" + lastName + "'").next() ? true : false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
