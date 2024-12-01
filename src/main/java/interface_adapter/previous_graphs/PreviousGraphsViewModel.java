package interface_adapter.previous_graphs;

import interface_adapter.ViewModel;
import interface_adapter.note.NoteState;

public class PreviousGraphsViewModel extends ViewModel<PreviousGraphsState> {

    public PreviousGraphsViewModel() {
        super("Previous Graphs");
        setState(new PreviousGraphsState());
    }
}
