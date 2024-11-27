package interface_adapter.phase_portrait;

import entity.ODESystem;
import interface_adapter.ViewModel;

import java.util.List;

public class PhasePortraitViewModel extends ViewModel<PhasePortraitState> {
    public PhasePortraitViewModel(PhasePortraitState state) {
        super("phase portrait");
        setState(state);
    }
}
