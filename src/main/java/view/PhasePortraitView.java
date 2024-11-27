package view;

import interface_adapter.phase_portrait.PhasePortraitController;
import interface_adapter.phase_portrait.PhasePortraitState;
import interface_adapter.phase_portrait.PhasePortraitViewModel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import use_case.PhasePortraitInteractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class PhasePortraitView extends JPanel implements ActionListener, PropertyChangeListener {
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
    private static ChartPanel CP;
    private List<List<Float>> unit_vectors = new ArrayList<>();
    private float vector_size_increment = 0.01f;
    private PhasePortraitViewModel phasePortraitViewModel;
    private PhasePortraitController phasePortraitController;

    public PhasePortraitView(PhasePortraitViewModel phasePortraitViewModel, PhasePortraitInteractor interactor) {
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
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new java.awt.BorderLayout());
        CP = new ChartPanel(phasePortraitViewModel.getState().getplot());
        jPanel1.add(CP, BorderLayout.CENTER);
        jPanel1.validate();

        this.add(scalling_buttons);
        this.add(bounds);
        this.add(CP);

        JFreeChart plot = phasePortraitViewModel.getState().getplot();
        this.phasePortraitViewModel = phasePortraitViewModel;
        this.phasePortraitViewModel.addPropertyChangeListener(this);
        CP.setChart(plot);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        decrease_button.addActionListener(
                evt -> {
                    if (evt.getSource().equals(decrease_button)) {
                        phasePortraitController.change_scale(phasePortraitViewModel.getState(), phasePortraitViewModel.getState().getscale()-vector_size_increment);
                    }
                });

        increase_button.addActionListener( evt -> {
                    if (evt.getSource().equals(increase_button)) {
                        phasePortraitController.change_scale(phasePortraitViewModel.getState(), phasePortraitViewModel.getState().getscale()+vector_size_increment);
                    }
                }
        );

        confirm.addActionListener(evt -> {
            try {
                phasePortraitController.change_viewbox(phasePortraitViewModel.getState(), Float.parseFloat(leftb.getText()), Float.parseFloat(rb.getText()), Float.parseFloat(lb.getText()), Float.parseFloat(ub.getText()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        vector_scale_button.addActionListener(evt -> {
            if (evt.getSource().equals(vector_scale_button)) {
                phasePortraitController.change_scale(phasePortraitViewModel.getState(), Float.parseFloat(vector_scale.getText()));
            }
        });
    }

    public void setPhasePortraitController(PhasePortraitController phasePortraitController) {
        this.phasePortraitController = phasePortraitController;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final PhasePortraitState state = (PhasePortraitState) evt.getNewValue();
        setchart(state.getplot());
        setscale(state.getscale());
    }

    public void setchart(JFreeChart plot){
        CP.setChart(plot);
    }

    public void setscale(Float scale){
        vector_scale.setText(scale.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
