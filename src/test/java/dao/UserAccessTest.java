package dao;

import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserAccessTest {
    private UserAccess uDao;
    private User bestUser;

    @BeforeEach
    public void setUp() throws DataAccessException {
        uDao = new UserAccess();
        uDao.openConnection();

        bestUser = new User("my fake username", "my fake password", "my fake email62", "John", "Doe", "m", "12345");

        uDao.clearUserTable();
    }

    @AfterEach
    public void tearDown() {
        uDao.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        uDao.addUser(bestUser);
        User compareTest = uDao.getUser(bestUser.getUsername());
        assertNotNull(compareTest);
        assertEquals(bestUser, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        uDao.addUser(bestUser);
        assertThrows(DataAccessException.class, () -> uDao.addUser(bestUser));
    }

    @Test
    public void getUserPass() throws DataAccessException {
        uDao.addUser(bestUser);
        User compareTest = uDao.getUser(bestUser.getUsername());
        assertNotNull(compareTest);
        assertEquals(bestUser, compareTest);
    }

    @Test
    public void getUserFail() throws DataAccessException {
        uDao.addUser(bestUser);
        User compareTest = uDao.getUser("fake username");
        assertNull(compareTest);
    }

    @Test
    public void clearUserTable() throws DataAccessException {
        uDao.addUser(bestUser);
        uDao.clearUserTable();
        User compareTest = uDao.getUser(bestUser.getUsername());
        assertNull(compareTest);
    }
}
