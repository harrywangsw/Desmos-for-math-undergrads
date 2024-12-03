package app;

import data_access.EquationsDataAccessObject;
import data_access.NewtonDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.equations.EquationsViewModel;
import interface_adapter.graph.GraphState;
import interface_adapter.graph.GraphViewModel;
import interface_adapter.main.MainViewModel;
import interface_adapter.phaseportrait.PhasePortraitState;
import interface_adapter.phaseportrait.PhasePortraitViewModel;
import interface_adapter.previous_graphs.PreviousGraphsViewModel;
import view.*;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private final EquationsDataAccessObject equationsDataAccessObject = new EquationsDataAccessObject();
    private final NewtonDataAccessObject newtonDataAccessObject = new NewtonDataAccessObject();

    private MainView mainView;
    private MainViewModel mainViewModel;
    private PreviousGraphsView previousGraphsView;
    private PreviousGraphsViewModel previousGraphsViewModel;
    private EquationsView equationsView;
    private EquationsViewModel equationsViewModel;
    private PhasePortraitView phasePortraitView;
    private PhasePortraitViewModel phasePortraitViewModel;
    private GraphViewModel graphViewModel;
    private GraphView graphView;

    public AppBuilder(){cardPanel.setLayout(cardLayout);}

//    public EquationsView addEquationsView(){
//        equationsViewModel = new EquationsViewModel();
//        equationsView = new EquationsView(equationsViewModel);
//        return equationsView;
//    }

    public AppBuilder addMainView(){
        MainAppBuilder mainAppBuilder = new MainAppBuilder();
        mainView = mainAppBuilder.addMainView().addMainUseCase().build();
        cardPanel.add(mainView, mainView.getViewName());
        return this;
    }

    public AppBuilder addPhasePortraitView(){
        phasePortraitViewModel = new PhasePortraitViewModel(new PhasePortraitState());
        phasePortraitView = new PhasePortraitView(phasePortraitViewModel);

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
        application.add(cardPanel);
        viewManagerModel.setState(mainView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }


}
