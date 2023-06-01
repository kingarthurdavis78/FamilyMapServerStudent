package dao;

import model.Authtoken;
import dao.AuthTokenAccess;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AuthTokenAccessTest {
    private AuthTokenAccess aDao;
    private Authtoken bestauthtoken;

    @BeforeEach
    public void setUp() throws DataAccessException {
        aDao = new AuthTokenAccess();
        aDao.openConnection();

        bestauthtoken = new Authtoken( "my fake authtoken", "my fake username");

        aDao.clearAuthTokenTable();
    }

    @AfterEach
    public void tearDown() {
        aDao.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        aDao.addAuthToken(bestauthtoken);
        Authtoken compareTest = aDao.getAuthToken(bestauthtoken.getAuthtoken());
        assertNotNull(compareTest);
        assertEquals(bestauthtoken, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        aDao.addAuthToken(bestauthtoken);
        assertThrows(DataAccessException.class, () -> aDao.addAuthToken(bestauthtoken));
    }

    @Test
    public void getauthtokenPass() throws DataAccessException {
        aDao.addAuthToken(bestauthtoken);
        Authtoken compareTest = aDao.getAuthToken(bestauthtoken.getAuthtoken());
        assertNotNull(compareTest);
        assertEquals(bestauthtoken, compareTest);
    }

    @Test
    public void getauthtokenFail() throws DataAccessException {
        aDao.addAuthToken(bestauthtoken);
        Authtoken compareTest = aDao.getAuthToken("fake authtoken name");
        assertNull(compareTest);
    }

    @Test
    public void clearauthtokenTable() throws DataAccessException {
        aDao.addAuthToken(bestauthtoken);
        aDao.clearAuthTokenTable();
        Authtoken compareTest = aDao.getAuthToken(bestauthtoken.getAuthtoken());
        assertNull(compareTest);
    }
}
