package model;

/**
 * An Event
 */
public class Event {
    /**
     * The Event's ID
     */
    private String eventID;

    /**
     * The Event's Username
     */
    private String associatedUsername;

    /**
     * The Event's Person's ID
     */
    private String personID;

    /**
     * The Event's Latitude
     */
    private String latitude;

    /**
     * The Event's Longitude
     */
    private String longitude;

    /**
     * The Event's Country
     */
    private String country;

    /**
     * The Event's City
     */
    private String city;

    /**
     * The Event's EventType
     */
    private String eventType;

    /**
     * The Event's Year
     */
    private String year;

    /**
     * The Event constructor
     */
    public Event(String eventID, String associatedUsername, String personID, String latitude, String longitude, String country, String city, String eventType, String year) {

    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
