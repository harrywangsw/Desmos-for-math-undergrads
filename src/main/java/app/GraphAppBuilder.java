package app;

import view.GraphView;
import view.PhasePortraitView;

import javax.swing.*;

public class GraphAppBuilder {
    public static final int HEIGHT = 600;
    public static final int WIDTH = 800;

    /**
     * Builder function for phase portrait use case.
     * @param view the Jframe containing the phase portrait's view
     * @throws Exception when the api call returns error
     */
    public static void makeView(GraphView view) {

        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Phase Portrait");
        frame.setSize(WIDTH, HEIGHT);
        frame.add(view);

        frame.setVisible(true);
    }
}
