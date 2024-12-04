package interface_adapter.previous_graphs;

import interface_adapter.ViewManagerModel;
import use_case.PreviousGraphsOutputBoundary;
import view.ViewManager;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Map;

public class PreviousGraphsPresenter implements PreviousGraphsOutputBoundary {
    private final PreviousGraphsViewModel previousGraphsViewModel;
    private final ViewManagerModel viewManagerModel;

    public PreviousGraphsPresenter(PreviousGraphsViewModel previousGraphsViewModel, ViewManagerModel viewManagerModel) {
        this.previousGraphsViewModel = previousGraphsViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void displayGraphs(List<String> graphList) {
        previousGraphsViewModel.getState().setPreviousGraphs(graphList);
        previousGraphsViewModel.getState().setError(null);
        previousGraphsViewModel.firePropertyChanged();

        viewManagerModel.setState(previousGraphsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
        System.out.println(viewManagerModel.getViewName());
//        viewManager.navigate(viewManagerModel.getViewName());
        System.out.println("Everything till here runs");

    }

    @Override
    public void preparePreviousGraphFailView(String errorMessage) {
        previousGraphsViewModel.getState().setError(errorMessage);
        previousGraphsViewModel.firePropertyChanged();

    }
}
