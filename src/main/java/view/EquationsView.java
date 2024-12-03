package view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import entity.OdeSystem;
import interface_adapter.equations.EquationResultState;
import interface_adapter.equations.EquationsController;
import interface_adapter.equations.EquationsViewModel;

/**
 * A sub-view of the MainView for the equations use case where the user can input equations
 * and view solutions/critical points.
 */
public class EquationsView extends JPanel implements ActionListener, PropertyChangeListener {

    private static final int TEXT_FIELD_WIDTH = 20;

    private final EquationsViewModel equationsViewModel;

    private final JPanel equationDisplay = new JPanel();

    private final JButton addEquationBtn = new JButton("Add Equation/Variable");
    private final JButton removeEquationBtn = new JButton("Remove Equation/Variable");
    private final JButton criticalPointsBtn = new JButton("Critical Points");
    private final JButton solveBtn = new JButton("Solve System");

    private final JPanel criticalPointsOutput = new JPanel();
    private final JPanel solutionOutput = new JPanel();

    private final JLabel criticalPointsLabel = new JLabel("Critical Points:");
    private final JLabel solutionLabel = new JLabel("Solution to System:");

    private final JLabel errorLabel = new JLabel("Error trying to extract critical points: ");

    private EquationsController equationsController;

    public EquationsView(EquationsViewModel equationsViewModel) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.equationsViewModel = equationsViewModel;
        this.equationsViewModel.addPropertyChangeListener(this);

        final JLabel headingLabel = new JLabel("Differential Equations:");
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(headingLabel);

        // Add Top Button Panel
        createTopButtonPanel();

        // Add Equations
        equationDisplay.setLayout(new BoxLayout(equationDisplay, BoxLayout.Y_AXIS));

        // Scrollable view
        final JScrollPane scrollPane = new JScrollPane(equationDisplay,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane);

        addNewEquation("x' = ");
        addNewEquation("y' = ");

        // Button Panel Bottom
        final JPanel buttonPanelBottom = new JPanel();

        criticalPointsBtn.addActionListener(event -> {
            final ArrayList<String> equations = new ArrayList<>();
            for (Component equationPanel : equationDisplay.getComponents()) {
                if (equationPanel instanceof JPanel) {
                    equations.add(((JTextField) ((JPanel) equationPanel).getComponent(1)).getText());
                }
            }
            equationsController.execute("critpoints", equations.toArray(new String[0]));
        });
        buttonPanelBottom.add(criticalPointsBtn);

        solveBtn.addActionListener(event -> {
            final ArrayList<String> equations = new ArrayList<>();
            for (Component equationPanel : equationDisplay.getComponents()) {
                if (equationPanel instanceof JPanel) {
                    equations.add(((JTextField) ((JPanel) equationPanel).getComponent(1)).getText());
                }
            }
            equationsController.execute("solve", equations.toArray(new String[0]));
        });
        buttonPanelBottom.add(solveBtn);

        this.add(buttonPanelBottom);

        // Outputs
        initializeOutputLabels();
    }

    private void initializeOutputLabels() {
        criticalPointsLabel.setVisible(false);
        criticalPointsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        criticalPointsOutput.setVisible(false);
        criticalPointsOutput.setLayout(new BoxLayout(criticalPointsOutput, BoxLayout.Y_AXIS));
        solutionLabel.setVisible(false);
        solutionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        solutionOutput.setVisible(false);
        solutionOutput.setLayout(new BoxLayout(solutionOutput, BoxLayout.Y_AXIS));
        this.add(criticalPointsLabel);
        this.add(criticalPointsOutput);
        this.add(solutionLabel);
        this.add(solutionOutput);

        errorLabel.setVisible(false);
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(errorLabel);
    }

    private void createTopButtonPanel() {
        // Button Panel Top
        final JPanel buttonPanelTop = new JPanel();

        // Add button
        addEquationBtn.addActionListener(event -> {
            addNewEquation(OdeSystem.VARIABLES[equationDisplay.getComponentCount()] + "' = ");
        });
        buttonPanelTop.add(addEquationBtn);

        // Remove button
        removeEquationBtn.addActionListener(event -> removePreviousEquation());
        buttonPanelTop.add(removeEquationBtn);

        this.add(buttonPanelTop);
    }

    private void addNewEquation(String label) {
        final JPanel newEquation = new JPanel(new FlowLayout(FlowLayout.LEFT));
        newEquation.add(new JLabel(label));
        newEquation.add(new JTextField("0", TEXT_FIELD_WIDTH));
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
        setError(state);
    }

    private void setCriticalPoints(EquationResultState state) {
        final ImageIcon[] criticalPoints = state.getCriticalPoints();
        if (criticalPoints != null) {
            criticalPointsOutput.removeAll();
            criticalPointsLabel.setVisible(true);
            criticalPointsOutput.setVisible(true);

            for (ImageIcon criticalPoint : criticalPoints) {
                final JLabel outputLabel = new JLabel(criticalPoint);
                outputLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                criticalPointsOutput.add(outputLabel);
            }
            this.revalidate();
        } else {
            criticalPointsLabel.setVisible(false);
            criticalPointsOutput.removeAll();
            criticalPointsOutput.setVisible(false);
            this.revalidate();
        }
    }

    private void setSolution(EquationResultState state) {
        final ImageIcon[] solutions = state.getSolutions();
        if (solutions != null) {
            solutionOutput.removeAll();
            solutionLabel.setVisible(true);
            solutionOutput.setVisible(true);

            for (ImageIcon solution : solutions) {
                final JLabel outputLabel = new JLabel(solution);
                outputLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                solutionOutput.add(outputLabel);
            }
            this.revalidate();
        } else {
            solutionLabel.setVisible(false);
            solutionOutput.removeAll();
            solutionOutput.setVisible(false);
            this.revalidate();
        }
    }

    private void setError(EquationResultState state) {
        final String error = state.getError();
        if (error != null) {
            errorLabel.setVisible(true);
            errorLabel.setText(error);
            this.revalidate();
        } else {
            errorLabel.setVisible(false);
            errorLabel.setText("");
            this.revalidate();
        }
    }

    public String[] getequations() {
        final ArrayList<String> equations = new ArrayList<>();
        for (Component equationPanel : equationDisplay.getComponents()) {
            if (equationPanel instanceof JPanel) {
                equations.add(((JTextField) ((JPanel) equationPanel).getComponent(1)).getText());
            }
        }
        return equations.toArray(new String[0]);
    }
}
