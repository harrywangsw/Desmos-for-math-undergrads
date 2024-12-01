package use_case;

import data_access.DBGraphDataAccessObject;
import entity.Graph;
import use_case.main.GraphDataAccessInterface;
import use_case.note.DataAccessException;
import use_case.previous_graphs.PreviousGraphsInputBoundary;

public class PreviousGraphsInteractor implements PreviousGraphsInputBoundary {
    private final GraphDataAccessInterface graphDataAccessInterface;
    private final PreviousGraphsOutputBoundary previousGraphsOutputBoundary;

    public PreviousGraphsInteractor(GraphDataAccessInterface graphDataAccessInterface, PreviousGraphsOutputBoundary previousGraphsOutputBoundary) {
        this.graphDataAccessInterface = graphDataAccessInterface;
        this.previousGraphsOutputBoundary = previousGraphsOutputBoundary;
    }

    @Override
    public void executePreviousGraphs(String path) throws DataAccessException {
        graphDataAccessInterface.loadGraph(path);
    }
}
