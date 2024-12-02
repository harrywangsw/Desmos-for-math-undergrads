package app;

import data_access.EquationsDataAccessObject;
import interface_adapter.main.MainController;
import interface_adapter.main.MainPresenter;
import interface_adapter.main.MainViewModel;
import interface_adapter.previous_graphs.PreviousGraphsPresenter;
import interface_adapter.previous_graphs.PreviousGraphsViewModel;
import use_case.MainInteractor;
import use_case.PreviousGraphsOutputBoundary;
import view.EquationsView;
import use_case.MainOutputBoundary;
import use_case.main.GraphDataAccessInterface;
import view.MainView;

import javax.swing.*;

/**
 * Builder for the Main Application.
 */
public class MainAppBuilder {
    public static final int HEIGHT = 500;
    public static final int WIDTH = 700;

    private MainViewModel mainViewModel = new MainViewModel();
    private PreviousGraphsViewModel previousGraphsViewModel = new PreviousGraphsViewModel();
    private MainInteractor mainInteractor;
    private MainView mainView;
    private GraphDataAccessInterface graphDAO;

    /**
     *
     * @param graphDataAccess
     * @return MainAppBuilder
     */
    public MainAppBuilder addGraphDAO(GraphDataAccessInterface graphDataAccess) {
        this.graphDAO = graphDataAccess;
        return this;
    }

    public MainAppBuilder addMainUseCase() {
        // Create main and previous graphs presenters
        final MainOutputBoundary mainOutputBoundary = new MainPresenter();
        final PreviousGraphsOutputBoundary previousGraphsOutputBoundary =
                new PreviousGraphsPresenter(previousGraphsViewModel);

        // Initialize the MainInteractor
        mainInteractor = new MainInteractor(graphDAO, mainOutputBoundary, previousGraphsOutputBoundary);

        // Set the MainController to MainView
        final MainController controller = new MainController(mainInteractor);
        if (mainView == null) {
            throw new RuntimeException("addMainView must be called before addMainUseCase");
        }
        mainView.setMainController(controller);
        return this;
    }

    public MainAppBuilder addMainView() {
        // Create the equations view
        EquationsBuilder equationsBuilder = new EquationsBuilder();
        EquationsDataAccessObject equationsDAO = new EquationsDataAccessObject();
        EquationsView equationsView = equationsBuilder.addEquationsDAO(equationsDAO)
                .addEquationsView().addEquationsUseCase().build();

//        PreviousGraphsView previousGraphsView = new PreviousGraphsView();
//        previousGraphsViewModel.addPropertyChangeListener(previousGraphsView);

        mainView = new MainView(equationsView, previousGraphsViewModel);

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
