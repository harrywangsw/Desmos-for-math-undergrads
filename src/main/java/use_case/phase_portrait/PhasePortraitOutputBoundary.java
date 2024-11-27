package use_case.phase_portrait;

import org.jfree.chart.JFreeChart;

public interface PhasePortraitOutputBoundary {
    void create_phaseportrait_view(JFreeChart plot) throws Exception;

    void change_chart(JFreeChart plot, float vector_scale);
}
