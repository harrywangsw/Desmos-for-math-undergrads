package use_case.main;

import use_case.note.DataAccessException;

import java.util.Map;
import java.util.List;

public interface GraphDataAccessInterface {

    String saveGraph(Map<String, String> graphMap) throws DataAccessException;

    List<Map<String, String>> loadAllGraphs() throws DataAccessException;
}
