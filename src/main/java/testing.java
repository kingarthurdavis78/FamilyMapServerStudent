import com.google.gson.Gson;
import dao.*;
import model.*;

public class testing {
    public static void main(String[] args) throws DataAccessException{
            User user = new User("username", "password", "email", "first", "last", "m", "idddddd");
            Event event = new Event("eventID2", "username", "personID2", 6.0F, 72.0F, "countr2y", "ci2ty", "ev2entType", 1992);
            String json = new Gson().toJson(event);
            String json2 = new Gson().toJson(user);
            System.out.println(json);
            System.out.println(json2);

            EventAccess eventAccess = new EventAccess();
            eventAccess.openConnection();
            eventAccess.addEvent(event);
            eventAccess.closeConnection(true);


    }
}
