package use_case.phase_portrait;

import data_access.NewtonDataAccessObject;
import entity.ODESystem;
import interface_adapter.phase_portrait.PhasePortraitPresenter;
import use_case.PhasePortraitInteractor;
import view.PhasePortraitView;

import javax.swing.*;
import java.util.List;

public class PhasePortraitAppBuilder {
    public static final int HEIGHT = 300;
    public static final int WIDTH = 400;

    public static void make_phase(ODESystem sys) throws Exception {
        PhasePortraitOutputBoundary outputboundary = new PhasePortraitPresenter();
        PhasePortraitDataAccessInterface dataAccessInterface = new NewtonDataAccessObject();
        PhasePortraitInteractor presenter = new PhasePortraitInteractor(dataAccessInterface, outputboundary);
        String[] vars = {"x", "y"};
        String[] exps = {"y+2x", "-2x"};
        PhasePortraitView view = new PhasePortraitView(exps, vars, new PhasePortraitInteractor());
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Phase Portrait");
        frame.setSize(WIDTH, HEIGHT);
        frame.add(view);

        frame.setVisible(true);
    }
}
