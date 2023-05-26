package dao;

import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserAccessTest {
    //We will use this to test that our insert method is working and failing in the right ways
    private UserAccess uDao;
    private User bestEvent;

    @BeforeEach
    public void setUp() throws DataAccessException {
        // Here we can set up any classes or variables we will need for each test
        // lets create a new instance of the Database class
        uDao = new UserAccess();
        uDao.openConnection();

        // and a new User with random data
        bestEvent = new User("my fake username", "my fake password", "my fake email62", "John", "Doe", "m", "12345");

        //Let's clear the database as well so any lingering data doesn't affect our tests
        uDao.clear();
    }

    @AfterEach
    public void tearDown() {
        // Here we close the connection to the database file, so it can be opened again later.
        // We will set commit to false because we do not want to save the changes to the database
        // between test cases.
        uDao.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        // Start by inserting an User into the database.
        uDao.addUser(bestEvent);
        // Let's use a find method to get the User that we just put in back out.
        User compareTest = uDao.getUser(bestEvent.getUsername());
        // First lets see if our find method found anything at all. If it did then we know that we got
        // something back from our database.
        assertNotNull(compareTest);
        // Now lets make sure that what we put in is the same as what we got out. If this
        // passes then we know that our insert did put something in, and that it didn't change the
        // data in any way.
        // This assertion works by calling the equals method in the User class.
        assertEquals(bestEvent, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        // Let's do this test again, but this time lets try to make it fail.
        // If we call the method the first time the User will be inserted successfully.
        uDao.addUser(bestEvent);

        // However, our sql table is set up so that the column "eventID" must be unique, so trying to insert
        // the same User again will cause the insert method to throw an exception, and we can verify this
        // behavior by using the assertThrows assertion as shown below.

        // Note: This call uses a lambda function. A lambda function runs the code that comes after
        // the "()->", and the assertThrows assertion expects the code that ran to throw an
        // instance of the class in the first parameter, which in this case is a DataAccessException.
        assertThrows(DataAccessException.class, () -> uDao.addUser(bestEvent));
    }

    @Test
    public void getUserPass() throws DataAccessException {
        uDao.addUser(bestEvent);
        User compareTest = uDao.getUser(bestEvent.getUsername());
        assertNotNull(compareTest);
        assertEquals(bestEvent, compareTest);
    }

    @Test
    public void getUserFail() throws DataAccessException {
        uDao.addUser(bestEvent);
        User compareTest = uDao.getUser("fake username");
        assertNull(compareTest);
    }

    @Test
    public void clearUserTable() throws DataAccessException {
        uDao.addUser(bestEvent);
        uDao.clearUserTable();
        User compareTest = uDao.getUser(bestEvent.getUsername());
        assertNull(compareTest);
    }
}
