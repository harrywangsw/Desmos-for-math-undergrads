package use_case;

import app.PhasePortraitAppBuilder;
import data_access.NewtonDataAccessObject;
import entity.ODESystem;
import interface_adapter.phaseportrait.PhasePortraitPresenter;
import interface_adapter.phaseportrait.PhasePortraitState;
import interface_adapter.phaseportrait.PhasePortraitViewModel;
import use_case.main.GraphDataAccessInterface;
import use_case.main.MainInputBoundary;

import use_case.note.DataAccessException;

import use_case.phaseportrait.PhasePortraitDataAccessInterface;

import use_case.phaseportrait.PhasePortraitOutputBoundary;
import view.GraphView;

import java.util.Arrays;

public class MainInteractor implements MainInputBoundary {

    private final GraphDataAccessInterface graphDataAccessInterface;
    private final MainOutputBoundary mainOutputBoundary;
    private final PreviousGraphsOutputBoundary previousGraphsOutputBoundary;

    public MainInteractor(GraphDataAccessInterface graphDataAccessInterface, MainOutputBoundary mainOutputBoundary,
                          PreviousGraphsOutputBoundary previousGraphsOutputBoundary) {
        this.graphDataAccessInterface = graphDataAccessInterface;
        this.mainOutputBoundary = mainOutputBoundary;
        this.previousGraphsOutputBoundary = previousGraphsOutputBoundary;
    }


    @Override
    public void executePlot(String[] equations) {
        final ODESystem system = new ODESystem(equations,
                Arrays.copyOfRange(ODESystem.VARIABLES, 0, equations.length));
        system.setInitialConditions(new Float[]{1.2F, 2.0F});
        System.out.println("Temporary Plot");
        try {
            GraphView.plotGraph(system).setVisible(true);   //TODO
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void executePhasePotrait(String[] equations) throws Exception {
        final ODESystem system = new ODESystem(equations,
                Arrays.copyOfRange(ODESystem.VARIABLES, 0, equations.length));
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
        PreviousGraphsInteractor previousGraphsInteractor = new PreviousGraphsInteractor(graphDataAccessInterface, previousGraphsOutputBoundary);
        previousGraphsInteractor.executePreviousGraphs();

    }
}
