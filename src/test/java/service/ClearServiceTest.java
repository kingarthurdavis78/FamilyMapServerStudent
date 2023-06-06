package service;
import com.google.gson.Gson;
import dao.*;

import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.LoadRequest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ClearServiceTest {


    @BeforeEach
    public void setUp() throws FileNotFoundException {
        Gson gson = new Gson();
        LoadService loadService = new LoadService();
        FileReader fr = new FileReader("passoffFiles/LoadData.json");
        LoadRequest loadRequest = gson.fromJson(fr, LoadRequest.class);
        loadService.load(loadRequest);
    }

    @Test
    public void clear() {
        ClearService clearService = new ClearService();
        clearService.clear();

        DatabaseAccess dao = new DatabaseAccess();
        try {
            dao.openConnection();
            UserAccess userAccess = new UserAccess(dao.getConn());
            PersonAccess personAccess = new PersonAccess(dao.getConn());
            EventAccess eventAccess = new EventAccess(dao.getConn());
            AuthTokenAccess authTokenAccess = new AuthTokenAccess(dao.getConn());

            assertNull(userAccess.getUser("sheila"));
            assertNull(personAccess.getPerson("Sheila_Parker"));
            assertNull(eventAccess.getEvent("Sheila_Family_Map"));
            assertNull(authTokenAccess.getAuthToken("sheila"));

            dao.closeConnection(true);
        } catch (DataAccessException e) {
            dao.closeConnection(false);
            e.printStackTrace();
        }
    }

}
