package dao;

import model.Person;
import model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonAccessTest {
    private PersonAccess pDao;
    private Person bestEvent;

    @BeforeEach
    public void setUp() throws DataAccessException {
        pDao = new PersonAccess();
        pDao.openConnection();

        bestEvent = new Person("123456", "my fake Person", "name", "nameane,e", "f", "543", "456", "null");

        pDao.clear();
    }

    @AfterEach
    public void tearDown() {
        pDao.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        pDao.addPerson(bestEvent);
        Person compareTest = pDao.getPerson(bestEvent.getPersonID());
        assertNotNull(compareTest);
        assertEquals(bestEvent, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        pDao.addPerson(bestEvent);
        assertThrows(DataAccessException.class, () -> pDao.addPerson(bestEvent));
    }

    @Test
    public void getPersonPass() throws DataAccessException {
        pDao.addPerson(bestEvent);
        Person compareTest = pDao.getPerson(bestEvent.getPersonID());
        assertNotNull(compareTest);
        assertEquals(bestEvent, compareTest);
    }

    @Test
    public void getPersonFail() throws DataAccessException {
        pDao.addPerson(bestEvent);
        Person compareTest = pDao.getPerson("fake Personname");
        assertNull(compareTest);
    }

    @Test
    public void clearPersonTable() throws DataAccessException {
        pDao.addPerson(bestEvent);
        pDao.clearPersonTable();
        Person compareTest = pDao.getPerson(bestEvent.getPersonID());
        assertNull(compareTest);
    }
}
