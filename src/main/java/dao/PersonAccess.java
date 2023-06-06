package dao;

import model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is used to access the Person table in the database.
 */
public class PersonAccess extends DatabaseAccess {

    /**
     * This method is used to clear the Person table in the database.
     */
    public void clearPersonTable() throws DataAccessException{
        String sql = "DELETE FROM Persons";
        try (PreparedStatement stmt = super.getConn().prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException(e.getMessage());
        }

    }

    /**
     * This method is used to add a Person to the database.
     */
    public void addPerson(Person person) throws DataAccessException{
        String sql = "INSERT INTO Persons (personID, associatedUsername, firstName, lastName, gender, fatherID, motherID, spouseID) VALUES(?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = super.getConn().prepareStatement(sql)) {
            stmt.setString(1, person.getPersonID());
            stmt.setString(2, person.getAssociatedUsername());
            stmt.setString(3, person.getFirstName());
            stmt.setString(4, person.getLastName());
            stmt.setString(5, person.getGender());
            stmt.setString(6, person.getFatherID());
            stmt.setString(7, person.getMotherID());
            stmt.setString(8, person.getSpouseID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException(e.getMessage());
        }
    }

    /**
     * This method is used to get a Person from the database.
     */
    public Person getPerson(String personID) throws DataAccessException {
        Person person;
        ResultSet rs;
        String sql = "SELECT * FROM Persons WHERE personID = ?;";
        try (PreparedStatement stmt = super.getConn().prepareStatement(sql)) {
            stmt.setString(1, personID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                person = new Person(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                return person;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException(e.getMessage());
        }
    }

    /**
     * This method is used to get all people associated with a user from the database.
     */
    public Person[] getFamily(String username) throws DataAccessException{
        String sql = "SELECT * FROM Persons WHERE AssociatedUsername = ?;";
        try (PreparedStatement stmt = super.getConn().prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            List<Person> family = new LinkedList<>();
            while (rs.next()) {
                family.add(new Person(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
            return family.toArray(new Person[0]);
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public void deleteFamily(String username) throws DataAccessException{
        String sql = "DELETE FROM Persons WHERE AssociatedUsername = ?;";
        try (PreparedStatement stmt = super.getConn().prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }



    /**
     * PersonAccess constructor.
     */
    public PersonAccess() {

    }

    /**
     * PersonAccess constructor.
     */
    public PersonAccess(Connection conn) {
        super(conn);
    }

}
