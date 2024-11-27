package interface_adapter.phase_portrait;

import entity.ODESystem;
import interface_adapter.ViewModel;

import java.util.List;

public class PhasePortraitViewModel extends ViewModel<PhasePortraitState> {
    public PhasePortraitState state;
    public PhasePortraitViewModel(PhasePortraitState state) {
        super("phase portrait");
        setState(new PhasePortraitState());
        this.state = state;
    }
}
