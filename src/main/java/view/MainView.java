package view;

import interface_adapter.main.MainController;

import javax.swing.*;

public class MainView extends JPanel {
    private final JButton runButton = new JButton("Run");
    private final JButton helpButton = new JButton("Help");
    private final JTextField inputFunctionArea = new JTextField(10);
    private final String[] menuItems = {"Plot", "Draw Phase Portrait", "Show Previous Graphs"};
    private final JComboBox<String> dropDownMenu = new JComboBox<>(menuItems);
    private MainController mainController;
//    private final MainViewModel mainViewModel;
    private EquationsView equationsView;

    public MainView(EquationsView equationsView) {
        this.equationsView = equationsView;
//        this.mainViewModel = mainViewModel;
        final JPanel rightCornerButtons = new JPanel();
        rightCornerButtons.add(runButton);
        rightCornerButtons.add(helpButton);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        dropDownMenu.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        runButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(runButton)) {
                        mainController.execute( (String) dropDownMenu.getSelectedItem(), equationsView.getequations());
                    }
                });

        helpButton.addActionListener( evt -> {
            if (evt.getSource().equals(helpButton)) {
                mainController.execute("help", new String[0]);
            }
                }
        );

        this.add(dropDownMenu);
        this.add(equationsView);
        this.add(rightCornerButtons);
    }

    public void setMainController(MainController controller) {this.mainController = controller;}
}