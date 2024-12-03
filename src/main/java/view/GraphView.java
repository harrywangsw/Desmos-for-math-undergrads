package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.*;

import interface_adapter.graph.GraphController;
import interface_adapter.graph.GraphState;
import interface_adapter.graph.GraphViewModel;
import interface_adapter.phaseportrait.PhasePortraitController;
import interface_adapter.phaseportrait.PhasePortraitState;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultXYDataset;

import data_access.NewtonDataAccessObject;
import entity.OdeSystem;
import use_case.GraphInteractor;

public class GraphView extends JPanel implements ActionListener, PropertyChangeListener {
    private static GraphController graphcontroller;
    private ChartPanel chartPanel;

    public GraphView(GraphViewModel view) {
        JFreeChart chart = view.getState().getChart();
        chartPanel = new ChartPanel(chart);
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
            graphcontroller.change(chart, xMinValue, xMaxValue, yMinValue, yMaxValue);
        });

        shiftUpButton.addActionListener(e -> {
            graphcontroller.smallchange(chart, "yup");
        });
        shiftDownButton.addActionListener(e -> {
            graphcontroller.smallchange(chart, "ydown");
        });
        shiftLeftButton.addActionListener(e -> {
            graphcontroller.smallchange(chart, "xdown");
        });
        shiftRightButton.addActionListener(e -> {
            graphcontroller.smallchange(chart, "xup");
        });

        this.add(new JLabel("X min:"));
        this.add(xMin);
        this.add(new JLabel("X max:"));
        this.add(xMax);
        this.add(new JLabel("Y min:"));
        this.add(yMin);
        this.add(new JLabel("Y max:"));
        this.add(yMax);
        this.add(updateButton);
        this.add(shiftUpButton);
        this.add(shiftDownButton);
        this.add(shiftLeftButton);
        this.add(shiftRightButton);

        this.add(chartPanel, BorderLayout.CENTER);
    }


    public void setController(GraphController graphcontroller) {
        this.graphcontroller = graphcontroller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final GraphState state = (GraphState) evt.getNewValue();
        System.out.println("wtf");
        chartPanel.setChart(state.getChart());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
