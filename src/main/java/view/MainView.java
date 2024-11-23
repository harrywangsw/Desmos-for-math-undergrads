package view;

import interface_adapter.main.MainController;
import interface_adapter.main.MainViewModel;

import javax.swing.*;
import java.util.List;

public class MainView extends JPanel {
    private final JButton runButton = new JButton("Run");
    private final JButton helpButton = new JButton("Help");
    private final JTextArea inputFunctionArea = new JTextArea();
    private final String[] menuItems = {"Plot", "Draw Phase Portrait", "Show Previous Graphs"};
    private final JComboBox<String> dropDownMenu = new JComboBox<>(menuItems);
    private MainController mainController;
//    private final MainViewModel mainViewModel;

    public MainView() {
//        this.mainViewModel = mainViewModel;
        final JPanel rightCornerButtons = new JPanel();
        rightCornerButtons.add(runButton);
        rightCornerButtons.add(helpButton);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        dropDownMenu.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        runButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(runButton)) {
                        mainController.execute( (String) dropDownMenu.getSelectedItem());
                    }
        });

        helpButton.addActionListener( evt -> {
            if (evt.getSource().equals(helpButton)) {
                mainController.execute("help");
            }
                }
        );

        this.add(rightCornerButtons);
        this.add(dropDownMenu);
    }

    public void setMainController(MainController controller) {this.mainController = controller;}
}
