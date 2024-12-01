package interface_adapter.phaseportrait;

import interface_adapter.ViewModel;

/**
 * View model used to store and update/track changes to the state of the phase portrait.
 */
public class PhasePortraitViewModel extends ViewModel<PhasePortraitState> {
    public PhasePortraitViewModel(PhasePortraitState state) {
        super("phase portrait");
        setState(state);
    }
}
