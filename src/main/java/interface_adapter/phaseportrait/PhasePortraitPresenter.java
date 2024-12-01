package interface_adapter.phaseportrait;

import java.util.List;

import org.jfree.chart.JFreeChart;

import use_case.phaseportrait.PhasePortraitOutputBoundary;

/**
 * A presenter.
 */
public class PhasePortraitPresenter implements PhasePortraitOutputBoundary {
    private final PhasePortraitViewModel phasePortraitViewModel;

    public PhasePortraitPresenter(PhasePortraitViewModel view) {
        this.phasePortraitViewModel = view;
    }

    /**
     * Change the phase plot.
     * @param plot the new plot
     * @param vector_scale scale
     */
    public void changechart(JFreeChart plot, float vector_scale, List<List<Float>> vectors) {
        phasePortraitViewModel.getState().setchart(plot);
        phasePortraitViewModel.getState().setscale(vector_scale);
        phasePortraitViewModel.getState().setUnitvectors(vectors);
        phasePortraitViewModel.firePropertyChanged();
    }
}
