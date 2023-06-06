package service;

import dao.DataAccessException;
import dao.UserAccess;
import model.Person;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.RegisterRequest;
import result.GetFamilyResponse;
import result.RegisterResponse;

import static org.junit.jupiter.api.Assertions.*;


public class RegisterServiceTest {
    @BeforeEach
    public void setUp() {
        ClearService clearService = new ClearService();
        clearService.clear();
    }

    @AfterEach
    public void tearDown() {
        ClearService clearService = new ClearService();
        clearService.clear();
    }

    @Test
    public void RegisterTest() {
        User user = new User("kingarthur", "camelot", "king@arthur.click", "Arthur", "King", "m", "King_Arthur");
        RegisterRequest registerRequest = new RegisterRequest(user);
        RegisterService registerService = new RegisterService();
        RegisterResponse response = registerService.register(registerRequest);

        assertTrue(response.getSuccess());
        assertEquals("kingarthur", response.getUsername());
        assertNotNull(response.getAuthtoken());
        assertNotNull(response.getPersonID());

        PersonService personService = new PersonService();
        assertEquals("Arthur", personService.getPerson(user, response.getPersonID()).toPerson().getFirstName());
        GetFamilyResponse family = personService.getFamily(user.getUsername());
        assertEquals(31, family.getData().length);
    }

    @Test
    public void RegisterTestFail() throws DataAccessException {
        User user = new User("kingarthur", "camelot", "king@arthur.click", "Arthur", "King", "m", "King_Arthur");
        UserAccess userAccess = new UserAccess();
        userAccess.openConnection();
        userAccess.addUser(user);
        userAccess.closeConnection(true);

        RegisterRequest registerRequest = new RegisterRequest(user);
        RegisterService registerService = new RegisterService();
        RegisterResponse response = registerService.register(registerRequest);

        assertFalse(response.getSuccess());
        assertEquals("Error: Failed to add user into the database", response.getMessage());
    }



}
