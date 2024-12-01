package use_case.main;

import entity.Graph;
import use_case.note.DataAccessException;

import javax.xml.crypto.Data;
import java.util.Map;
import java.util.List;

public interface GraphDataAccessInterface {

    String saveGraph(Map<String, String> graphMap) throws DataAccessException;

    List<Graph> loadAllGraphs() throws DataAccessException;
}
