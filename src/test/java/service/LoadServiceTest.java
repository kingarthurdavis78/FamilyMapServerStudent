package service;

import com.google.gson.Gson;
import model.Event;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.LoadRequest;
import result.LoadResponse;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.*;
public class LoadServiceTest {

    @BeforeEach
    public void setUp() {
        ClearService clearService = new ClearService();
        clearService.clear();
    }

    @AfterEach
    public void tearDown() {
        ClearService clearService = new ClearService();
        clearService.clear();
    }

    @Test
    public void loadTest() {
        LoadService loadService = new LoadService();
        Gson gson = new Gson();
        try {
            FileReader fr = new FileReader("passoffFiles/LoadData.json");
            LoadRequest loadRequest = gson.fromJson(fr, LoadRequest.class);
            LoadResponse response = loadService.load(loadRequest);
            assertTrue(response.getSuccess());
            assertEquals(response.getMessage(), "Successfully added 2 users, 11 persons, and 19 events to the database.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loadTestFail() {
        LoadService loadService = new LoadService();
        LoadRequest loadRequest = new LoadRequest();
        LoadResponse response = loadService.load(loadRequest);
        assertFalse(response.getSuccess());
        assertEquals(response.getMessage(), "Error: Invalid request data");
    }



}
