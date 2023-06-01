package service;

import dao.UserAccess;
import dao.AuthTokenAccess;
import dao.DataAccessException;
import model.Authtoken;
import model.User;
import request.LoginRequest;
import result.LoginResponse;

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
                return new LoginResponse("Error: Invalid username", false);
            }

            if (!user.getPassword().equals(request.getPassword())) {
                return new LoginResponse("Error: Invalid password", false);
            }

            AuthTokenAccess authTokenAccess = new AuthTokenAccess();
            authTokenAccess.openConnection();
            Authtoken authToken = authTokenAccess.getAuthToken(user.getUsername());
            authTokenAccess.closeConnection(false);

            if (authToken == null) {
                return new LoginResponse("Error: Invalid username", false);
            }

            return new LoginResponse(authToken.getAuthtoken(), user.getUsername(), user.getPersonID(), true);
        } catch (DataAccessException ex) {
            return new LoginResponse("Error: " + ex.getMessage(), false);
        }
    }
}
