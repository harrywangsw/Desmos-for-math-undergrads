package interface_adapter.phase_portrait;
import data_access.NewtonDataAccessObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jfree.data.xy.VectorSeries;
import org.jfree.data.xy.VectorSeriesCollection;
import org.jfree.chart.renderer.xy.VectorRenderer;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFrame;
import use_case.phase_portrait.PhasePortraitOutputBoundary;
import view.PhasePortraitView;

public class PhasePortraitPresenter implements PhasePortraitOutputBoundary {
    private final PhasePortraitViewModel view;

    public PhasePortraitPresenter(PhasePortraitViewModel view) {
        this.view = view;
    }

}