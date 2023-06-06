package service;

import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.LoadRequest;
import result.FillResponse;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.*;

public class FillServiceTest {
    @BeforeEach
    public void setUp() throws FileNotFoundException {
        ClearService clearService = new ClearService();
        clearService.clear();

        Gson gson = new Gson();
        LoadService loadService = new LoadService();
        FileReader fr = new FileReader("passoffFiles/LoadData.json");
        LoadRequest loadRequest = gson.fromJson(fr, LoadRequest.class);
        loadService.load(loadRequest);

    }

    @AfterEach
    public void tearDown() {
        ClearService clearService = new ClearService();
        clearService.clear();
    }

    @Test
    public void fillTest() {
        FillService fillService = new FillService();
        FillResponse response = fillService.fill("sheila", 4);
        assertTrue(response.getSuccess());
        assertEquals(response.getMessage(), "Successfully added 31 persons and 92 events to the database.");
    }

    @Test
    public void fillTestFail() {
        FillService fillService = new FillService();
        FillResponse response = fillService.fill("wrong username", 4);
        assertFalse(response.getSuccess());
        assertEquals(response.getMessage(), "Error: Invalid username");
    }
}
