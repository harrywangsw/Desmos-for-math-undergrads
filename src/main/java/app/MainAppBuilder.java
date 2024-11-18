package app;

import interface_adapter.main.MainViewModel;
import use_case.MainInteractor;
import view.MainView;

import javax.swing.*;

/**
 * Builder for the Main Application
 */
public class MainAppBuilder {
    public static final int HEIGHT = 300;
    public static final int WIDTH = 400;
    private MainViewModel mainViewModel = new MainViewModel();
    private MainView mainView;
    private MainInteractor mainInteractor;

    public JFrame build() {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Desmos for Math Undergrad");
        frame.setSize(WIDTH, HEIGHT);

        frame.add(mainView);

        return frame;
    }
}
