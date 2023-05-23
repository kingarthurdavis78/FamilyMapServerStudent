package result;

import model.Event;

/**
 * This class is used to create a response for the event service.
 */
public class EventResponse {
    /**
     * Array of Event objects
     */
    Event[] data;

    /**
     * Error message string
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
    public EventResponse(Event[] data) {
        this.data = data;
        this.success = true;
    }

    /**
     * This is the constructor for an unsuccessful EventResponse class.
     * @param message
     */
    public EventResponse(String message) {
        this.message = message;
        this.success = false;
    }


}
