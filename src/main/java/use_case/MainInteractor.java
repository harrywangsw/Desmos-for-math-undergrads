package use_case;

import java.util.Arrays;

import entity.OdeSystem;
import interface_adapter.ViewManagerModel;
import interface_adapter.graph.GraphPresenter;
import interface_adapter.graph.GraphState;
import interface_adapter.graph.GraphViewModel;
import use_case.graph.GraphOutputBoundary;
import use_case.main.MainInputBoundary;
import use_case.note.DataAccessException;
import view.GraphView;
import view.PhasePortraitView;

/**
 * Interactor for the MainView that handles the run functionality.
 */
public class MainInteractor implements MainInputBoundary {

    private final PhasePortraitInteractor phasePortraitInteractor;
    private final PreviousGraphsInteractor previousGraphsInteractor;
    private final ViewManagerModel viewManagerModel;

    public MainInteractor(PhasePortraitInteractor phasePortraitInteractor, ViewManagerModel viewManagerModel,
                          PreviousGraphsInteractor previousGraphsInteractor) {
        this.phasePortraitInteractor = phasePortraitInteractor;
        this.previousGraphsInteractor = previousGraphsInteractor;
        this.viewManagerModel = viewManagerModel;
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
            interactor.makegraph(system);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void executePhasePortrait(String[] equations) throws Exception {
        final OdeSystem system = new OdeSystem(equations,
                Arrays.copyOfRange(OdeSystem.VARIABLES, 0, equations.length));
//        PhasePortraitViewModel viewModel = new PhasePortraitViewModel(new PhasePortraitState(system));
//        PhasePortraitOutputBoundary outputboundary = new PhasePortraitPresenter(viewModel);
//        PhasePortraitDataAccessInterface dataAccessInterface = new NewtonDataAccessObject();
//        PhasePortraitInteractor interactor = new PhasePortraitInteractor(dataAccessInterface, outputboundary);
//        interactor.makePhase(viewModel, outputboundary, system);
        phasePortraitInteractor.makePhase(system);

        viewManagerModel.setState(PhasePortraitView.viewName);
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void executeHelp() {
        System.out.println("You just need to believe hard enough.");
    }

    @Override
    public void executePreviousGraphs() throws DataAccessException {
//        PreviousGraphsInteractor previousGraphsInteractor = new PreviousGraphsInteractor(previousGraphsOutputBoundary);
        previousGraphsInteractor.executePreviousGraphs();

    }

    @Override
    public void executeHome() {
        viewManagerModel.setState("Home");
        viewManagerModel.firePropertyChanged();
    }
}
