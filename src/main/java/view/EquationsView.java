package view;

import entity.ODESystem;
import interface_adapter.equations.EquationResultState;
import interface_adapter.equations.EquationsController;
import interface_adapter.equations.EquationsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class EquationsView extends JPanel implements ActionListener, PropertyChangeListener {

    private final EquationsViewModel equationsViewModel;

    private final ArrayList<String> equationList = new ArrayList<>();

    private final JPanel equationDisplay = new JPanel();

    private final JButton addEquationBtn = new JButton("Add Equation/Variable");
    private final JButton removeEquationBtn = new JButton("Remove Equation/Variable");
    private final JButton criticalPointsBtn = new JButton("Critical Points");
    private final JButton solveBtn = new JButton("Solve System");

    private final JPanel criticalPointsOutput = new JPanel();
    private final JLabel solutionOutput = new JLabel();

    private final JLabel criticalPointsLabel = new JLabel("Critical Points:");
    private final JLabel solutionLabel = new JLabel("Solution to System:");

    private EquationsController equationsController;

    public EquationsView(EquationsViewModel equationsViewModel) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.equationsViewModel = equationsViewModel;
        this.equationsViewModel.addPropertyChangeListener(this);

        final JLabel headingLabel = new JLabel("Differential Equations:");
        headingLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(headingLabel);

        // Button Panel Top
        final JPanel buttonPanelTop = new JPanel();

        // Add button
        addEquationBtn.addActionListener(e -> addNewEquation(ODESystem.VARIABLES[equationDisplay.getComponentCount()] + "' = "));
        buttonPanelTop.add(addEquationBtn);

        // Remove button
        removeEquationBtn.addActionListener(e -> removePreviousEquation());
        buttonPanelTop.add(removeEquationBtn);

        this.add(buttonPanelTop);

        // Add Equations
        equationDisplay.setLayout(new BoxLayout(equationDisplay, BoxLayout.Y_AXIS));

        // Scrollable view
        JScrollPane scrollPane = new JScrollPane(equationDisplay, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane);

        addNewEquation("x' = ");
        addNewEquation("y' = ");

        // Button Panel Bottom
        final JPanel buttonPanelBottom = new JPanel();

        criticalPointsBtn.addActionListener(e -> {
            ArrayList<String> equations = new ArrayList<>();
            for (Component equationPanel : equationDisplay.getComponents()) {
                if (equationPanel instanceof JPanel) {
                    equations.add(((JTextField) ((JPanel) equationPanel).getComponent(1)).getText());
                }
            }
            equationsController.execute("critpoints", equations.toArray(new String[0]));
        });
        buttonPanelBottom.add(criticalPointsBtn);

        solveBtn.addActionListener(e -> {});
        buttonPanelBottom.add(solveBtn);

        this.add(buttonPanelBottom);

        // Outputs
        criticalPointsLabel.setVisible(false);
        criticalPointsOutput.setVisible(false);
        criticalPointsOutput.setLayout(new BoxLayout(criticalPointsOutput, BoxLayout.Y_AXIS));
        solutionLabel.setVisible(false);
        solutionOutput.setVisible(false);
        this.add(criticalPointsLabel);
        this.add(criticalPointsOutput);
        this.add(solutionLabel);
        this.add(solutionOutput);
    }

    private void addNewEquation(String label) {
        JPanel newEquation = new JPanel(new FlowLayout(FlowLayout.LEFT));
        newEquation.add(new JLabel(label));
        newEquation.add(new JTextField("0", 20));
        equationDisplay.add(newEquation);
        this.revalidate();
    }

    private void removePreviousEquation() {
        equationDisplay.remove(equationDisplay.getComponentCount() - 1);
        this.revalidate();
    }

    public void setEquationsController(EquationsController controller) {
        this.equationsController = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final EquationResultState state = (EquationResultState) evt.getNewValue();
        setCriticalPoints(state);
        setSolution(state);
    }

    private void setCriticalPoints(EquationResultState state) {
        String[] criticalPoints = state.getCriticalPoints();
        if (criticalPoints != null) {
            criticalPointsOutput.removeAll();
            criticalPointsLabel.setVisible(true);
            criticalPointsOutput.setVisible(true);

            for (String criticalPoint : criticalPoints) {
                criticalPointsOutput.add(new JLabel(criticalPoint));
            }
        } else {
            criticalPointsLabel.setVisible(false);
            criticalPointsOutput.removeAll();
            criticalPointsOutput.setVisible(false);
        }
    }

    private void setSolution(EquationResultState state) {}
}
