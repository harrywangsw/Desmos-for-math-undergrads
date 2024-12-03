package interface_adapter.graph;

import org.jfree.chart.JFreeChart;
import use_case.graph.GraphOutputBoundary;

public class GraphPresenter implements GraphOutputBoundary {
    GraphViewModel viewModel;
    public GraphPresenter(GraphViewModel view){
        this.viewModel = view;
    }
    @Override
    public void changegraph(JFreeChart chart) {
        viewModel.getState().setChart(chart);
        viewModel.firePropertyChanged();
    }
}
