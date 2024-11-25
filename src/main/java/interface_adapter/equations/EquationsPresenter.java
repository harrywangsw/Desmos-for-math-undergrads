package interface_adapter.equations;

import interface_adapter.note.NoteViewModel;
import use_case.equations.EquationsOutputBoundary;

import javax.swing.*;

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

    @Override
    public void prepareSolutionsSuccessView(String[] solutions) {
        equationsViewModel.getState().setSolutions(solutions);
        equationsViewModel.firePropertyChanged();
    }

    @Override
    public void prepareSolutionsFailureView() {
        equationsViewModel.getState().setSolutions(null);
        equationsViewModel.firePropertyChanged();
    }


}