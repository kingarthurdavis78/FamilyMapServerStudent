package result;

/**
 * This class is used to create a response for the clear service.
 */
public class ClearResponse {
    /**
     * This is the message.
     */
    String message;
    /**
     * This is a boolean that shows if the clear was successful.
     */
    Boolean success;

    /**
     * This is the constructor for the ClearResponse class.
     * @param message this is the message
     * @param success this is a boolean that shows if the clear was successful
     */
    public ClearResponse(String message, Boolean success) {
        this.message = message;
        this.success = success;
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
