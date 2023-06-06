package service;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import dao.*;
import model.Event;
import model.Person;
import result.FillResponse;
import model.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

/**
 * FillService Object
 */
public class FillService {

    private final Random rand = new Random();

    private String[] maleNames;

    private String[] femaleNames;

    private String[] surnames;

    private Location[] events;

    private int peopleAdded;
    private int eventsAdded;

    User user;

    private DatabaseAccess dao;

    private PersonAccess pDao;

    private EventAccess eDao;

    private UserAccess uDao;

    private static class Names {
        private String[] data;

        public String[] getData() {
            return data;
        }
    }

    private static class Location {
        String country;
        String city;
        float latitude;
        float longitude;

        public String getCountry() {
            return country;
        }

        public String getCity() {
            return city;
        }

        public float getLatitude() {
            return latitude;
        }

        public float getLongitude() {
            return longitude;
        }
    }

    private static class EventData {
        Location[] data;
        public Location[] getData() {
            return data;
        }

    }

    private void loadJson() throws FileNotFoundException {
        Gson gson = new Gson();

        FileReader fNames = new FileReader("json/fnames.json");
        Names femNames = gson.fromJson(fNames, Names.class);
        femaleNames = femNames.getData();

        FileReader mNames = new FileReader("json/mnames.json");
        Names malNames = gson.fromJson(mNames, Names.class);
        maleNames = malNames.getData();

        FileReader sNames = new FileReader("json/snames.json");
        Names surNames = gson.fromJson(sNames, Names.class);
        surnames = surNames.getData();

        FileReader eventData = new FileReader("json/locations.json");
        EventData event = gson.fromJson(eventData, EventData.class);
        events = event.getData();
    }



    private void addGeneration(Person theirKid, String gender, int kidsBirth, int generations) throws DataAccessException {
        if (generations == 0) {
            return;
        }

        // Get Person Data
        String firstName;
        String lastName;
        String ID;
        String spouseID;
        if (theirKid == null) {
            ID = user.getPersonID();
            spouseID = null;
            firstName = user.getFirstName();
            lastName = user.getLastName();
        } else if (gender.equals("m")) {
            ID = theirKid.getFatherID();
            spouseID = theirKid.getMotherID();
            int mNum = rand.nextInt(maleNames.length);
            firstName = maleNames[mNum];
            lastName = theirKid.getLastName();
        } else {
            ID = theirKid.getMotherID();
            spouseID = theirKid.getFatherID();
            int fNum = rand.nextInt(femaleNames.length);
            int sNum = rand.nextInt(surnames.length);
            firstName = femaleNames[fNum];
            lastName = surnames[sNum];
        }

        // Add Person
        Person person;
        String dadID = UUID.randomUUID().toString();
        String momID = UUID.randomUUID().toString();
        if (generations > 1) {
            person = new Person(ID, user.getUsername(), firstName, lastName, gender, dadID, momID, spouseID);
        } else {
            person = new Person(ID, user.getUsername(), firstName, lastName, gender, null, null, spouseID);
        }
        pDao.addPerson(person);
        peopleAdded++;

        // Add Events

        // Birth
        int bNum = rand.nextInt(events.length);
        Location birthData = events[bNum];
        int bYear = kidsBirth - 13 - rand.nextInt(15);
        Event birthEvent = new Event(UUID.randomUUID().toString(), user.getUsername(), ID, birthData.getLatitude(), birthData.getLongitude(), birthData.getCountry(), birthData.getCity(), "Birth", bYear);
        eDao.addEvent(birthEvent);
        eventsAdded++;

        // Parents Marriage
        if (generations > 1) {
            int mNum = rand.nextInt(events.length);
            Location marriageData = events[mNum];
            int mYear = bYear - rand.nextInt(5);
            Event dadMarriageEvent = new Event(UUID.randomUUID().toString(), user.getUsername(), dadID, marriageData.getLatitude(), marriageData.getLongitude(), marriageData.getCountry(), marriageData.getCity(), "Marriage", mYear);
            Event momMarriageEvent = new Event(UUID.randomUUID().toString(), user.getUsername(), momID, marriageData.getLatitude(), marriageData.getLongitude(), marriageData.getCountry(), marriageData.getCity(), "Marriage", mYear);
            eDao.addEvent(dadMarriageEvent);
            eDao.addEvent(momMarriageEvent);
            eventsAdded += 2;
        }

        // Death
        int dNum = rand.nextInt(events.length);
        Location deathData = events[dNum];
        int dYear = bYear + 60 + rand.nextInt(15);
        Event deathEvent = new Event(UUID.randomUUID().toString(), user.getUsername(), ID, deathData.getLatitude(), deathData.getLongitude(), deathData.getCountry(), deathData.getCity(), "Death", dYear);
        eDao.addEvent(deathEvent);
        eventsAdded++;

        addGeneration(person, "m", bYear, generations - 1);
        addGeneration(person, "f", bYear, generations - 1);
    }




    /**
     * The fill method
     */
    public FillResponse fill(String username, int numGenerations) {
        dao = new DatabaseAccess();
        try {
            loadJson();
            dao.openConnection();
            // Initialize DAOs
            uDao = new UserAccess(dao.getConn());
            pDao = new PersonAccess(dao.getConn());
            eDao = new EventAccess(dao.getConn());

            // Validate User
            user = uDao.getUser(username);
            if (user == null) {
                dao.closeConnection(false);
                return new FillResponse("Error: Invalid username", false);
            }

            // Clear User's Data
            pDao.deleteFamily(username);
            eDao.deleteFamilyEvents(username);

            // Add Family
            addGeneration(null, user.getGender(), 2021, numGenerations + 1);

            dao.closeConnection(true);
            return new FillResponse("Successfully added " + peopleAdded + " persons and " + eventsAdded + " events to the database.", true);
        } catch (Exception ex) {
            dao.closeConnection(false);
            return new FillResponse("Error: " + ex.getMessage(), false);
        }
    }
}
