package result;

/**
 * This class is used to create a response for the login service.
 */
public class LoginResponse {
    /**
     * This is the authtoken.
     */
    String authtoken;

    /**
     * This is the username.
     */
    String username;

    /**
     * This is the personID.
     */
    String personID;

    /**
     * This is a boolean that is true.
     */
    Boolean success;

    /**
     * This is the constructor for the LoginResponse class.
     * @param authtoken
     * @param username
     * @param personID
     * @param success
     */
    public LoginResponse(String authtoken, String username, String personID, Boolean success) {
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
        this.success = success;
    }

}
