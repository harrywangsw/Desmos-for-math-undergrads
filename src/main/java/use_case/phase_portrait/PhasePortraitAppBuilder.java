package use_case.phase_portrait;

import data_access.NewtonDataAccessObject;
import entity.ODESystem;
import interface_adapter.phase_portrait.PhasePortraitController;
import interface_adapter.phase_portrait.PhasePortraitPresenter;
import interface_adapter.phase_portrait.PhasePortraitState;
import interface_adapter.phase_portrait.PhasePortraitViewModel;
import org.jfree.chart.JFreeChart;
import use_case.PhasePortraitInteractor;
import view.PhasePortraitView;

import javax.swing.*;
import java.util.List;

public class PhasePortraitAppBuilder {
    public static final int HEIGHT = 300;
    public static final int WIDTH = 400;

    public static void main(String[] args) throws Exception {
        String[] vars = {"x", "y"};
        String[] exps = {"y+2x", "-2x"};
        ODESystem sys = new ODESystem(exps, vars);
        make_phase(sys);
    }

    public static void make_phase(ODESystem sys) throws Exception {

        PhasePortraitViewModel viewModel = new PhasePortraitViewModel(new PhasePortraitState(sys));
        PhasePortraitOutputBoundary outputboundary = new PhasePortraitPresenter(viewModel);
        PhasePortraitDataAccessInterface dataAccessInterface = new NewtonDataAccessObject();
        PhasePortraitInteractor interactor = new PhasePortraitInteractor(dataAccessInterface, outputboundary);
        List<List<Float>> unit_vectors = interactor.create_phase_vectors(sys.getEquations(), sys.getVariables());
        JFreeChart plot = interactor.create_chart(unit_vectors);
        PhasePortraitState state = new PhasePortraitState(sys, plot, unit_vectors, -1, 1, 1, -1, 1);
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
