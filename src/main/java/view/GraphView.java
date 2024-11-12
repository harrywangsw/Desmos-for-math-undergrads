package view;
import app.newton;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.chart.plot.PlotOrientation;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static javax.swing.SwingConstants.HORIZONTAL;

public class GraphView {
    public static void main(String[] args) throws Exception {
        List<List<Float>> full_sol = newton.euler(new String[]{"x^2"}, new String[]{"x"}, new Float[]{1f}, 0.8f);
        double[][] array = new double[2][full_sol.size()];

        for (int i = 0; i < full_sol.size(); i++) {
            array[1][i] = full_sol.get(i).get(0); // we're isolating one component from the vector [x(t), y(t), z(t), ...]
            array[0][i] = i*newton.interval;
        }
        plot(array);
    }

    /**
     * plots a 1d function
     * @param func 2d array of size 2, first element stores the x values, 2nd stores the y values
     * @throws Exception
     */
    public static void plot(double[][] func) throws Exception {
        DefaultXYDataset dataset = new DefaultXYDataset();
        dataset.addSeries("test_func", func);
        JFreeChart chart = ChartFactory.createXYLineChart("plot", "t", "x", dataset, PlotOrientation.VERTICAL, false, false, false);
        ChartPanel chartPanel = new ChartPanel(chart);
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        JTextField xMin = new JTextField(10);
        JTextField xMax= new JTextField(10);
        JTextField yMin = new JTextField(10);
        JTextField yMax = new JTextField(10);
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(e -> {
            String xMinValue = xMin.getText();
            String xMaxValue = xMax.getText();
            String yMinValue = yMin.getText();
            String yMaxValue = yMax.getText();

            // Parse the input ranges and update the chart axes
            updateAxisRange(chart, xMinValue, xMaxValue, yMinValue, yMaxValue);
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

        frame.setSize(1000, 600);
        frame.add(chartPanel, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
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

        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null,"Invalid input");
        }
    }
}
