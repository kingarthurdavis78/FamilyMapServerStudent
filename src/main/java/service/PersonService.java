package service;


import dao.DataAccessException;
import dao.PersonAccess;
import model.Person;
import model.User;
import result.GetPersonResponse;
import result.GetFamilyResponse;

/**
 * PersonService Object
 */
public class PersonService {

    /**
     * Gets a person from the database with the given personID
     */
    public GetPersonResponse getPerson(User user, String personID) {
        PersonAccess personAccess = new PersonAccess();
        try {
        personAccess.openConnection();
        Person person = personAccess.getPerson(personID);
        personAccess.closeConnection(true);

        if (person == null) {
            throw new DataAccessException("Error: Person not found");
        }
        if (!person.getAssociatedUsername().equals(user.getUsername())) {
            throw new DataAccessException("Error: Person is not associated with this user");
        }

        return new GetPersonResponse(person.getAssociatedUsername(), person.getPersonID(), person.getFirstName(), person.getLastName(), person.getGender(), person.getFatherID(), person.getMotherID(), person.getSpouseID(), true);

        } catch (DataAccessException ex) {
            return new GetPersonResponse("Error: " + ex.getMessage(), false);
        }
    }

    /**
     * Returns all family members of the current user
     */
    public GetFamilyResponse getFamily(String username) {
        PersonAccess personAccess = new PersonAccess();
        try {
            personAccess.openConnection();
            Person[] family = personAccess.getFamily(username);
            personAccess.closeConnection(true);

            if (family == null) {
                throw new DataAccessException("Error: No family members found");
            }

            return new GetFamilyResponse(family);
        } catch (DataAccessException ex) {
            return new GetFamilyResponse("Error: " + ex.getMessage());
        }
    }
}
