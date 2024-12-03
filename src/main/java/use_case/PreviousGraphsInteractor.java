package use_case;

import use_case.main.GraphDataAccessInterface;
import use_case.note.DataAccessException;
import use_case.previous_graphs.PreviousGraphsInputBoundary;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PreviousGraphsInteractor implements PreviousGraphsInputBoundary {
//    private final GraphDataAccessInterface graphDataAccessInterface;
    private final PreviousGraphsOutputBoundary previousGraphsOutputBoundary;

    public PreviousGraphsInteractor(PreviousGraphsOutputBoundary previousGraphsOutputBoundary) {
        this.previousGraphsOutputBoundary = previousGraphsOutputBoundary;
    }

    @Override
    public void executePreviousGraphs() throws DataAccessException {
        List<String> graphPaths = new ArrayList<>();

        File graphFolder = new File("./graphs/");

        if (graphFolder.exists() && graphFolder.isDirectory()) {
            File[] files = graphFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        graphPaths.add(file.getAbsolutePath());
                    }
                }
            }
        }

        previousGraphsOutputBoundary.displayGraphs(graphPaths);
    }
}
