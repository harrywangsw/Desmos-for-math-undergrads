package view;

import interface_adapter.ViewModel;
import interface_adapter.main.MainController;
import use_case.note.DataAccessException;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainView extends JPanel {
    private final JButton runButton = new JButton("Run");
    private final JButton helpButton = new JButton("Help");
    private final JTextField inputFunctionArea = new JTextField(10);
    private final String[] menuItems = {"Plot", "Draw Phase Portrait"};
    private final JComboBox<String> dropDownMenu = new JComboBox<>(menuItems);
    private MainController mainController;
    private final ViewModel<String> viewModel;
    private EquationsView equationsView;
    private PreviousGraphsView previousGraphsView;

    public MainView(EquationsView equationsView, ViewModel viewModel) {
        this.equationsView = equationsView;
        this.viewModel = viewModel;
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

        // Add listener to ViewModel
        this.viewModel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("state".equals(evt.getPropertyName()) && "Show Previous Graphs".equals(evt.getNewValue())) {
                    displayPreviousGraphs();
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

    /**
     * Displays the PreviousGraphsView JPanel when triggered.
     */
    private void displayPreviousGraphs() {
        removeAll(); // Clear existing components
        add(previousGraphsView); // Add the PreviousGraphsView JPanel
        revalidate(); // Re-layout the components
        repaint(); // Repaint the panel
    }
}
