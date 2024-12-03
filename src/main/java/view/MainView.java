package view;

import interface_adapter.main.MainController;
import use_case.note.DataAccessException;

import javax.swing.*;

public class MainView extends JPanel {
    private final String viewName = "Main";

    private final JButton runButton = new JButton("Run");
    private final JButton helpButton = new JButton("Help");
    private final JTextField inputFunctionArea = new JTextField(10);
    private final String[] menuItems = {"Plot", "Draw Phase Portrait"};
    private final JComboBox<String> dropDownMenu = new JComboBox<>(menuItems);
    private MainController mainController;
//    private final ViewModel<String> viewModel;
    private EquationsView equationsView;
    private PreviousGraphsView previousGraphsView;

    public MainView(EquationsView equationsView) {
        this.equationsView = equationsView;
//        this.viewModel = viewModel;
        this.previousGraphsView = new PreviousGraphsView();

        final JPanel rightCornerButtons = new JPanel();
        rightCornerButtons.add(runButton);
        rightCornerButtons.add(helpButton);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        dropDownMenu.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        runButton.addActionListener(evt -> {
            if (evt.getSource().equals(runButton)) {
                try {
                    mainController.execute((String) dropDownMenu.getSelectedItem(), equationsView.getequations());
                } catch (DataAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        helpButton.addActionListener(evt -> {
            if (evt.getSource().equals(helpButton)) {
                try {
                    mainController.execute("help", new String[0]);
                } catch (DataAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        this.add(dropDownMenu);
        this.add(equationsView);
        this.add(rightCornerButtons);
    }

    public void setMainController(MainController controller) {
        this.mainController = controller;
    }

    public String getViewName(){
        return viewName;
    }

}
