package use_case.note;

import java.sql.SQLException;

/**
 * Exception thrown when there is an error with accessing data.
 */
public class DataAccessException extends Exception {
    public DataAccessException(String string, SQLException rollbackException) {
        super(string);
    }
}
