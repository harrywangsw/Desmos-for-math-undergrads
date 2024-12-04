package use_case;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import use_case.note.DataAccessException;
import use_case.previous_graphs.PreviousGraphsInputBoundary;

public class PreviousGraphsInteractor implements PreviousGraphsInputBoundary {
    private final PreviousGraphsOutputBoundary previousGraphsOutputBoundary;

    public PreviousGraphsInteractor(PreviousGraphsOutputBoundary previousGraphsOutputBoundary) {
        this.previousGraphsOutputBoundary = previousGraphsOutputBoundary;
    }

    @Override
    public void executePreviousGraphs() throws DataAccessException {
        final List<String> graphPaths = new ArrayList<>();

        final File graphFolder = new File("./graphs/");

        if (graphFolder.exists() && graphFolder.isDirectory()) {
            final File[] files = graphFolder.listFiles();
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
