package interface_adapter.previous_graphs;

import java.util.List;
import java.util.Map;

public class PreviousGraphsState {
    private List<Map<String, String>> previousGraphs;
    private String error;

    public List<Map<String, String>> getPreviousGraphs() {
        return previousGraphs;
    }

    public void setPreviousGraphs(List<Map<String, String>> previousGraphs) {
        this.previousGraphs = previousGraphs;
    }

    public void setError(String errorMessage) {
        this.error = errorMessage;
    }

    public String getError() {
        return error;
    }
}
