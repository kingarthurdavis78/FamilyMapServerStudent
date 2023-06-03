package result;

/**
 * GetPersonResponse Object
 */
public class GetPersonResponse {
    /**
     * The person's username
     */
    String associatedUsername;

    /**
     * The person's ID
     */
    String personID;

    /**
     * The person's first name
     */
    String firstName;

    /**
     * The person's last name
     */
    String lastName;

    /**
     * The person's gender
     */
    String gender;

    /**
     * The person's father's ID
     */
    String fatherID;

    /**
     * The person's mother's ID
     */
    String motherID;

    /**
     * The person's spouse's ID
     */
    String spouseID;

    /**
     * The error message
     */
    String message;

    /**
     * The success boolean
     */
    Boolean success;

    /**
     * The GetPersonResponse constructor
     */
    public GetPersonResponse(String username, String personID, String firstName, String lastName, String gender, boolean success) {
        this.associatedUsername = username;
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.success = success;
    }

    public GetPersonResponse(String username, String personID, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID, boolean success) {
        this.associatedUsername = username;
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
        this.success = success;
    }

    public GetPersonResponse(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
