package service;

import com.google.gson.Gson;
import model.Event;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import request.LoadRequest;
import result.GetEventResponse;
import result.GetEventsResponse;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class EventServiceTest {

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
    public void eventTest() {
        EventService eventService = new EventService();
        GetEventResponse res = eventService.getEvent("sheila", "Sheila_Asteroids");
        Event actual = res.toEvent();


        Event expected = new Event("Sheila_Asteroids", "sheila", "Sheila_Parker", 77.4666976928711F, -68.7667007446289F, "Denmark", "Qaanaaq", "completed asteroids", 2014);

        assertTrue(res.isSuccess());
        assertEquals(expected, actual);
    }

    @Test
    public void eventTestFail() {
        EventService eventService = new EventService();
        GetEventResponse res = eventService.getEvent("wrong username", "Sheila_Asteroids");

        assertFalse(res.isSuccess());
        assertEquals("Error: event is not associated with this user", res.getMessage());
    }

    @Test
    public void allEventsTest() {
        EventService eventService = new EventService();
        GetEventsResponse response = eventService.getAllEvents("sheila");

        assertTrue(response.getSuccess());
        assertEquals(16, response.getData().length);
        assertEquals(Event.class, response.getData()[0].getClass());
    }

    @Test
    public void allEventsTestFail() {
        EventService eventService = new EventService();
        GetEventsResponse response = eventService.getAllEvents("wrong username");

        assertFalse(response.getSuccess());
        assertEquals("Error: No family members found", response.getMessage());
    }


}
