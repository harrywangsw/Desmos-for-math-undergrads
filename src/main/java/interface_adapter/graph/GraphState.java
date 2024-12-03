package interface_adapter.graph;

import org.jfree.chart.JFreeChart;

import javax.swing.*;

public class GraphState {
    private JFreeChart chart;
    public GraphState() {}
    public JFreeChart getChart() {return chart;}
    public void setChart(JFreeChart chart) {this.chart = chart;}
}
