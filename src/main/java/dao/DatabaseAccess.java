package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is used to access the database.
 */
public class DatabaseAccess {
    /**
     * Connection to the database.
     */
    private Connection conn;


    /**
     * This method is used to open a connection to the database.
     */
    public Connection openConnection() throws DataAccessException {
        try {
            // The Structure for this Connection is driver:language:path
            // The path assumes you start in the root of your project unless given a full file path
            final String CONNECTION_URL = "jdbc:sqlite:familymap.db";

            // Open a database connection to the file given in the path
            conn = DriverManager.getConnection(CONNECTION_URL);

            // Start a transaction
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to open connection to database");
        }

        return conn;
    }

    /**
     * This method is used to close a connection to the database.
     */
    public void closeConnection(boolean commit) {
        try {
            if (commit) {
                conn.commit();
            } else {
                conn.rollback();
            }
            conn.close();
            conn = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to clear the database.
     */
    public void clear() {}

    /**
     * DatabaseAccess constructor.
     */
    public DatabaseAccess() {
    }

    /**
     * This method is used to get the connection to the database.
     */
    public Connection getConn() {
        return conn;
    }


}
