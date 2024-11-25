package interface_adapter.equations;

import interface_adapter.note.NoteViewModel;
import use_case.equations.EquationsOutputBoundary;

public class EquationsPresenter implements EquationsOutputBoundary {

    private final EquationsViewModel equationsViewModel;

    public EquationsPresenter(EquationsViewModel equationsViewModel) {
        this.equationsViewModel = equationsViewModel;
    }

    @Override
    public void prepareCritPointsSuccessView(String[] criticalPoints) {
        equationsViewModel.getState().setCriticalPoints(criticalPoints);
        equationsViewModel.firePropertyChanged();
    }

    @Override
    public void prepareCritPointsFailureView() {
        equationsViewModel.getState().setCriticalPoints(null);
        equationsViewModel.firePropertyChanged();
    }
}
