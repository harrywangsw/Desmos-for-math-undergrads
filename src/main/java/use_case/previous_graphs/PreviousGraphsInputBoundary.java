package use_case.previous_graphs;

import entity.Graph;
import use_case.note.DataAccessException;

import java.util.List;

public interface PreviousGraphsInputBoundary {

    void executePreviousGraphs() throws DataAccessException;
}
