package service;

import result.ClearResponse;
import dao.DatabaseAccess;
import dao.DataAccessException;

/**
 * ClearService Object
 */
public class ClearService {

    /**
     * The clear method
     */
    public ClearResponse clear() {
        DatabaseAccess db = new DatabaseAccess();
        try {
            db.openConnection();
            db.clear();
            db.closeConnection(true);
        } catch (DataAccessException ex) {
            return new ClearResponse("Error: " + ex.getMessage(), false);
        }
        return new ClearResponse("Clear succeeded.", true);
    }
}
