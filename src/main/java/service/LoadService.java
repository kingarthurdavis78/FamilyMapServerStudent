package service;

import dao.*;
import request.LoadRequest;
import result.ClearResponse;
import result.LoadResponse;

import java.sql.Connection;

/**
 * LoadService Object
 */
public class LoadService {


    /**
     * The load method
     */
    public LoadResponse load(LoadRequest request) {

        // Clear Database
        ClearService clearService = new ClearService();
        ClearResponse clearResponse = clearService.clear();
        if (!clearResponse.getSuccess()) {
            return new LoadResponse(clearResponse.getMessage(), false);
        }

        try {
            // Open Connection
            DatabaseAccess db = new DatabaseAccess();
            db.openConnection();
            Connection conn = db.getConn();

            // Add Users
            UserAccess userAccess = new UserAccess(conn);
            for (int i = 0; i < request.getUsers().length; i++) {
                userAccess.addUser(request.getUsers()[i]);
            }

            // Add Persons
            PersonAccess personAccess = new PersonAccess(conn);
            for (int i = 0; i < request.getPersons().length; i++) {
                personAccess.addPerson(request.getPersons()[i]);
            }

            // Add Events
            EventAccess eventAccess = new EventAccess(conn);
            for (int i = 0; i < request.getEvents().length; i++) {
                eventAccess.addEvent(request.getEvents()[i]);
            }

            // Close Connection
            db.closeConnection(true);

            return new LoadResponse("Successfully added " + request.getUsers().length + " users, " + request.getPersons().length + " persons, and " + request.getEvents().length + " events to the database.", true);
        } catch (DataAccessException ex) {
            return new LoadResponse(ex.getMessage(), false);
        }

    }
}
