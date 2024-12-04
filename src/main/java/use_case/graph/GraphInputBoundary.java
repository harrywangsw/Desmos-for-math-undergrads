package use_case.graph;

import entity.OdeSystem;
import interface_adapter.graph.GraphViewModel;
import org.jfree.chart.JFreeChart;
import use_case.equations.ApiAccessException;

public interface GraphInputBoundary {
    void makegraph(OdeSystem system, GraphViewModel viewModel) throws ApiAccessException;

    void updateAxisRange(JFreeChart chart, String xMin, String xMax, String yMin, String yMax);

    void yUp(JFreeChart chart);

    void yDown(JFreeChart chart);

    void xUp(JFreeChart chart);

    void xDown(JFreeChart chart);
}
