package use_case;

import data_access.DBGraphDataAccessObject;
import entity.Graph;
import use_case.main.GraphDataAccessInterface;
import use_case.note.DataAccessException;
import use_case.previous_graphs.PreviousGraphsInputBoundary;

import java.util.List;
import java.util.Map;

public class PreviousGraphsInteractor implements PreviousGraphsInputBoundary {
    private final GraphDataAccessInterface graphDataAccessInterface;
    private final PreviousGraphsOutputBoundary previousGraphsOutputBoundary;

    public PreviousGraphsInteractor(GraphDataAccessInterface graphDataAccessInterface, PreviousGraphsOutputBoundary previousGraphsOutputBoundary) {
        this.graphDataAccessInterface = graphDataAccessInterface;
        this.previousGraphsOutputBoundary = previousGraphsOutputBoundary;
    }

    @Override
    public void executePreviousGraphs() throws DataAccessException {
        List<Map<String, String>> graphList = graphDataAccessInterface.loadAllGraphs();
        previousGraphsOutputBoundary.displayGraphs(graphList);
    }
}
