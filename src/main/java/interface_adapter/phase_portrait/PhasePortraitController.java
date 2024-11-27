package interface_adapter.phase_portrait;

import use_case.PhasePortraitInteractor;
import use_case.phase_portrait.PhasePortraitOutputBoundary;

public class PhasePortraitController {

    private final PhasePortraitInteractor phasePortraitInteractor;

    public PhasePortraitController(PhasePortraitInteractor phasePortraitInteractor, PhasePortraitOutputBoundary phasePortraitOutputBoundary) {
        this.phasePortraitInteractor = phasePortraitInteractor;
    }

    public void change_scale(PhasePortraitState state, float vector_scale) {
        phasePortraitInteractor.change_scale(state.getunit_vectors(), vector_scale);
    }

    public void change_viewbox(PhasePortraitState state, float lb, float rb, float lowerbound, float upperbound) throws Exception {
        phasePortraitInteractor.change_viewbox(state.getsystem().getEquations(), state.getsystem().getVariables(), lowerbound, upperbound, lb, rb, state.getscale());
    }
}
