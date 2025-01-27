package model;

import java.util.Objects;

/**
 * A User
 */
public class User {
    /**
     * The User's Username
     */
    private String username;
    /**
     * The User's Password
     */
    private String password;
    /**
     * The User's Email
     */
    private String email;
    /**
     * The User's First Name
     */
    private String firstName;
    /**
     * The User's Last Name
     */
    private String lastName;
    /**
     * The User's Gender
     */
    private String gender;
    /**
     * The User's ID
     */
    private String personID;

    /**
     * The User constructor
     */
    public User(String username, String password, String email, String firstName, String lastName, String gender, String personID) {
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
        setGender(gender);
        setPersonID(personID);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {return false;}

        if (this == obj) {
            return true;
        }
        if (!(obj instanceof User)){
            return false;
        }
        User user = (User)obj;
        return getUsername().equals(user.getUsername()) && getPassword().equals(user.getPassword()) && getEmail().equals(user.getEmail()) && getFirstName().equals(user.getFirstName()) && getLastName().equals(user.getLastName()) && getGender().equals(user.getGender()) && getPersonID().equals(user.getPersonID());
    }
}
