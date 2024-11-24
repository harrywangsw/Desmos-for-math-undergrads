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
    private JTextField vector_scale = new JTextField("change vector scale");
    private JButton vector_scale_button = new JButton("confirm scale");
    private JTextField leftb = new JTextField("change left bound");
    private JTextField rb = new JTextField("change right bound");
    private JTextField ub = new JTextField("change upper bound");
    private JTextField lb = new JTextField("change lower bound");
    private JButton confirm = new JButton("confirm");
    private PhasePortraitInteractor interactor;
    private ChartPanel CP;
    private List<List<Float>> unit_vectors = new ArrayList<>();
    private float vector_size = 0.1f;
    private float vector_size_increment = 0.01f;

    public PhasePortraitView(String[] exps, String[] vars, PhasePortraitInteractor interactor) throws Exception {
        this.interactor = interactor;
        unit_vectors = interactor.create_phase_vectors(exps, vars);
        final JPanel scalling_buttons = new JPanel();
        final JPanel bounds = new JPanel();
        scalling_buttons.add(decrease_button);
        scalling_buttons.add(increase_button);
        scalling_buttons.add(vector_scale_button);
        scalling_buttons.add(vector_scale);
        bounds.add(leftb);
        bounds.add(rb);
        bounds.add(ub);
        bounds.add(lb);
        bounds.add(confirm);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        decrease_button.addActionListener(
                evt -> {
                    if (evt.getSource().equals(decrease_button)) {
                        vector_size -= vector_size_increment;
                        vector_scale.setText(Float.toString(vector_size));
                        CP.setChart(interactor.change_scale(unit_vectors, vector_size));
                    }
                });

        increase_button.addActionListener( evt -> {
                    if (evt.getSource().equals(increase_button)) {
                        vector_size += vector_size_increment;
                        vector_scale.setText(Float.toString(vector_size));
                        CP.setChart(interactor.change_scale(unit_vectors, vector_size));
                    }
                }
        );

        confirm.addActionListener(evt -> {
            try {
                CP.setChart(interactor.change_bounds(exps, vars, Float.parseFloat(ub.getText()), Float.parseFloat(lb.getText()), Float.parseFloat(leftb.getText()), Float.parseFloat(rb.getText())));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        vector_scale_button.addActionListener(evt -> {
            if (evt.getSource().equals(vector_scale_button)) {
                CP.setChart(interactor.change_scale(unit_vectors, Float.parseFloat(vector_scale.getText())));
            }
        });

        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new java.awt.BorderLayout());
        JFreeChart chart = interactor.create_chart(unit_vectors);
        CP = new ChartPanel(chart);
        jPanel1.add(CP, BorderLayout.CENTER);
        jPanel1.validate();

        this.add(scalling_buttons);
        this.add(bounds);
        this.add(CP);
    }
}
