package result;

import model.Event;

public class GetEventsResponse {
    /**
     * Array of Event objects
     */
    Event[] data;

    /**
     * Error message
     */
    String message;

    /**
     * Boolean identifier
     */
    Boolean success;

    /**
     * This is the constructor for a successful EventResponse class.
     * @param data
     */
    public GetEventsResponse(Event[] data) {
        this.data = data;
        this.success = true;
    }

    /**
     * This is the constructor for an unsuccessful EventResponse class.
     * @param message
     */
    public GetEventsResponse(String message) {
        this.message = message;
        this.success = false;
    }

    public Event[] getData() {
        return data;
    }

    public void setData(Event[] data) {
        this.data = data;
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
