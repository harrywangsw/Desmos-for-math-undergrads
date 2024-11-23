package app;

import entity.Graph;
import interface_adapter.main.MainController;
import interface_adapter.main.MainPresenter;
import interface_adapter.main.MainViewModel;
import interface_adapter.note.NoteController;
import use_case.MainInteractor;
import use_case.MainOutputBoundary;
import use_case.main.GraphDataAccessInterface;
import view.MainView;

import javax.swing.*;

/**
 * Builder for the Main Application
 */
public class MainAppBuilder {
    public static final int HEIGHT = 300;
    public static final int WIDTH = 400;
    //    private MainViewModel mainViewModel = new MainViewModel();
    private MainInteractor mainInteractor;
    private MainView mainView;
    private GraphDataAccessInterface graphDAO;
//    private MainInteractor mainInteractor;

    public MainAppBuilder addGraphDAO(GraphDataAccessInterface graphDataAccess){
        graphDAO = graphDataAccess;
        return this;
    }

    public MainAppBuilder addMainUseCase() {
        final MainOutputBoundary mainOutputBoundary = new MainPresenter();
        mainInteractor = new MainInteractor(graphDAO, mainOutputBoundary);
        final MainController controller = new MainController(mainInteractor);
        if (mainView == null) {
            throw new RuntimeException("addMainView must be called before addMainUseCase");
        }
        mainView.setMainController(controller);
        return this;
    }

    public MainAppBuilder addMainView() {
        mainView = new MainView();
        return this;
    }


    public JFrame build() {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Desmos for Math Undergrad");
        frame.setSize(WIDTH, HEIGHT);
        frame.add(mainView);

        return frame;
    }
}
