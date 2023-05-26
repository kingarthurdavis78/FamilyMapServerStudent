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
    private float latitude;

    /**
     * The Event's Longitude
     */
    private float longitude;

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
    private int year;

    /**
     * The Event constructor
     */
    public Event(String eventID, String associatedUsername, String personID, float latitude, float longitude, String country, String city, String eventType, int year) {
        setEventID(eventID);
        setAssociatedUsername(associatedUsername);
        setPersonID(personID);
        setLatitude(latitude);
        setLongitude(longitude);
        setCountry(country);
        setCity(city);
        setEventType(eventType);
        setYear(year);
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

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {return false;}

        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Event)){
            return false;
        }
        Event event = (Event) obj;
        return getEventID().equals(event.getEventID()) &&
                getAssociatedUsername().equals(event.getAssociatedUsername()) &&
                getPersonID().equals(event.getPersonID()) &&
                getLatitude() == event.getLatitude() &&
                getLongitude() == event.getLongitude() &&
                getCountry().equals(event.getCountry()) &&
                getCity().equals(event.getCity()) &&
                getEventType().equals(event.getEventType()) &&
                getYear() == event.getYear();
    }
}
