package result;

/**
 * This class is used to create a response for the register service.
 */
public class RegisterResponse {
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
     * This is the constructor for the RegisterResponse class.
     * @param authtoken
     * @param username
     * @param personID
     * @param success
     */
    public RegisterResponse(String authtoken, String username, String personID, Boolean success) {
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
        this.success = success;
    }

    public RegisterResponse(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }


    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
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
