package ca.ucalgary.ensf609.sample.app.errors;

public class UserNotFoundException extends ApplicationException {

    public UserNotFoundException(int code, String message) {
        super(code, message);
    }
}
