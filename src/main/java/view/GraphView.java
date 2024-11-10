package view;
import app.newton;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.chart.plot.PlotOrientation;

import javax.swing.*;
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
     * @param func 2d array of size 2, first elemnt stores the x values, 2nd stores the y values
     * @throws Exception
     */
    public static void plot(double[][] func) throws Exception {
        DefaultXYDataset dataset = new DefaultXYDataset();
        dataset.addSeries("test_func", func);
        JFreeChart chart = ChartFactory.createXYLineChart("plot", "t", "x", dataset, PlotOrientation.VERTICAL, false, false, false);
        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.setContentPane(chartPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
