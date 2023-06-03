import com.google.gson.Gson;
import dao.*;
import model.*;

public class testing {
    public static void main(String[] args) throws DataAccessException{
            User user = new User("username", "password", "email", "first", "last", "m", "idddddd");
            Person person = new Person("123", "username", "123", "123", "123", "123", "123", "123");
            String json = new Gson().toJson(person);
            String json2 = new Gson().toJson(user);
            System.out.println(json);
            System.out.println(json2);

            UserAccess userAccess = new UserAccess();
            userAccess.openConnection();
            userAccess.addUser(user);
            userAccess.closeConnection(true);

    }
}
