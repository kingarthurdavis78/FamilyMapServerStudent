package model;


/**
 * Authtoken Object
 */
public class Authtoken {
    /**
    * Unique authtoken
    */
    String authtoken;

    /**
     * Username that is associated with the authtoken
     */
    String username;

    /**
     * Authtoken constructor
     */
    public Authtoken(String authtoken, String username) {
        this.authtoken = authtoken;
        this.username = username;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o instanceof Authtoken) {
            Authtoken oAuthToken = (Authtoken) o;
            return oAuthToken.getAuthtoken().equals(getAuthtoken()) &&
                    oAuthToken.getUsername().equals(getUsername());
        } else {
            return false;
        }
    }
}
