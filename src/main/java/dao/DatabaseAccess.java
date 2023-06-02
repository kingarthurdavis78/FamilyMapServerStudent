package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

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

    private String opentext(String textFile) {
        try {
            StringBuilder sql = new StringBuilder();
            File file = new File(textFile);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                sql.append(line);
            }
            scanner.close();
            return sql.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method is used to clear the database.
     */
    public void clear() throws DataAccessException {
        try (Statement smt = getConn().createStatement()) {
            smt.addBatch("DELETE FROM AuthTokens");
            smt.addBatch("DELETE FROM Users");
            smt.addBatch("DELETE FROM Persons");
            smt.addBatch("DELETE FROM Events");
            smt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException(e.getMessage());
        }
    }

    /**
     * DatabaseAccess constructor.
     */
    public DatabaseAccess() {
    }

    public DatabaseAccess(Connection conn) {
        this.conn = conn;
    }

    /**
     * This method is used to get the connection to the database.
     */
    public Connection getConn() {
        return conn;
    }


}
