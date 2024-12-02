package app;

import view.PhasePortraitView;

import javax.swing.*;

public class PreviousGraphsBuilder {
    public static final int HEIGHT = 600;
    public static final int WIDTH = 800;

//    public static void main(String[] args) throws Exception {
//        String[] vars = {"x", "y"};
//        String[] exps = {"x", "y"};
//        ODESystem sys = new ODESystem(exps, vars);
//        makePhase(sys);
//    }

    /**
     * Builder function for phase portrait use case.
     * @param view the Jframe containing the phase portrait's view
     * @throws Exception when the api call returns error
     */
    public static void makeView(PhasePortraitView view) throws Exception {

//        PhasePortraitViewModel viewModel = new PhasePortraitViewModel(new PhasePortraitState(sys));
//        PhasePortraitOutputBoundary outputboundary = new PhasePortraitPresenter(viewModel);
//        PhasePortraitDataAccessInterface dataAccessInterface = new NewtonDataAccessObject();
//        PhasePortraitInteractor interactor = new PhasePortraitInteractor(dataAccessInterface, outputboundary);
//        List<List<Float>> unitvectors = interactor.createphasevectors(sys.getEquations(), sys.getVariables());
//        JFreeChart plot = interactor.createchart(unitvectors);
//        PhasePortraitState state = new PhasePortraitState(sys, plot, unitvectors, -1, 1, 1, -1, 1);
//        viewModel.setState(state);
//        PhasePortraitView view = new PhasePortraitView(viewModel, interactor);
//        view.setPhasePortraitController(new PhasePortraitController(interactor, outputboundary));

        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Phase Portrait");
        frame.setSize(WIDTH, HEIGHT);
        frame.add(view);

        frame.setVisible(true);
    }
}
