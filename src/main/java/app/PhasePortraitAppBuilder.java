package app;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.jfree.chart.JFreeChart;

import data_access.NewtonDataAccessObject;
import entity.ODESystem;
import interface_adapter.phaseportrait.PhasePortraitController;
import interface_adapter.phaseportrait.PhasePortraitPresenter;
import interface_adapter.phaseportrait.PhasePortraitState;
import interface_adapter.phaseportrait.PhasePortraitViewModel;
import use_case.PhasePortraitInteractor;
import use_case.phaseportrait.PhasePortraitDataAccessInterface;
import use_case.phaseportrait.PhasePortraitOutputBoundary;
import view.PhasePortraitView;

/**
 * App builder for phaseportrait.
 */
public class PhasePortraitAppBuilder {
    public static final int HEIGHT = 300;
    public static final int WIDTH = 400;

    /**
     * Builder function for phase portrait use case.
     * @param sys the ode system
     * @throws Exception when the api call returns error
     */
    public static void makePhase(ODESystem sys) throws Exception {

        PhasePortraitViewModel viewModel = new PhasePortraitViewModel(new PhasePortraitState(sys));
        PhasePortraitOutputBoundary outputboundary = new PhasePortraitPresenter(viewModel);
        PhasePortraitDataAccessInterface dataAccessInterface = new NewtonDataAccessObject();
        PhasePortraitInteractor interactor = new PhasePortraitInteractor(dataAccessInterface, outputboundary);
        List<List<Float>> unitvectors = interactor.createphasevectors(sys.getEquations(), sys.getVariables());
        JFreeChart plot = interactor.createchart(unitvectors);
        PhasePortraitState state = new PhasePortraitState(sys, plot, unitvectors, -1, 1, 1, -1, 1);
        viewModel.setState(state);
        PhasePortraitView view = new PhasePortraitView(viewModel, interactor);
        view.setPhasePortraitController(new PhasePortraitController(interactor, outputboundary));

        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Phase Portrait");
        frame.setSize(WIDTH, HEIGHT);
        frame.add(view);

        frame.setVisible(true);
    }
}
