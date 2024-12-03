package use_case;

import app.GraphAppBuilder;
import app.PhasePortraitAppBuilder;
import data_access.NewtonDataAccessObject;
import entity.OdeSystem;
import interface_adapter.graph.GraphController;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultXYDataset;
import use_case.equations.ApiAccessException;
import use_case.graph.GraphInputBoundary;
import use_case.graph.GraphOutputBoundary;
import view.GraphView;

import javax.swing.*;
import java.util.List;

public class GraphInteractor implements GraphInputBoundary {
    GraphOutputBoundary graphOutputBoundary;
    public GraphInteractor(GraphOutputBoundary graphOutputBoundary) {
        this.graphOutputBoundary = graphOutputBoundary;
    }
    @Override
    public void makegraph(OdeSystem system) throws ApiAccessException {
        if (system.getVariables().length > 1) {
            System.out.println("only 1D systems have plotting support");
        }
        final NewtonDataAccessObject newtonDataAccessObject = new NewtonDataAccessObject();
        final List<List<Float>> fullSol = newtonDataAccessObject.eulersolve(system.getEquations(),
                system.getVariables(),
                system.getInitialConditions(), 8f);
        final double[][] array = new double[2][fullSol.size()];

        for (int i = 0; i < fullSol.size(); i++) {
            array[1][i] = fullSol.get(i).get(0);
            array[0][i] = i * NewtonDataAccessObject.INTERVAL;
        }
        JFreeChart chart = plot(array);
        GraphView view = new GraphView(chart);
        view.setController(new GraphController(this));
        graphOutputBoundary.changegraph(chart);
        GraphAppBuilder.makeView(view);
    }

    @Override
    public void updateAxisRange(JFreeChart chart, String xMin, String xMax, String yMin, String yMax) {
        // Parse the input range for X and Y axes
        try {
            final double xMinValue = Double.parseDouble(xMin);
            final double xMaxValue = Double.parseDouble(xMax);
            final double yMinValue = Double.parseDouble(yMin);
            final double yMaxValue = Double.parseDouble(yMax);

            // Get the plot from the chart
            final XYPlot plot = chart.getXYPlot();

            // Update the X and Y axis range
            final ValueAxis xAxis = plot.getDomainAxis();
            final ValueAxis yAxis = plot.getRangeAxis();

            xAxis.setRange(xMinValue, xMaxValue);
            yAxis.setRange(yMinValue, yMaxValue);

        }
        catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null, "Invalid input");
        }
        graphOutputBoundary.changegraph(chart);
    }

    @Override
    public void yUp(JFreeChart chart) {
        // Parse the input range for X and Y axes
        final XYPlot plot = chart.getXYPlot();
        final ValueAxis yAxis = plot.getRangeAxis();

        final double minY = yAxis.getLowerBound();
        final double maxY = yAxis.getUpperBound();

        yAxis.setRange(minY + 1, maxY + 1);
        graphOutputBoundary.changegraph(chart);
    }

    @Override
    public void yDown(JFreeChart chart) {
        // Parse the input range for X and Y axes
        final XYPlot plot = chart.getXYPlot();
        final ValueAxis yAxis = plot.getRangeAxis();

        final double minY = yAxis.getLowerBound();
        final double maxY = yAxis.getUpperBound();

        yAxis.setRange(minY - 1, maxY - 1);
        graphOutputBoundary.changegraph(chart);
    }

    @Override
    public void xUp(JFreeChart chart) {
        // Parse the input range for X and Y axes
        final XYPlot plot = chart.getXYPlot();
        final ValueAxis xAxis = plot.getDomainAxis();

        final double minX = xAxis.getLowerBound();
        final double maxX = xAxis.getUpperBound();

        xAxis.setRange(minX + 1, maxX + 1);
        graphOutputBoundary.changegraph(chart);
    }

    @Override
    public void xDown(JFreeChart chart) {
        // Parse the input range for X and Y axes
        final XYPlot plot = chart.getXYPlot();
        final ValueAxis xAxis = plot.getDomainAxis();

        final double minX = xAxis.getLowerBound();
        final double maxX = xAxis.getUpperBound();

        xAxis.setRange(minX - 1, maxX - 1);
        graphOutputBoundary.changegraph(chart);
    }

    public JFreeChart plot(double[][] func) {
        final DefaultXYDataset dataset = new DefaultXYDataset();
        dataset.addSeries("test_func", func);
        final JFreeChart chart = ChartFactory.createXYLineChart("plot", "t", "x", dataset,
                PlotOrientation.VERTICAL, false, false, false);
        return chart;
    }
}
