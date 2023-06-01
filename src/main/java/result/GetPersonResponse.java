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

}
