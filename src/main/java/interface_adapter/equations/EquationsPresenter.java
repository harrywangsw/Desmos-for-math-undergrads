package interface_adapter.equations;

import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

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
        try {
            final ImageIcon[] critPointImages = new ImageIcon[criticalPoints.length];
            for (int i = 0; i < criticalPoints.length; i++) {
                critPointImages[i] = new ImageIcon(ImageIO.read(new URL(criticalPoints[i])));
            }
            equationsViewModel.getState().setCriticalPoints(critPointImages);
            equationsViewModel.getState().setError(null);
            equationsViewModel.firePropertyChanged();
        }
        catch (IOException exception) {
            prepareCritPointsFailureView("There was an error trying to find critical points!");
        }
    }

    @Override
    public void prepareCritPointsFailureView(String error) {
        equationsViewModel.getState().setCriticalPoints(null);
        equationsViewModel.getState().setError(error);
        equationsViewModel.firePropertyChanged();
    }

    @Override
    public void prepareSolutionsSuccessView(String[] solutions) {
        try {
            final ImageIcon[] solutuionImages = new ImageIcon[solutions.length];
            for (int i = 0; i < solutions.length; i++) {
                solutuionImages[i] = new ImageIcon(ImageIO.read(new URL(solutions[i])));
            }
            equationsViewModel.getState().setSolutions(solutuionImages);
            equationsViewModel.getState().setError(null);
            equationsViewModel.firePropertyChanged();
        }
        catch (IOException exception) {
            prepareSolutionsFailureView("There was an error trying to get solutions!");
        }
    }

    @Override
    public void prepareSolutionsFailureView(String error) {
        equationsViewModel.getState().setSolutions(null);
        equationsViewModel.getState().setError(error);
        equationsViewModel.firePropertyChanged();
    }
}
