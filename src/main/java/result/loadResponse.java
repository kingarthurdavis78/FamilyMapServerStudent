package result;

/**
 * This class is used to create a response for the load service.
 */
public class loadResponse {
    /**
     * This is the message.
     */
    String message;

    /**
     * This is a boolean that shows if the load was successful.
     */
    Boolean success;

    /**
     * This is the constructor for the loadResponse class.
     * @param message
     * @param success
     */
    public loadResponse(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }
}
