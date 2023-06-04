package dao;

import model.Event;
import java.sql.*;


/**
 * This class is used to access the Event table in the database.
 */
public class EventAccess extends DatabaseAccess {

    /**
     * This method is used to clear the Event table in the database.
     */
    public void clearEventTable() throws DataAccessException {
        String sql = "DELETE FROM Events";
        try (PreparedStatement stmt = super.getConn().prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException(e.getMessage());
        }
    }

    /**
     * This method is used to add an Event to the database.
     */
    public void addEvent(Event event)  throws DataAccessException{
        String sql = "INSERT INTO Events (EventID, AssociatedUsername, PersonID, Latitude, Longitude, " +
                "Country, City, EventType, Year) VALUES(?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = super.getConn().prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, event.getEventID());
            stmt.setString(2, event.getAssociatedUsername());
            stmt.setString(3, event.getPersonID());
            stmt.setFloat(4, event.getLatitude());
            stmt.setFloat(5, event.getLongitude());
            stmt.setString(6, event.getCountry());
            stmt.setString(7, event.getCity());
            stmt.setString(8, event.getEventType());
            stmt.setInt(9, event.getYear());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting an event into the database");
        }
    }

    /**
     * This method is used to get an Event from the database.
     */
    public Event getEvent(String eventID) throws DataAccessException{
        Event event;
        ResultSet rs;
        String sql = "SELECT * FROM Events WHERE EventID = ?;";
        try (PreparedStatement stmt = super.getConn().prepareStatement(sql)) {
            stmt.setString(1, eventID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                event = new Event(rs.getString("EventID"), rs.getString("AssociatedUsername"),
                        rs.getString("PersonID"), rs.getFloat("Latitude"), rs.getFloat("Longitude"),
                        rs.getString("Country"), rs.getString("City"), rs.getString("EventType"),
                        rs.getInt("Year"));
                return event;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an event in the database");
        }
    }

    /**
     * This method is used to get all Events associated with the given user from the database.
     */
    public Event[] getAllEvents(String username) throws DataAccessException {
        Event[] events;
        ResultSet rs;
        String sql = "SELECT * FROM Events WHERE AssociatedUsername = ?;";
        try (PreparedStatement stmt = super.getConn().prepareStatement(sql)) {
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            events = new Event[2];
            int i = 0;
            while (rs.next()) {
                events[i] = new Event(rs.getString("EventID"), rs.getString("AssociatedUsername"),
                        rs.getString("PersonID"), rs.getFloat("Latitude"), rs.getFloat("Longitude"),
                        rs.getString("Country"), rs.getString("City"), rs.getString("EventType"),
                        rs.getInt("Year"));
                i++;
            }
            return events;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding events in the database");
        }
    }

    /**
     * This method is used to delete an Event from the database.
     */
    public void deleteEventAccess(Event event) throws DataAccessException{
        String sql = "DELETE * FROM Events WHERE EventID = ?;";
        try (PreparedStatement stmt = super.getConn().prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException(e.getMessage());
        }
    }


    /**
     * EventAccess constructor.
     */
    public EventAccess() {
    }

    /**
     * EventAccess constructor.
     */
    public EventAccess(Connection conn) {
        super(conn);
    }


}