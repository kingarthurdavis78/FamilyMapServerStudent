package result;

/**
 * This class is used to create a response for the register service.
 */
public class ResisterResponse {
    /**
     * This is the auth token.
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
     * This is the error message.
     */
    String message;

    /**
     * This is a boolean that shows if the register was successful.
     */
    Boolean success;

    /**
     * This is the constructor for the ResisterResponse class.
     * @param authtoken
     * @param username
     * @param personID
     * @param success
     */
    public ResisterResponse(String authtoken, String username, String personID, Boolean success) {
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
        this.success = success;
    }

    public ResisterResponse(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }

}
