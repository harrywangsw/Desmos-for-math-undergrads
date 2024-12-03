package interface_adapter.graph;

import interface_adapter.ViewModel;

public class GraphViewModel extends ViewModel<GraphState> {
    public GraphViewModel(GraphState state) {
        super("Time series graph");
        setState(state);
    }
}
