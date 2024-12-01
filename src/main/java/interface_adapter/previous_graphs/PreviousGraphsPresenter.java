package interface_adapter.previous_graphs;

import use_case.PreviousGraphsOutputBoundary;

import java.util.List;
import java.util.Map;

public class PreviousGraphsPresenter implements PreviousGraphsOutputBoundary {
    private final PreviousGraphsViewModel previousGraphsViewModel;

    public PreviousGraphsPresenter(PreviousGraphsViewModel previousGraphsViewModel) {
        this.previousGraphsViewModel = previousGraphsViewModel;
    }

    @Override
    public void displayGraphs(List<Map<String, String>> graphList) {
        previousGraphsViewModel.getState().setPreviousGraphs(graphList);
        previousGraphsViewModel.getState().setError(null);
        previousGraphsViewModel.firePropertyChanged();
    }

    @Override
    public void preparePreviousGraphFailView(String errorMessage) {
        previousGraphsViewModel.getState().setError(errorMessage);
        previousGraphsViewModel.firePropertyChanged();

    }
}
