package use_case.equations;

/**
 * Exception thrown when there is an error with accessing data.
 */
public class ApiAccessException extends Exception {
    public ApiAccessException(String string) {
        super(string);
    }
}
