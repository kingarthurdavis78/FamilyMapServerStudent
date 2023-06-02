package request;

import model.User;
import model.Person;
import model.Event;

/**
 * LoadRequest
 */
public class LoadRequest {
    /**
    * Users
    */
    private User[] users;

    /**
     * Persons
     */
    private Person[] persons;

    /**
     * Events
     */
    private Event[] events;

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public Person[] getPersons() {
        return persons;
    }

    public void setPersons(Person[] persons) {
        this.persons = persons;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }
}
