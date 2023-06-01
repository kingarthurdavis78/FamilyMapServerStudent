package result;

/**
 * This class is used to create a response for the fill service.
 */
public class FillResponse {
    /**
     * This is the message.
     */
    String message;
    /**
     * This is a boolean that shows if the fill was successful.
     */
    Boolean success;

    /**
     * This is the constructor for the FillResponse class.
     * @param message
     * @param success
     */
    public FillResponse(String message, Boolean success) {
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
