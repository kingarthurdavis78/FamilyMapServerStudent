package dao;


import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class DatabaseAccessTest {
    private DatabaseAccess Dao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        Dao = new DatabaseAccess();
        Dao.openConnection();
    }

    @AfterEach
    public void tearDown() {
        Dao.closeConnection(false);
    }

    @Test
    public void clearTables() throws DataAccessException {
        UserAccess userAccess = new UserAccess(Dao.getConn());
//        userAccess.addUser(new User("username", "password", "email", "firstName", "lastName", "m", "personID"));
        Dao.clear();

        assertNull(userAccess.getUser("username"));
    }
}
