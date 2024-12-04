package use_case;

import data_access.NewtonDataAccessObject;
import entity.OdeSystem;
import interface_adapter.graph.GraphPresenter;
import interface_adapter.graph.GraphState;
import interface_adapter.graph.GraphViewModel;
import interface_adapter.phaseportrait.PhasePortraitPresenter;
import interface_adapter.phaseportrait.PhasePortraitState;
import interface_adapter.phaseportrait.PhasePortraitViewModel;
import use_case.graph.GraphOutputBoundary;
import use_case.main.MainInputBoundary;

import use_case.main.DataAccessException;

import use_case.phaseportrait.PhasePortraitDataAccessInterface;

import use_case.phaseportrait.PhasePortraitOutputBoundary;

import java.util.Arrays;

public class MainInteractor implements MainInputBoundary {

    private final MainOutputBoundary mainOutputBoundary;
    private final PreviousGraphsOutputBoundary previousGraphsOutputBoundary;

    public MainInteractor(MainOutputBoundary mainOutputBoundary,
                          PreviousGraphsOutputBoundary previousGraphsOutputBoundary) {
        this.mainOutputBoundary = mainOutputBoundary;
        this.previousGraphsOutputBoundary = previousGraphsOutputBoundary;
    }


    @Override
    public void executePlot(String[] equations) {
        final OdeSystem system = new OdeSystem(equations,
                Arrays.copyOfRange(OdeSystem.VARIABLES, 0, equations.length));
        system.setInitialConditions(new Float[]{1.2F});
        System.out.println("Temporary Plot");
        try {
            GraphState state = new GraphState();
            GraphViewModel viewModel = new GraphViewModel(state);
            GraphOutputBoundary boundary = new GraphPresenter(viewModel);
            GraphInteractor interactor = new GraphInteractor(boundary);
            interactor.makegraph(system, viewModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void executePhasePortrait(String[] equations) throws Exception {
        final OdeSystem system = new OdeSystem(equations,
                Arrays.copyOfRange(OdeSystem.VARIABLES, 0, equations.length));
        PhasePortraitViewModel viewModel = new PhasePortraitViewModel(new PhasePortraitState(system));
        PhasePortraitOutputBoundary outputboundary = new PhasePortraitPresenter(viewModel);
        PhasePortraitDataAccessInterface dataAccessInterface = new NewtonDataAccessObject();
        PhasePortraitInteractor interactor = new PhasePortraitInteractor(dataAccessInterface, outputboundary);
        interactor.makePhase(viewModel, outputboundary, system);
    }

    @Override
    public void executeHelp() {
        System.out.println("You just need to believe hard enough.");
    }

    @Override
    public void executePreviousGraphs() throws DataAccessException {
        PreviousGraphsInteractor previousGraphsInteractor = new PreviousGraphsInteractor(previousGraphsOutputBoundary);
        previousGraphsInteractor.executePreviousGraphs();

    }
}
