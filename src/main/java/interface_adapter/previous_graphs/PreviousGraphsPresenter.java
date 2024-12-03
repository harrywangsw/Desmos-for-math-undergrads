package interface_adapter.previous_graphs;

import interface_adapter.ViewManagerModel;
import use_case.PreviousGraphsOutputBoundary;

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
    }

    @Override
    public void preparePreviousGraphFailView(String errorMessage) {
        previousGraphsViewModel.getState().setError(errorMessage);
        previousGraphsViewModel.firePropertyChanged();

    }
}
