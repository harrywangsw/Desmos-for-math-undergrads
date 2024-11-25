package use_case.equations;

/**
 * Exception thrown when there is an error with accessing data.
 */
public class APIAccessException extends Exception {
    public APIAccessException(String string) {
        super(string);
    }
}
