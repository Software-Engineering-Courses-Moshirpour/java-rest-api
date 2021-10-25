package ca.ucalgary.ensf609.sample.app.errors;

class InvalidRequestException extends ApplicationException {

    public InvalidRequestException(int code, String message) {
        super(code, message);
    }
}
