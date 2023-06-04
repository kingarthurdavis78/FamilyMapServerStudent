package service;

import dao.UserAccess;
import model.User;
import request.LoginRequest;
import result.FillResponse;
import result.LoginResponse;
import result.RegisterResponse;
import dao.DataAccessException;
import request.RegisterRequest;

/**
 * RegisterService Object
 */
public class RegisterService {

    private String generateID() {
        return java.util.UUID.randomUUID().toString();
    }

    /**
     * The register method
     */
    public RegisterResponse register(RegisterRequest request) {
        String id = generateID();
        User user = new User(request.getUsername(), request.getPassword(), request.getEmail(), request.getFirstName(), request.getLastName(), request.getGender(), id);

//        FillService fillService = new FillService();
//        FillResponse fillRes = fillService.fill(user.getUsername());
//        if (!fillRes.getSuccess()) {
//            return new RegisterResponse("Error: " + fillRes.getMessage(), false);
//        }
        UserAccess userAccess = new UserAccess();
        try {
            userAccess.openConnection();
            userAccess.addUser(user);
            userAccess.closeConnection(true);

            LoginService loginService = new LoginService();
            LoginRequest loginRequest = new LoginRequest(user.getUsername(), user.getPassword());
            LoginResponse loginRes = loginService.login(loginRequest);
            if (!loginRes.getSuccess()) {
                return new RegisterResponse(loginRes.getMessage(), false);
            }

            return new RegisterResponse(id, user.getUsername(), user.getPersonID(), true);

        } catch (DataAccessException ex) {
            userAccess.closeConnection(false);
            return new RegisterResponse(ex.getMessage(), false);
        }
    }

}
