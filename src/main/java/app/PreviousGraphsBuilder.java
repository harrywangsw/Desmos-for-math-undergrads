package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.previous_graphs.PreviousGraphsController;
import interface_adapter.previous_graphs.PreviousGraphsPresenter;
import interface_adapter.previous_graphs.PreviousGraphsViewModel;
import use_case.PreviousGraphsInteractor;
import use_case.PreviousGraphsOutputBoundary;
import view.PhasePortraitView;
import view.PreviousGraphsView;
import view.ViewManager;

import javax.swing.*;

public class PreviousGraphsBuilder {
    private PreviousGraphsViewModel previousGraphsViewModel = new PreviousGraphsViewModel();
    private PreviousGraphsView previousGraphsView;
    private PreviousGraphsInteractor previousGraphsInteractor;

    public PreviousGraphsBuilder addPreviousGraphsUseCase(ViewManagerModel viewManagerModel){
        final PreviousGraphsOutputBoundary previousGraphsOutputBoundary = new PreviousGraphsPresenter
                (previousGraphsViewModel, viewManagerModel);
        previousGraphsInteractor = new PreviousGraphsInteractor(previousGraphsOutputBoundary);
        if (previousGraphsView == null){
            throw new RuntimeException("addPreviousGraphsView must be called first");
        }
        return this;
    }

    public PreviousGraphsBuilder addPreviousGraphsView() {
        previousGraphsViewModel = new PreviousGraphsViewModel();
        previousGraphsView = new PreviousGraphsView(previousGraphsViewModel);
        return this;
    }

    public PreviousGraphsView buildForView() {
        return this.previousGraphsView;
    }

}
