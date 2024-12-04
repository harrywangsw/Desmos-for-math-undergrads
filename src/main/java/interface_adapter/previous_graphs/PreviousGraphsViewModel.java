package interface_adapter.previous_graphs;

import interface_adapter.ViewModel;

public class PreviousGraphsViewModel extends ViewModel<PreviousGraphsState> {

    public PreviousGraphsViewModel() {
        super("Previous Graphs");
        setState(new PreviousGraphsState());
    }
}
