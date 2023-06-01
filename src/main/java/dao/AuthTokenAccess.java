package dao;

import model.Authtoken;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is used to access the AuthToken table in the database.
 */
public class AuthTokenAccess  extends DatabaseAccess{

    /**
     * This method is used to clear the AuthToken table in the database.
     */
    public void clearAuthTokenTable() throws DataAccessException{
        String sql = "DELETE FROM AuthTokens";
        try (PreparedStatement stmt = super.getConn().prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException(e.getMessage());
        }
    }

    /**
     * This method is used to add an AuthToken to the database.
     */
    public void addAuthToken(Authtoken authtoken) throws DataAccessException{
        String sql = "INSERT INTO AuthTokens (authtoken, username) VALUES(?,?)";
        try (PreparedStatement stmt = super.getConn().prepareStatement(sql)) {
            stmt.setString(1, authtoken.getAuthtoken());
            stmt.setString(2, authtoken.getUsername());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException(e.getMessage());
        }
    }

    /**
     * This method is used to get an AuthToken from the database.
     */
    public Authtoken getAuthToken(String authtoken) throws DataAccessException{
        Authtoken auth;
        ResultSet rs;
        String sql = "SELECT * FROM AuthTokens WHERE authtoken = ?;";
        try (PreparedStatement stmt = super.getConn().prepareStatement(sql)) {
            stmt.setString(1, authtoken);
            rs = stmt.executeQuery();
            if (rs.next()) {
                auth= new Authtoken(rs.getString(1), rs.getString(2));
                return auth;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException(e.getMessage());
        }
    }

    /**
     * This method is used to delete an AuthToken from the database.
     */
    public void deleteAuthToken(Authtoken authtoken) {

    }

    /**
     * AuthTokenAccess constructor.
     */
    public AuthTokenAccess() {

    }
}
