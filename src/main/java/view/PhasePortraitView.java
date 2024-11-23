package view;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import phase_portrait.PhasePortraitInteractor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PhasePortraitView extends JPanel {
    private final JButton decrease_button = new JButton("decrease vector scale");
    private final JButton increase_button = new JButton("increase vector scale");
    private PhasePortraitInteractor interactor;
    private ChartPanel CP;
    private List<List<Float>> unit_vectors = new ArrayList<>();
    private float vector_size = 0.1f;
    private float vector_size_increment = 0.1f;

    public PhasePortraitView(String[] exps, String[] vars, PhasePortraitInteractor interactor) throws Exception {
        this.interactor = interactor;
        unit_vectors = interactor.create_phase_vectors(exps, vars);
        final JPanel rightCornerButtons = new JPanel();
        rightCornerButtons.add(decrease_button);
        rightCornerButtons.add(increase_button);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        decrease_button.addActionListener(
                evt -> {
                    if (evt.getSource().equals(decrease_button)) {
                        vector_size -= vector_size_increment;
                        CP.setChart(interactor.change_scale(unit_vectors, vector_size));
                    }
                });

        increase_button.addActionListener( evt -> {
                    if (evt.getSource().equals(increase_button)) {
                        vector_size += vector_size_increment;
                        CP.setChart(interactor.change_scale(unit_vectors, vector_size));
                    }
                }
        );

        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new java.awt.BorderLayout());
        JFreeChart chart = interactor.plot_vectors(unit_vectors);
        CP = new ChartPanel(chart);
        jPanel1.add(CP, BorderLayout.CENTER);
        jPanel1.validate();

        this.add(rightCornerButtons);
        this.add(CP);
    }
}
