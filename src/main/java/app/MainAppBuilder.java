package app;

import data_access.EquationsDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.main.MainController;
import interface_adapter.main.MainPresenter;
import interface_adapter.previous_graphs.PreviousGraphsPresenter;
import interface_adapter.previous_graphs.PreviousGraphsViewModel;
import use_case.MainInteractor;
import use_case.MainOutputBoundary;
import use_case.PreviousGraphsOutputBoundary;
import use_case.main.GraphDataAccessInterface;
import view.EquationsView;
import view.MainView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

/**
 * Builder for the Main Application.
 */

public class MainAppBuilder {
    public static final int HEIGHT = 500;
    public static final int WIDTH = 700;
    //    private MainViewModel mainViewModel = new MainViewModel();
    private MainInteractor mainInteractor;
    private PreviousGraphsViewModel previousGraphsViewModel = new PreviousGraphsViewModel();
    private MainView mainView;
    private GraphDataAccessInterface graphDao;
    private ViewManagerModel viewManagerModel = new ViewManagerModel();
    //    private MainInteractor mainInteractor;

    /**
     * Builder for the Main Application.
     * @param graphDataAccess w
     * @return this
     */
    public MainAppBuilder addGraphDAO(GraphDataAccessInterface graphDataAccess) {
        graphDao = graphDataAccess;
        return this;
    }

    /**
     * Builder for the Main Application.
     * @return this
     * @throws RuntimeException e
     */

    public MainAppBuilder addMainUseCase() {
        final MainOutputBoundary mainOutputBoundary = new MainPresenter();
        final PreviousGraphsOutputBoundary previousGraphsOutputBoundary =
                new PreviousGraphsPresenter(previousGraphsViewModel, viewManagerModel);
        mainInteractor = new MainInteractor(graphDao, mainOutputBoundary, previousGraphsOutputBoundary);
        final MainController controller = new MainController(mainInteractor);
        if (mainView == null) {
            throw new RuntimeException("addMainView must be called before addMainUseCase");
        }
        mainView.setMainController(controller);
        return this;
    }
    /**
     * Builder for the Main Application.
     * @return this
     */

    public MainAppBuilder addMainView() {
        final EquationsBuilder equationsBuilder = new EquationsBuilder();
        final EquationsDataAccessObject equationsDao = new EquationsDataAccessObject();
        final EquationsView equationsView = equationsBuilder.addequationsdao(equationsDao)
                .addEquationsView().addEquationsUseCase().build();
        mainView = new MainView(equationsView);
        return this;
    }

    /**
     * Builder for the Main Application.
     * @return frame
     */
    public MainView build() {
//        final JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setTitle("Desmos for Math Undergrad");
//        frame.setSize(WIDTH, HEIGHT);
//        frame.add(mainView);
//
//        return frame;
        return this.mainView;
    }
}
