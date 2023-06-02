package service;

import dao.UserAccess;
import dao.AuthTokenAccess;
import dao.DataAccessException;
import model.Authtoken;
import model.User;
import request.LoginRequest;
import result.LoginResponse;

import java.util.UUID;

/**
 * LoginService Object
 */
public class LoginService {

    /**
     * The login method
     */
    public LoginResponse login(LoginRequest request) {
        try {
            UserAccess userAccess = new UserAccess();
            userAccess.openConnection();
            User user = userAccess.getUser(request.getUsername());
            userAccess.closeConnection(false);

            if (user == null) {
                return new LoginResponse("Error: No user found", false);
            }

            if (!user.getPassword().equals(request.getPassword())) {
                return new LoginResponse("Error: Invalid password", false);
            }

            UUID uuid = UUID.randomUUID();
            String authtokenString = uuid.toString();
            Authtoken authtoken = new Authtoken(authtokenString, user.getUsername());
            AuthTokenAccess authTokenAccess = new AuthTokenAccess();
            authTokenAccess.openConnection();
            authTokenAccess.addAuthToken(authtoken);
            authTokenAccess.closeConnection(true);


            return new LoginResponse(authtokenString, user.getUsername(), user.getPersonID(), true);
        } catch (DataAccessException ex) {
            return new LoginResponse("Error: " + ex.getMessage(), false);
        }
    }
}
