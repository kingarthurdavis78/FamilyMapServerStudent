package service;

import dao.UserAccess;
import model.User;
import request.LoginRequest;
import result.FillResponse;
import result.LoadResponse;
import result.LoginResponse;
import service.FillService;
import dao.DataAccessException;
import request.RegisterRequest;
import result.ResisterResponse;

/**
 * RegisterService Object
 */
public class RegisterService {

    private String generateID() {
        // TODO: Generate a unique ID
        return null;
    }

    /**
     * The register method
     */
    public ResisterResponse register(RegisterRequest request) {
        String id = generateID();
        User user = new User(request.getUsername(), request.getPassword(), request.getEmail(), request.getFirstName(), request.getLastName(), request.getGender(), id);

        FillService fillService = new FillService();
        FillResponse fillRes = fillService.fill(user.getUsername());
        if (!fillRes.getSuccess()) {
            return new ResisterResponse("Error: " + fillRes.getMessage(), false);
        }

        LoginService loginService = new LoginService();
        LoginRequest loginRequest = new LoginRequest(user.getUsername(), user.getPassword());
        LoginResponse loginRes = loginService.login(loginRequest);
        if (!loginRes.getSuccess()) {
            return new ResisterResponse("Error: " + loginRes.getMessage(), false);
        }

        try {
            UserAccess userAccess = new UserAccess();
            userAccess.addUser(user);
            userAccess.closeConnection(true);
            return new ResisterResponse(id, user.getUsername(), user.getPersonID(), true);
        } catch (DataAccessException ex) {
            return new ResisterResponse("Error: " + ex.getMessage(), false);
        }
    }

}
