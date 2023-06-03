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

    public Person[] getData() {
        return data;
    }

    public void setData(Person[] data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
