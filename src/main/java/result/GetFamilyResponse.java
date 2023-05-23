package result;

import model.Person;

/**
 * GetFamilyResponse Object
 */
public class GetFamilyResponse {
    /**
     * The family members of the user
     */
    Person[] data;

    /**
     * The error message
     */
    String message;

    /**
     * The success boolean
     */
    Boolean success;


    /**
     * The GetFamilyResponse constructor
     */
    public GetFamilyResponse(String message) {
        this.message = message;
        this.success = false;
    }

    public GetFamilyResponse(Person[] data) {
        this.data = data;
        this.success = true;
    }
}
