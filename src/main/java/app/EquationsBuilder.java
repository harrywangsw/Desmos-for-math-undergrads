package app;

import data_access.EquationsDataAccessObject;
import interface_adapter.equations.EquationsController;
import interface_adapter.equations.EquationsPresenter;
import interface_adapter.equations.EquationsViewModel;
import interface_adapter.main.MainController;
import interface_adapter.main.MainViewModel;
import interface_adapter.note.NoteController;
import interface_adapter.note.NotePresenter;
import interface_adapter.note.NoteViewModel;
import use_case.EquationsInteractor;
import use_case.MainInteractor;
import use_case.NoteInteractor;
import use_case.NoteOutputBoundary;
import use_case.equations.EquationsDataAccessInterface;
import use_case.equations.EquationsOutputBoundary;
import use_case.note.NoteDataAccessInterface;
import view.EquationsView;
import view.MainView;
import view.NoteView;

import javax.swing.*;

/**
 * Builder for the Equations View
 */
public class EquationsBuilder {
    private EquationsDataAccessInterface equationsDAO;
    private EquationsViewModel equationsViewModel = new EquationsViewModel();
    private EquationsView equationsView;
    private EquationsInteractor equationsInteractor;

    /**
     * Sets the EquationsDAO to be used in this application.
     * @param equationsDataAccess the DAO to use
     * @return this builder
     */
    public EquationsBuilder addEquationsDAO(EquationsDataAccessInterface equationsDataAccess) {
        equationsDAO = equationsDataAccess;
        return this;
    }

    /**
     * Creates the objects for the Equations Use Case and connects the EquationsView to its
     * controller.
     * <p>This method must be called after addEquationsView!</p>
     * @return this builder
     * @throws RuntimeException if this method is called before addEquationsView
     */
    public EquationsBuilder addEquationsUseCase() {
        final EquationsOutputBoundary equationsOutputBoundary = new EquationsPresenter(equationsViewModel);
        equationsInteractor = new EquationsInteractor(
                equationsDAO, equationsOutputBoundary);

        final EquationsController controller = new EquationsController(equationsInteractor);
        if (equationsView == null) {
            throw new RuntimeException("addEquationsView must be called before addEquationsUseCase");
        }
        equationsView.setEquationsController(controller);
        return this;
    }

    /**
     * Creates the EquationsView and underlying EquationsViewModel.
     * @return this builder
     */
    public EquationsBuilder addEquationsView() {
        equationsViewModel = new EquationsViewModel();
        equationsView = new EquationsView(equationsViewModel);
        return this;
    }

    /**
     * Returns the built equations view
     * @return the equationsView
     */
    public EquationsView build() {
        return this.equationsView;
    }
}
