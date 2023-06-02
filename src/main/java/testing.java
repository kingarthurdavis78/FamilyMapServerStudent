import dao.*;
import model.*;

public class testing {
    public static void main(String[] args) {
        try {
            UserAccess userAccess = new UserAccess();
            userAccess.openConnection();
            userAccess.deleteUser("test");
            userAccess.closeConnection(true);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}
