package service;

import dao.DataAccessException;
import dao.EventAccess;
import model.Event;
import result.GetEventResponse;
import result.GetEventsResponse;

/**
 * EventService Object
 */
public class EventService {

    /**
     * Returns the single Event object with the specified ID.
     */
    public GetEventResponse getEvent(String username, String eventID) {
        EventAccess eDao = new EventAccess();
        try {
            eDao.openConnection();
            Event event = eDao.getEvent(eventID);
            eDao.closeConnection(true);

            if (event == null) {
                throw new DataAccessException("Error: event not found");
            }
            if (!event.getAssociatedUsername().equals(username)) {
                throw new DataAccessException("Error: event is not associated with this user");
            }

            return new GetEventResponse(event);

        } catch (DataAccessException ex) {
            return new GetEventResponse("Error: " + ex.getMessage());
        }
    }
    
    /**
     * Returns ALL events for ALL family members of the current user.
     */
    public GetEventsResponse getAllEvents(String username) {
        EventAccess eDao = new EventAccess();
        try {
            eDao.openConnection();
            Event[] events = eDao.getAllEvents(username);
            eDao.closeConnection(true);

            if (events == null) {
                throw new DataAccessException("Error: No family members found");
            }

            return new GetEventsResponse(events);
        } catch (DataAccessException ex) {
            return new GetEventsResponse("Error: " + ex.getMessage());
        }
    }

}
