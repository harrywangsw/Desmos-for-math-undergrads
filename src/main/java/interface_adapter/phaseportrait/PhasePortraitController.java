package interface_adapter.phaseportrait;

import use_case.PhasePortraitInteractor;
import use_case.equations.APIAccessException;
import use_case.phaseportrait.PhasePortraitOutputBoundary;

/**
 * A controller.
 */
public class PhasePortraitController {

    private final PhasePortraitInteractor phasePortraitInteractor;

    /**
     * Creat the controller.
     * @param phasePortraitInteractor interactor
     * @param phasePortraitOutputBoundary output
     */
    public PhasePortraitController(PhasePortraitInteractor phasePortraitInteractor,
                                   PhasePortraitOutputBoundary phasePortraitOutputBoundary) {
        this.phasePortraitInteractor = phasePortraitInteractor;
    }

    /**
     * Change scale.
     * @param state state of the phase portrait
     * @param vectorscale the desired scale
     */
    public void changescale(PhasePortraitState state, float vectorscale) {
        phasePortraitInteractor.changescale(state.getunit_vectors(), vectorscale);
    }

    /**
     * Change the view box of the plot.
     * @param state state of the app
     * @param leb left bound
     * @param rib right bound
     * @param lowerbound lower b
     * @param upperbound upper b
     * @throws APIAccessException when newton api returns error
     */
    public void changeviewbox(PhasePortraitState state, float leb, float rib,
                              float lowerbound, float upperbound) throws APIAccessException {
        phasePortraitInteractor.changeviewbox(state.getsystem().getEquations(),
                state.getsystem().getVariables(), lowerbound, upperbound, leb, rib, state.getscale());
    }
}
