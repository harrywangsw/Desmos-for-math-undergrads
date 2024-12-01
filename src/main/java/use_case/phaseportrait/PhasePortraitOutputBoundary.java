package use_case.phaseportrait;

import org.jfree.chart.JFreeChart;

/**
 * A outputboundary.
 */
public interface PhasePortraitOutputBoundary {

    /**
     * Change the phase plot.
     * @param plot the plot
     * @param vector_scale scale
     */
    void changechart(JFreeChart plot, float vector_scale);
}
