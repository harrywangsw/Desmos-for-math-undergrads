package interface_adapter.previous_graphs;

import java.util.List;
import java.util.Map;

public class PreviousGraphsState {
    private List<String> previousGraphs;
    private String error;

    public List<String> getPreviousGraphs() {
        return previousGraphs;
    }

    public void setPreviousGraphs(List<String> previousGraphs) {
        this.previousGraphs = previousGraphs;
    }

    public void setError(String errorMessage) {
        this.error = errorMessage;
    }

    public String getError() {
        return error;
    }
}
