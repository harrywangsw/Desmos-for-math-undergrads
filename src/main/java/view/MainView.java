package view;

import javax.swing.*;

import interface_adapter.main.MainController;
import use_case.note.DataAccessException;

public class MainView extends JPanel {

    private final JButton runButton = new JButton("Run");
    private final JButton helpButton = new JButton("Help");
    private final JTextField inputFunctionArea = new JTextField(10);
    private final String[] menuItems = {"Home", "Plot", "Draw Phase Portrait", "Show Previous Graphs"};
    private final JComboBox<String> dropDownMenu = new JComboBox<>(menuItems);
    private MainController mainController;
    private EquationsView equationsView;

    public MainView(EquationsView equationsView) {
        this.equationsView = equationsView;
        final JPanel rightCornerButtons = new JPanel();
        rightCornerButtons.add(runButton);
        rightCornerButtons.add(helpButton);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        dropDownMenu.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        runButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(runButton)) {
                        try {
                            mainController.execute((String) dropDownMenu.getSelectedItem(), equationsView.getEquations());
                        }
                        catch (DataAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

        helpButton.addActionListener( evt -> {
            if (evt.getSource().equals(helpButton)) {
                try {
                    mainController.execute("help", new String[0]);
                } catch (DataAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        this.add(dropDownMenu);
        this.add(rightCornerButtons);
        this.add(equationsView);
    }

    public void setMainController(MainController controller) {
        this.mainController = controller;
    }
}
