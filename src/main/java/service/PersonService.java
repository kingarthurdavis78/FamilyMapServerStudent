package service;


import dao.DataAccessException;
import dao.PersonAccess;
import model.Person;
import result.GetPersonResponse;
import result.GetFamilyResponse;

/**
 * PersonService Object
 */
public class PersonService {

    /**
     * Gets a person from the database with the given personID
     */
    public GetPersonResponse getPerson(String personID) {
        PersonAccess personAccess = new PersonAccess();
        try {
        personAccess.openConnection();
        Person person = personAccess.getPerson(personID);
        personAccess.closeConnection(true);
        if (person == null) {
            return new GetPersonResponse("Error: Person not found", false);
        }
        return new GetPersonResponse(person.getAssociatedUsername(), person.getPersonID(), person.getFirstName(), person.getLastName(), person.getGender(), person.getFatherID(), person.getMotherID(), person.getSpouseID(), true);
        } catch (DataAccessException ex) {
            personAccess.closeConnection(false);
            return new GetPersonResponse("Error: " + ex.getMessage(), false);
        }
    }

    /**
     * Returns all family members of the current user
     */
    public GetFamilyResponse getFamily(String username) {
        return null;
    }
}
