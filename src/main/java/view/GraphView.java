package view;

import java.awt.*;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultXYDataset;

import data_access.NewtonDataAccessObject;
import entity.OdeSystem;

public class GraphView {
    public static JFrame plotGraph(OdeSystem system) throws Exception {
        if (system.getVariables().length > 1) {
            System.out.println("only 1D systems have plotting support");
            return null;
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
        return plot(array);
    }
    /**
     * Plots a 1d function.
     * @param func 2d array of size 2, first element stores the x values, 2nd stores the y values
     * @throws Exception e
     */

    public static JFrame plot(double[][] func) throws Exception {
        final DefaultXYDataset dataset = new DefaultXYDataset();
        dataset.addSeries("test_func", func);
        final JFreeChart chart = ChartFactory.createXYLineChart("plot", "t", "x", dataset,
                PlotOrientation.VERTICAL, false, false, false);

        final File imageFile = new File("./graphs/" + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) + ".png");
        final int width = 800;
        final int height = 600;

        System.out.println("Chart saved as: " + imageFile.getAbsolutePath());
        final ChartPanel chartPanel = new ChartPanel(chart);
        final JPanel panel = new JPanel();
        final JFrame frame = new JFrame();
        final JTextField xMin = new JTextField(10);
        final JTextField xMax= new JTextField(10);
        final JTextField yMin = new JTextField(10);
        final JTextField yMax = new JTextField(10);
        final JButton updateButton = new JButton("Update");
        final JButton shiftUpButton = new JButton("Up");
        final JButton shiftDownButton = new JButton("Down");
        final JButton shiftLeftButton = new JButton("Left");
        final JButton shiftRightButton = new JButton("Right");

        updateButton.addActionListener(e -> {
            final String xMinValue = xMin.getText();
            final String xMaxValue = xMax.getText();
            final String yMinValue = yMin.getText();
            final String yMaxValue = yMax.getText();

            // Parse the input ranges and update the chart axes
            updateAxisRange(chart, xMinValue, xMaxValue, yMinValue, yMaxValue);
        });

        shiftUpButton.addActionListener(e -> {
            yUp(chart);
        });
        shiftDownButton.addActionListener(e -> {
            yDown(chart);
        });
        shiftLeftButton.addActionListener(e -> {
            xDown(chart);
        });
        shiftRightButton.addActionListener(e -> {
            xUp(chart);
        });

        panel.add(new JLabel("X min:"));
        panel.add(xMin);
        panel.add(new JLabel("X max:"));
        panel.add(xMax);
        panel.add(new JLabel("Y min:"));
        panel.add(yMin);
        panel.add(new JLabel("Y max:"));
        panel.add(yMax);
        panel.add(updateButton);
        panel.add(shiftUpButton);
        panel.add(shiftDownButton);
        panel.add(shiftLeftButton);
        panel.add(shiftRightButton);

        frame.setSize(1200, 800);
        frame.add(chartPanel, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        return frame;
    }

    private static void updateAxisRange(JFreeChart chart, String xMin, String xMax, String yMin, String yMax) {
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
    }

    private static void xUp(JFreeChart chart) {
        // Parse the input range for X and Y axes
        final XYPlot plot = chart.getXYPlot();
        final ValueAxis xAxis = plot.getDomainAxis();

        final double minX = xAxis.getLowerBound();
        final double maxX = xAxis.getUpperBound();

        xAxis.setRange(minX + 1, maxX + 1);

    }

    private static void xDown(JFreeChart chart) {
        // Parse the input range for X and Y axes
        final XYPlot plot = chart.getXYPlot();
        final ValueAxis xAxis = plot.getDomainAxis();

        final double minX = xAxis.getLowerBound();
        final double maxX = xAxis.getUpperBound();

        xAxis.setRange(minX - 1, maxX - 1);

    }

    private static void yUp(JFreeChart chart) {
        // Parse the input range for X and Y axes
        final XYPlot plot = chart.getXYPlot();
        final ValueAxis yAxis = plot.getRangeAxis();

        final double minY = yAxis.getLowerBound();
        final double maxY = yAxis.getUpperBound();

        yAxis.setRange(minY + 1, maxY + 1);
    }

    private static void yDown(JFreeChart chart) {
        // Parse the input range for X and Y axes
        final XYPlot plot = chart.getXYPlot();
        final ValueAxis yAxis = plot.getRangeAxis();

        final double minY = yAxis.getLowerBound();
        final double maxY = yAxis.getUpperBound();

        yAxis.setRange(minY - 1, maxY - 1);
    }

}
