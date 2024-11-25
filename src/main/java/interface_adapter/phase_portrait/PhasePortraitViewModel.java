package interface_adapter.phase_portrait;

import interface_adapter.ViewModel;
import interface_adapter.note.NoteState;

public class PhasePortraitViewModel extends ViewModel<PhasePortraitState> {
    public PhasePortraitViewModel() {
        super("note");
        setState(new PhasePortraitState());
    }
}
