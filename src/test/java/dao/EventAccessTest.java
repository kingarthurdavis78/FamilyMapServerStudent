package dao;

import model.Event;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class EventAccessTest {

    private EventAccess eDao;
    private Event bestEvent;

    @BeforeEach
    public void setUp() throws DataAccessException {
        eDao = new EventAccess();
        eDao.openConnection();

        bestEvent = new Event("Biking_123A", "Gale", "Gale123A",
                35.9f, 140.1f, "Japan", "Ushiku",
                "Biking_Around", 2016);

        eDao.clearEventTable();
    }

    @AfterEach
    public void tearDown() {
        eDao.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        eDao.addEvent(bestEvent);
        Event compareTest = eDao.getEvent(bestEvent.getEventID());
        assertNotNull(compareTest);
        assertEquals(bestEvent, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        eDao.addEvent(bestEvent);

        assertThrows(DataAccessException.class, () -> eDao.addEvent(bestEvent));
    }

}
