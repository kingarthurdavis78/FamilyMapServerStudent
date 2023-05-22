package result;

/**
 * This class is used to create a response for the fill service.
 */
public class fillResponse {
    /**
     * This is the message.
     */
    String message;
    /**
     * This is a boolean that shows if the fill was successful.
     */
    Boolean success;

    /**
     * This is the constructor for the fillResponse class.
     * @param message
     * @param success
     */
    public fillResponse(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }
}
