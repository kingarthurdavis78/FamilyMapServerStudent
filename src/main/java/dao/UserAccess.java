package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is used to access the User table in the database.
 */
public class UserAccess extends DatabaseAccess{

    /**
     * This method is used to clear the User table in the database.
     */
    public void clearUserTable() throws DataAccessException {
        String sql = "DELETE FROM Users";
        try (PreparedStatement stmt = super.getConn().prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the user table");
        }
    }

    /**
     * This method is used to add a User to the database.
     */
    public void addUser(User user) throws DataAccessException{
        String sql = "INSERT INTO Users (username, password, email, firstName, lastName, gender, personID) VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = super.getConn().prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            stmt.setString(6, user.getGender());
            stmt.setString(7, user.getPersonID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException(e.getMessage());
        }

    }

    /**
     * This method is used to get a User from the database.
     */
    public User getUser(String username) throws DataAccessException{
        User user;
        ResultSet rs;
        String sql = "SELECT * FROM Users WHERE username = ?;";
        try (PreparedStatement stmt = super.getConn().prepareStatement(sql)) {
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an User in the database");
        }
    }

    /**
     * This method is used to delete a User from the database.
     */
    public void deleteUser(String username) throws DataAccessException {
        String sql = "DELETE FROM Users WHERE username = ?";
        try (PreparedStatement stmt = super.getConn().prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while deleting an User in the database");
        }
    }

    /**
     * UserAccess constructor.
     */
    public UserAccess() {
    }

    public UserAccess(Connection conn) {
        super(conn);
    }
}
