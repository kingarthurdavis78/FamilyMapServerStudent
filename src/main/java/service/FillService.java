package service;

import result.FillResponse;
import dao.UserAccess;
import model.User;

/**
 * FillService Object
 */
public class FillService {
    private int peopleAdded;
    private int eventsAdded;

    private void populate(User user, int numGeneration) {
        // TODO: Populate the database with generated data for the specified user name.
    }

    /**
     * The fill method
     */
    public FillResponse fill(String username, int numGenerations) {
        try {
            UserAccess userAccess = new UserAccess();
            User user = userAccess.getUser(username);
            if (user == null) {
                return new FillResponse("Error: Invalid username", false);
            }

            userAccess.closeConnection(true);
            return new FillResponse("Successfully added " + peopleAdded + " persons and " + eventsAdded + " events to the database.", true);
        } catch (Exception ex) {
            return new FillResponse("Error: " + ex.getMessage(), false);
        }
    }

    /**
     * The fill method
     */
    public FillResponse fill(String username) {
        int numGenerations = 4;
        try {
            UserAccess userAccess = new UserAccess();
            User user = userAccess.getUser(username);
            if (user == null) {
                return new FillResponse("Error: Invalid username", false);
            }
            userAccess.closeConnection(true);
            return new FillResponse("Successfully added " + peopleAdded + " persons and " + eventsAdded + " events to the database.", true);
        } catch (Exception ex) {
            return new FillResponse("Error: " + ex.getMessage(), false);
        }
    }
}
