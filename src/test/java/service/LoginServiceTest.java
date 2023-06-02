package service;

import dao.*;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.LoginRequest;
import result.LoginResponse;

import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTest {

    LoginRequest loginRequest = new LoginRequest("test", "test");

    User user = new User("test", "test", "test", "test", "test", "test", "test");

    UserAccess useraccess = new UserAccess();

    @BeforeEach
    public void setUp() throws DataAccessException{
        useraccess.openConnection();
        useraccess.addUser(user);
        useraccess.closeConnection(true);
    }

    @AfterEach
    public void tearDown() throws DataAccessException{
        useraccess.openConnection();
        useraccess.deleteUser(user.getUsername());
        useraccess.closeConnection(true);
    }

    @Test
    public void loginPass() {

        LoginService loginService = new LoginService();
        LoginResponse response = loginService.login(loginRequest);
        assertTrue(response.getSuccess());
        assertEquals("test", response.getUsername());

    }

    @Test
    public void loginFailNoUser() {

        LoginService loginService = new LoginService();
        LoginResponse response = loginService.login(new LoginRequest("not a real user", "test"));
        assertFalse(response.getSuccess());
        assertEquals("Error: No user found", response.getMessage());

    }

    @Test
    public void loginFailWrongPassword() {

        LoginService loginService = new LoginService();
        LoginResponse response = loginService.login(new LoginRequest("test", "not a real password"));
        assertFalse(response.getSuccess());
        assertEquals("Error: Invalid password", response.getMessage());

    }
}
