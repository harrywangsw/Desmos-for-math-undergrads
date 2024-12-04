package use_case.main;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

@Nullable
public interface GraphDataAccessInterface {

    /**
     * Save the graph.
     * @param graphMap the graph map
     * @return String
     * @throws DataAccessException DataAccessException
     */

    String saveGraph(Map<String, String> graphMap) throws DataAccessException;
    /**
     * Load all graphs.
     * @return List of Map (String, String)
     * @throws DataAccessException DataAccessException
     */

    List<Map<String, String>> loadAllGraphs() throws DataAccessException;
}
