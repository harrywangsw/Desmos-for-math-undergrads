package use_case.main;

import use_case.note.DataAccessException;

import javax.xml.crypto.Data;
import java.util.Map;

public interface GraphDataAccessInterface {

    String saveGraph(Map<String, String> graphMap) throws DataAccessException;

    String loadGraph() throws DataAccessException;
}
