package use_case.phase_portrait;

import phase_portrait.PhasePortraitInteractor;
import view.PhasePortraitView;

import javax.swing.*;

public class PhasePortraitAppBuilder {
    public static final int HEIGHT = 300;
    public static final int WIDTH = 400;

    public static void main(String[] args) throws Exception {
        PhasePortraitInteractor presenter = new PhasePortraitInteractor();
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
