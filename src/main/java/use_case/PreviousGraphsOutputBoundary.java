package use_case;

import java.util.List;

public interface PreviousGraphsOutputBoundary {

    void displayGraphs(List<String> graphList);

    void preparePreviousGraphFailView(String error);
}
