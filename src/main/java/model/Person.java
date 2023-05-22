package model;

/**
 * A Person
 */
public class Person {
    /**
     * The Person's ID
     */
    private String personID;

    /**
     * The Person's Username
     */
    private String associatedUsername;

    /**
     * The Person's First Name
     */
    private String firstName;

    /**
     * The Person's Last Name
     */

    private String lastName;

    /**
     * The Person's Gender
     */
    private String gender;

    /**
     * The Person's Father's ID
     */
    private String fatherID;

    /**
     * The Person's Mother's ID
     */
    private String motherID;

    /**
     * The Person's Spouse's ID
     */
    private String spouseID;

    /**
     * The Person constructor
     */
    public Person() {}

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
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

    public String getFatherID() {
        return fatherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public String getMotherID() {
        return motherID;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    public String getSpouseID() {
        return spouseID;
    }

    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }
}
