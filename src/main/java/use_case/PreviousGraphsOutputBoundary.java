package use_case;

import entity.Graph;

import java.util.List;
import java.util.Map;

public interface PreviousGraphsOutputBoundary {

    void displayGraphs(List<Map<String, String>> graphList);

    void preparePreviousGraphFailView(String error);
}
