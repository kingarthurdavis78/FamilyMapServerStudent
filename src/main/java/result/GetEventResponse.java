package result;

import model.Event;

public class GetEventResponse {

    /**
     * associatedUsername string
     */
    String associatedUsername;

    /**
     * EventID string
     */
    String eventID;

    /**
     * PersonID string
     */
    String personID;

    /**
     * Latitude float
     */
    float latitude;

    /**
     * Longitude float
     */
    float longitude;

    /**
     * Country string
     */
    String country;

    /**
     * City string
     */
    String city;

    /**
     * EventType string
     */
    String eventType;

    /**
     * Year int
     */
    int year;

    /**
     * Error message
     */
    String message;

    /**
     * Success boolean
     */
    boolean success;

    /**
     * This is the constructor for a successful EventResponse class.
     * @param event
     */
    public GetEventResponse(Event event) {
        this.associatedUsername = event.getAssociatedUsername();
        this.eventID = event.getEventID();
        this.personID = event.getPersonID();
        this.latitude = event.getLatitude();
        this.longitude = event.getLongitude();
        this.country = event.getCountry();
        this.city = event.getCity();
        this.eventType = event.getEventType();
        this.year = event.getYear();
        this.success = true;
    }

    /**
     * This is the constructor for an unsuccessful EventResponse class.
     * @param message
     */
    public GetEventResponse(String message) {
        this.message = message;
        this.success = false;
    }

    public Event toEvent() {
        return new Event(eventID, associatedUsername, personID, latitude, longitude, country, city, eventType, year);
    }

    public String getassociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
