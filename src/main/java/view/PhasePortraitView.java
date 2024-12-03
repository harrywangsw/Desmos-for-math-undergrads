package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import interface_adapter.phaseportrait.PhasePortraitController;
import interface_adapter.phaseportrait.PhasePortraitState;
import interface_adapter.phaseportrait.PhasePortraitViewModel;
import use_case.PhasePortraitInteractor;
import use_case.equations.ApiAccessException;

/**
 * View for phase portrait.
 */
public class PhasePortraitView extends JPanel implements ActionListener, PropertyChangeListener {
    private static ChartPanel chartPanel;
    private final JButton decreasebutton = new JButton("decrease vector scale");
    private final JButton increasebutton = new JButton("increase vector scale");
    private final JTextField vectorscale = new JTextField("change vector scale");
    private final JButton vectorscalebutton = new JButton("confirm scale");
    private final JTextField leftb = new JTextField("change left bound");
    private final JTextField rb = new JTextField("change right bound");
    private final JTextField ub = new JTextField("change upper bound");
    private final JTextField lb = new JTextField("change lower bound");
    private final JButton confirm = new JButton("confirm");
    private final float vectorsizeincrement = 0.01f;
    private final PhasePortraitViewModel phasePortraitViewModel;
    private PhasePortraitController phasePortraitController;

    public PhasePortraitView(PhasePortraitViewModel phasePortraitViewModel, PhasePortraitInteractor interactor) {
        final JPanel scallingbuttons = new JPanel();
        final JPanel bounds = new JPanel();
        scallingbuttons.add(decreasebutton);
        scallingbuttons.add(increasebutton);
        scallingbuttons.add(vectorscalebutton);
        scallingbuttons.add(vectorscale);
        bounds.add(leftb);
        bounds.add(rb);
        bounds.add(ub);
        bounds.add(lb);
        bounds.add(confirm);
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new java.awt.BorderLayout());
        chartPanel = new ChartPanel(phasePortraitViewModel.getState().getplot());
        jPanel1.add(chartPanel, BorderLayout.CENTER);
        jPanel1.validate();

        this.add(scallingbuttons);
        this.add(bounds);
        this.add(chartPanel);

        JFreeChart plot = phasePortraitViewModel.getState().getplot();
        this.phasePortraitViewModel = phasePortraitViewModel;
        this.phasePortraitViewModel.addPropertyChangeListener(this);
        chartPanel.setChart(plot);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        decreasebutton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(decreasebutton)) {
                        phasePortraitController.changescale(phasePortraitViewModel.getState(),
                                phasePortraitViewModel.getState().getscale() - vectorsizeincrement);
                    }
                });

        increasebutton.addActionListener(evt -> {
                if (evt.getSource().equals(increasebutton)) {
                    phasePortraitController.changescale(phasePortraitViewModel.getState(),
                            phasePortraitViewModel.getState().getscale() + vectorsizeincrement);
                }
            }
        );

        confirm.addActionListener(evt -> {
            try {
                phasePortraitController.changeviewbox(phasePortraitViewModel.getState(),
                        Float.parseFloat(leftb.getText()), Float.parseFloat(rb.getText()),
                        Float.parseFloat(lb.getText()), Float.parseFloat(ub.getText()));
            }
            catch (ApiAccessException err) {
                throw new RuntimeException(err);
            }
        });
        vectorscalebutton.addActionListener(evt -> {
            if (evt.getSource().equals(vectorscalebutton)) {
                phasePortraitController.changescale(phasePortraitViewModel.getState(),
                        Float.parseFloat(vectorscale.getText()));
            }
        });
    }

    /**
     * Set the controller.
     * @param phasePortraitController the controller
     */
    public void setPhasePortraitController(PhasePortraitController phasePortraitController) {
        this.phasePortraitController = phasePortraitController;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final PhasePortraitState state = (PhasePortraitState) evt.getNewValue();
        setchart(state.getplot());
        setscale(state.getscale());
    }

    /**
     * Set the plot in the view.
     * @param plot Jfreechart, the plot
     */
    public void setchart(JFreeChart plot) {
        chartPanel.setChart(plot);
    }

    /**
     * Set the scale displayed in this view.
     * @param scale the scale
     */
    public void setscale(Float scale) {
        vectorscale.setText(scale.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
