package interface_adapter.equations;

import use_case.equations.EquationsOutputBoundary;

/**
 * Presenter for the Equations use cases.
 */
public class EquationsPresenter implements EquationsOutputBoundary {

    private final EquationsViewModel equationsViewModel;

    public EquationsPresenter(EquationsViewModel equationsViewModel) {
        this.equationsViewModel = equationsViewModel;
    }

    @Override
    public void prepareCritPointsSuccessView(String[] criticalPoints) {
        equationsViewModel.getState().setCriticalPoints(criticalPoints);
        equationsViewModel.getState().setError(null);
        equationsViewModel.firePropertyChanged();
    }

    @Override
    public void prepareCritPointsFailureView(String error) {
        equationsViewModel.getState().setCriticalPoints(null);
        equationsViewModel.getState().setError(error);
        equationsViewModel.firePropertyChanged();
    }

    @Override
    public void prepareSolutionsSuccessView(String[] solutions) {
        equationsViewModel.getState().setSolutions(solutions);
        equationsViewModel.getState().setError(null);
        equationsViewModel.firePropertyChanged();
    }

    @Override
    public void prepareSolutionsFailureView(String error) {
        equationsViewModel.getState().setSolutions(null);
        equationsViewModel.getState().setError(error);
        equationsViewModel.firePropertyChanged();
    }
}
