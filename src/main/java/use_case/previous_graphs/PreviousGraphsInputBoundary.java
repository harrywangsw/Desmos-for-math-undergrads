package use_case.previous_graphs;

import use_case.note.DataAccessException;

public interface PreviousGraphsInputBoundary {

    void executePreviousGraphs(String path) throws DataAccessException;
}
