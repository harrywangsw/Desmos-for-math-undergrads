package view;

import data_access.NewtonDataAccessObject;

import entity.ODESystem;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.chart.plot.PlotOrientation;

/**
 * Class used to display the GraphView in Java.
 */
public class GraphView {
    public static JFrame plotGraph(ODESystem system) throws Exception {
        if (system.getVariables().length > 1){
            System.out.println("only 1D systems have plotting support");
            return null;
        }
        NewtonDataAccessObject newtonDataAccessObject = new NewtonDataAccessObject();
        List<List<Float>> full_sol = newtonDataAccessObject.eulersolve(system.getEquations(), system.getVariables(), system.getInitialConditions(), 8f);
        final double[][] array = new double[2][full_sol.size()];

        for (int i = 0; i < full_sol.size(); i++) {
            array[1][i] = full_sol.get(i).get(0); // we're isolating one component from the vector [x(t), y(t), z(t), ...]
            array[0][i] = i* NewtonDataAccessObject.INTERVAL;
        }
        return plot(array);
    }

    /**
     * plots a 1d function
     * @param func 2d array of size 2, first element stores the x values, 2nd stores the y values
     * @throws Exception
     * @return JFrame that helps out with
     */
    public static JFrame plot(double[][] func) throws Exception {
        DefaultXYDataset dataset = new DefaultXYDataset();
        dataset.addSeries("test_func", func);
        JFreeChart chart = ChartFactory.createXYLineChart("plot", "t", "x", dataset, PlotOrientation.VERTICAL, false, false, false);

        File imageFile = new File("./graphs/" + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) + ".png");
        final int width = 800;
        final int height = 600;
        ChartUtilities.saveChartAsPNG(imageFile, chart, width, height);

    System.out.println("Chart saved as: " + imageFile.getAbsolutePath());
        ChartPanel chartPanel = new ChartPanel(chart);
        final JPanel panel = new JPanel();
        final JFrame frame = new JFrame();
        final JTextField xMin = new JTextField(10);
        final JTextField yMin = new JTextField(10);
        final JTextField xMax= new JTextField(10);
        final JTextField yMax = new JTextField(10);
        final JButton updateButton = new JButton("Update");
        final JButton shiftUpButton = new JButton("Up");
        final JButton shiftDownButton = new JButton("Down");
        final JButton shiftLeftButton = new JButton("Left");
        final JButton shiftRightButton = new JButton("Right");

        updateButton.addActionListener(e -> {
            String xMinValue = xMin.getText();
            String xMaxValue = xMax.getText();
            String yMinValue = yMin.getText();
            String yMaxValue = yMax.getText();

            // Parse the input ranges and update the chart axes
            updateAxisRange(chart, xMinValue, xMaxValue, yMinValue, yMaxValue);
        });

        shiftUpButton.addActionListener(e -> {
            YUp(chart);
        });
        shiftDownButton.addActionListener(e -> {
            YDown(chart);
        });
        shiftLeftButton.addActionListener(e -> {
            XDown(chart);
        });
        shiftRightButton.addActionListener(e -> {
            XUp(chart);
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
            double xMinValue = Double.parseDouble(xMin);
            double xMaxValue = Double.parseDouble(xMax);
            double yMinValue = Double.parseDouble(yMin);
            double yMaxValue = Double.parseDouble(yMax);

            // Get the plot from the chart
            XYPlot plot = chart.getXYPlot();

            // Update the X and Y axis range
            ValueAxis xAxis = plot.getDomainAxis();
            ValueAxis yAxis = plot.getRangeAxis();

            xAxis.setRange(xMinValue, xMaxValue);
            yAxis.setRange(yMinValue, yMaxValue);

        }
        catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null,"Invalid input");
        }
    }

    private static void XUp(JFreeChart chart) {
        // Parse the input range for X and Y axes
        XYPlot plot = chart.getXYPlot();
        ValueAxis xAxis = plot.getDomainAxis();

        double minX = xAxis.getLowerBound();
        double maxX = xAxis.getUpperBound();

        xAxis.setRange(minX + 1, maxX + 1);

    }

    private static void XDown(JFreeChart chart) {
        // Parse the input range for X and Y axes
        XYPlot plot = chart.getXYPlot();
        ValueAxis xAxis = plot.getDomainAxis();

        final double minX = xAxis.getLowerBound();
        final double maxX = xAxis.getUpperBound();

        xAxis.setRange(minX - 1, maxX - 1);
    }

    private static void YUp(JFreeChart chart) {
        // Parse the input range for X and Y axes
        XYPlot plot = chart.getXYPlot();
        ValueAxis yAxis = plot.getRangeAxis();

        final double minY = yAxis.getLowerBound();
        final double maxY = yAxis.getUpperBound();

        yAxis.setRange(minY + 1, maxY + 1);
    }


    private static void YDown(JFreeChart chart) {
        // Parse the input range for X and Y axes
        XYPlot plot = chart.getXYPlot();
        ValueAxis yAxis = plot.getRangeAxis();

        final double minY = yAxis.getLowerBound();
        final double maxY = yAxis.getUpperBound();

        yAxis.setRange(minY - 1, maxY - 1);
    }

}
