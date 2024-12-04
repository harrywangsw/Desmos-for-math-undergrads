package app;

import data_access.EquationsDataAccessObject;
import data_access.NewtonDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.equations.EquationsViewModel;
import interface_adapter.graph.GraphState;
import interface_adapter.graph.GraphViewModel;
import interface_adapter.main.MainViewModel;
import interface_adapter.phaseportrait.PhasePortraitController;
import interface_adapter.phaseportrait.PhasePortraitPresenter;
import interface_adapter.phaseportrait.PhasePortraitState;
import interface_adapter.phaseportrait.PhasePortraitViewModel;
import interface_adapter.previous_graphs.PreviousGraphsViewModel;
import use_case.PhasePortraitInteractor;
import use_case.phaseportrait.PhasePortraitDataAccessInterface;
import use_case.phaseportrait.PhasePortraitOutputBoundary;
import view.*;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    private final JPanel appPanel = new JPanel();
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private final NewtonDataAccessObject newtonDataAccessObject = new NewtonDataAccessObject();

    private MainView mainView;
    private PhasePortraitInteractor phasePortraitInteractor;
    private MainViewModel mainViewModel;
    private PreviousGraphsView previousGraphsView;
    private PreviousGraphsViewModel previousGraphsViewModel;
    private PhasePortraitView phasePortraitView;
    private PhasePortraitViewModel phasePortraitViewModel;
    private GraphViewModel graphViewModel;
    private GraphView graphView;

    public AppBuilder(){
        cardPanel.setLayout(cardLayout);
        appPanel.setLayout(new BoxLayout(appPanel, BoxLayout.Y_AXIS));
    }

    public AppBuilder addMainView(){
        MainAppBuilder mainAppBuilder = new MainAppBuilder();
        mainView = mainAppBuilder.addMainView().addMainUseCase().build();
        appPanel.add(mainView, 0);
        return this;
    }

    public AppBuilder addPhasePortraitView(){
        phasePortraitViewModel = new PhasePortraitViewModel(new PhasePortraitState());
        phasePortraitView = new PhasePortraitView(phasePortraitViewModel);

        final PhasePortraitOutputBoundary outputboundary = new PhasePortraitPresenter(phasePortraitViewModel);
        final PhasePortraitDataAccessInterface dataAccessInterface = new NewtonDataAccessObject();
        phasePortraitInteractor = new PhasePortraitInteractor(dataAccessInterface, outputboundary);
        phasePortraitView.setPhasePortraitController(
                new PhasePortraitController(phasePortraitInteractor, outputboundary));

        cardPanel.add(phasePortraitView, phasePortraitView.getViewName());
        return this;
    }

//    public AppBuilder addGraphView(){
//        graphViewModel = new GraphViewModel(new GraphState());
//        graphView = new GraphView(graphViewModel);
//    }

    public AppBuilder addPreviousGraphsView(){
        PreviousGraphsBuilder previousGraphsBuilder = new PreviousGraphsBuilder();
        previousGraphsView = previousGraphsBuilder.addPreviousGraphsView()
                .addPreviousGraphsUseCase(viewManagerModel).buildForView();
        cardPanel.add(previousGraphsView, previousGraphsView.getViewName());
        return this;
    }

    public JFrame build() {
        final JFrame application = new JFrame("PhaseFlow");
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appPanel.add(cardPanel);
        application.add(appPanel);

        return application;
    }
}
