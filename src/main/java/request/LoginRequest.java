package request;

/**
 * LoginRequest
 */
public class LoginRequest {
    /**
     * Username
     */
    private String username;

    /**
     * Password
     */
    private String password;

    /**
     * Constructor
     */
    public LoginRequest(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
