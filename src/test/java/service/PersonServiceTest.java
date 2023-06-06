package service;

import com.google.gson.Gson;
import model.Person;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.LoadRequest;
import result.GetPersonResponse;
import result.GetFamilyResponse;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.*;

public class PersonServiceTest {
    

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
        public void PersonTest() {
            PersonService PersonService = new PersonService();
            User user = new User("sheila", "parker", "sheila@parker.com", "sheila", "parker", "f", "Sheila_Parker");
            GetPersonResponse res = PersonService.getPerson(user, "Sheila_Parker");
            Person actual = res.toPerson();


            Person expected = new Person("Sheila_Parker", "sheila", "Sheila", "Parker", "f", "Blaine_McGary", "Betty_White", "Davis_Hyer");

            assertTrue(res.getSuccess());
            assertEquals(expected, actual);
        }

        @Test
        public void PersonTestFail() {
            PersonService PersonService = new PersonService();
            User fakeUser = new User("fake", "fake", "fake", "fake", "fake", "fake", "fake");
            GetPersonResponse res = PersonService.getPerson(fakeUser, "fake");

            assertFalse(res.getSuccess());
            assertEquals("Error: Person not found", res.getMessage());
        }

        @Test
        public void allFamilyTest() {
            PersonService PersonService = new PersonService();
            GetFamilyResponse response = PersonService.getFamily("sheila");

            assertTrue(response.getSuccess());
            assertEquals(8, response.getData().length);
            assertEquals(Person.class, response.getData()[0].getClass());
        }

        @Test
        public void allFamilyTestFail() {
            PersonService familyService = new PersonService();
            GetFamilyResponse response = familyService.getFamily("wrong username");

            assertFalse(response.getSuccess());
            assertEquals("Error: No family members found", response.getMessage());
        }




}
