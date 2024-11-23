package data_access;

import use_case.main.GraphDataAccessInterface;
import use_case.note.DataAccessException;

import java.util.Map;

public class DBGraphDataAccessObject implements GraphDataAccessInterface {

    @Override
    public String saveGraph(Map<String, String> graphMap) throws DataAccessException {
        return "";
    }

    @Override
    public String loadGraph() throws DataAccessException {
        return "";
    }
}
