package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EquationsView extends JPanel {
    private final JPanel equationList = new JPanel();
    private final JButton addEquationBtn = new JButton("Add Equation/Variable");
    private final JButton removeEquationBtn = new JButton("Remove Equation/Variable");

    private final String[] VARIABLES = {"x", "y", "z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w"};

    public EquationsView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final JLabel headingLabel = new JLabel("Differential Equations:");
        headingLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(headingLabel);

        equationList.setLayout(new BoxLayout(equationList, BoxLayout.Y_AXIS));

        // Scrollable view
        JScrollPane scrollPane = new JScrollPane(equationList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane);

        addNewEquation("x' = ");
        addNewEquation("y' = ");

        // Button Panel
        final JPanel buttonPanel = new JPanel();

        // Add button
        addEquationBtn.addActionListener(e -> addNewEquation(VARIABLES[equationList.getComponentCount()] + "' = "));
        buttonPanel.add(addEquationBtn);

        // Remove button
        removeEquationBtn.addActionListener(e -> removePreviousEquation());
        buttonPanel.add(removeEquationBtn);

        this.add(buttonPanel);
    }

    private void addNewEquation(String label) {
        JPanel newEquation = new JPanel(new FlowLayout(FlowLayout.LEFT));
        newEquation.add(new JLabel(label));
        newEquation.add(new JTextField("0", 20));
        equationList.add(newEquation);
        this.revalidate();
    }

    private void removePreviousEquation() {
        equationList.remove(equationList.getComponentCount() - 1);
        this.revalidate();
    }
}
