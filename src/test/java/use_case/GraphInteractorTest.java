package use_case;

import entity.OdeSystem;
import interface_adapter.graph.GraphState;
import interface_adapter.graph.GraphViewModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisState;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.*;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.ui.RectangleEdge;
import org.junit.Test;
import use_case.equations.ApiAccessException;
import use_case.equations.EquationsDataAccessInterface;
import use_case.equations.EquationsOutputBoundary;
import use_case.graph.GraphOutputBoundary;
import use_case.phaseportrait.PhasePortraitDataAccessInterface;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GraphInteractorTest {
    @Test
    public void testmakegraph() throws ApiAccessException {
        GraphOutputBoundary graphOutputBoundary = new GraphOutputBoundary() {
            @Override
            public void changegraph(JFreeChart chart) {
                assertEquals(chart.getPlot().getPlotType(), "XY Plot");
            }
        };
        GraphInteractor interactor = new GraphInteractor(graphOutputBoundary);
        String[] testEquations = {"x"};
        String[] testVariables = {"x"};
        Float[] tessIc = {1F};
        OdeSystem system = new OdeSystem(testEquations, testVariables);
        system.setInitialConditions(tessIc);
        interactor.makegraph(system, new GraphViewModel(new GraphState()));
    }

    @Test
    public void testupdateaxisrange() throws ApiAccessException {
        GraphOutputBoundary graphOutputBoundary = new GraphOutputBoundary() {

            @Override
            public void changegraph(JFreeChart chart) {
                assertEquals(chart.getXYPlot().getDomainAxis().getUpperBound(), 1.0, 0.001);
                assertEquals(chart.getXYPlot().getRangeAxis().getUpperBound(), 1.0, 0.001);
                assertEquals(chart.getXYPlot().getDomainAxis().getLowerBound(), 0.0, 0.001);
                assertEquals(chart.getXYPlot().getRangeAxis().getLowerBound(), 0.0, 0.001);
            }
        };
        GraphInteractor interactor = new GraphInteractor(graphOutputBoundary);
        JFreeChart chart = ChartFactory.createXYLineChart("", "", "", null, PlotOrientation.HORIZONTAL, false, false, false);
        interactor.updateAxisRange(chart, "0.0", "1.0", "0.0", "1.0");
    }

    @Test
    public void testxup() throws ApiAccessException {
        GraphOutputBoundary graphOutputBoundary = new GraphOutputBoundary() {

            @Override
            public void changegraph(JFreeChart chart) {
                assertEquals(chart.getXYPlot().getDomainAxis().getUpperBound(), 2.05, 0.001);
                assertEquals(chart.getXYPlot().getDomainAxis().getLowerBound(), 1.0, 0.001);
            }
        };
        GraphInteractor interactor = new GraphInteractor(graphOutputBoundary);
        JFreeChart chart = ChartFactory.createXYLineChart("", "", "", null, PlotOrientation.HORIZONTAL, false, false, false);
        interactor.xUp(chart);
    }

    @Test
    public void testyup() throws ApiAccessException {
        GraphOutputBoundary graphOutputBoundary = new GraphOutputBoundary() {

            @Override
            public void changegraph(JFreeChart chart) {
                assertEquals(chart.getXYPlot().getRangeAxis().getUpperBound(), 2.05, 0.001);
                assertEquals(chart.getXYPlot().getRangeAxis().getLowerBound(), 1.0, 0.001);
            }
        };
        GraphInteractor interactor = new GraphInteractor(graphOutputBoundary);
        JFreeChart chart = ChartFactory.createXYLineChart("", "", "", null, PlotOrientation.HORIZONTAL, false, false, false);
        interactor.yUp(chart);
    }

    @Test
    public void testxdown() throws ApiAccessException {
        GraphOutputBoundary graphOutputBoundary = new GraphOutputBoundary() {

            @Override
            public void changegraph(JFreeChart chart) {
                assertEquals(chart.getXYPlot().getDomainAxis().getUpperBound(), 0.05, 0.001);
                assertEquals(chart.getXYPlot().getDomainAxis().getLowerBound(), -1.0, 0.001);
            }
        };
        GraphInteractor interactor = new GraphInteractor(graphOutputBoundary);
        JFreeChart chart = ChartFactory.createXYLineChart("", "", "", null, PlotOrientation.HORIZONTAL, false, false, false);
        interactor.xDown(chart);
    }

    @Test
    public void testydown() throws ApiAccessException {
        GraphOutputBoundary graphOutputBoundary = new GraphOutputBoundary() {

            @Override
            public void changegraph(JFreeChart chart) {
                assertEquals(chart.getXYPlot().getRangeAxis().getUpperBound(), 0.05, 0.001);
                assertEquals(chart.getXYPlot().getRangeAxis().getLowerBound(), -1.0, 0.001);
            }
        };
        GraphInteractor interactor = new GraphInteractor(graphOutputBoundary);
        JFreeChart chart = ChartFactory.createXYLineChart("", "", "", null, PlotOrientation.HORIZONTAL, false, false, false);
        interactor.yDown(chart);
    }
}
