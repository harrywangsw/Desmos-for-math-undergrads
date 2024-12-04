package use_case;

import java.util.List;

public interface PreviousGraphsOutputBoundary {

    /**
     * Display graphs.
     * @param graphList graph list
     */
    void displayGraphs(List<String> graphList);
    /**
     * Prepare previous graph fail view.
     * @param error error
     */

    void preparePreviousGraphFailView(String error);
}
