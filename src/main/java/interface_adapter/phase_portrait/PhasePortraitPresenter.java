package interface_adapter.phase_portrait;

import org.jfree.chart.JFreeChart;
import use_case.phase_portrait.PhasePortraitOutputBoundary;
import view.PhasePortraitView;

import javax.swing.*;

public class PhasePortraitPresenter implements PhasePortraitOutputBoundary {
    final int WIDTH = 400;
    final int HEIGHT = 600;
    private final PhasePortraitViewModel phasePortraitViewModel;

    public PhasePortraitPresenter(PhasePortraitViewModel view) {
        this.phasePortraitViewModel = view;
    }

    @Override
    public void create_phaseportrait_view(JFreeChart plot) {
        PhasePortraitView view = new PhasePortraitView(plot, phasePortraitViewModel);
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Phase Portrait");
        frame.setSize(WIDTH, HEIGHT);
        frame.add(view);

        frame.setVisible(true);
    }

    public void change_chart(JFreeChart plot, float vector_scale) {
        phasePortraitViewModel.getState().setchart(plot);
        phasePortraitViewModel.getState().setscale(vector_scale);
        phasePortraitViewModel.firePropertyChanged();
    }
}