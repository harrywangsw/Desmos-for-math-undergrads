package use_case.phase_portrait;

import org.jfree.chart.JFreeChart;

import java.util.List;

public interface PhasePortraitInputBoundary {
    List<List<Float>> create_phase_vectors(String[] expression, String[] variable) throws Exception;

    JFreeChart create_chart(List<List<Float>> vectors);

    JFreeChart change_scale(List<List<Float>> vectors, float vector_size);

    JFreeChart change_bounds(String[] expression, String[] variable, float ub, float lb, float leftb, float rb) throws Exception;
}
