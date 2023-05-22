package result;

/**
 * This class is used to create a response for the register service.
 */
public class ResisterResponseError {
    /**
     * This is the error message.
     */
    String message;
    /**
     * This is a boolean that is false.
     */
    Boolean success;

    /**
     * This is the constructor for the ResisterResponseError class.
     * @param message
     * @param success
     */
    public ResisterResponseError(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }
}

