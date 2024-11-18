package view;

import interface_adapter.main.MainController;

import javax.swing.*;
import java.util.List;

public class MainView extends JPanel {
    private final JButton runButton = new JButton("Run");
    private final JButton helpButton = new JButton("Help");
    private final JTextArea inputFunctionArea = new JTextArea();
    private final String[] menuItems = {"Plot", "Draw Phase Portrait"};
    private final JComboBox<String> dropDownMenu = new JComboBox<>(menuItems);

    public MainView() {
        final JPanel rightCornerButtons = new JPanel();
        rightCornerButtons.add(runButton);
        rightCornerButtons.add(helpButton);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        dropDownMenu.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        runButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(runButton)) {

                    }
        });

        helpButton.addActionListener( evt -> {
            if (evt.getSource().equals(helpButton)) {
                MainController.execute("help")
            }
                }
        );
    }

    public static void main(String[] args) {

    }
}
