package view;

import data_access.DBNoteDataAccessObject;
import data_access.EquationsDataAccessObject;
import interface_adapter.equations.EquationsController;
import interface_adapter.equations.EquationsPresenter;
import interface_adapter.equations.EquationsViewModel;
import interface_adapter.main.MainController;
import interface_adapter.main.MainViewModel;
import interface_adapter.note.NoteController;
import use_case.EquationsInteractor;
import use_case.NoteInteractor;
import use_case.equations.EquationsDataAccessInterface;
import use_case.equations.EquationsOutputBoundary;
import use_case.note.NoteDataAccessInterface;

import javax.swing.*;
import java.util.List;

public class MainView extends JPanel {
    private final JButton runButton = new JButton("Run");
    private final JButton helpButton = new JButton("Help");
    private final JTextField inputFunctionArea = new JTextField(10);
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

        this.add(dropDownMenu);
        this.add(buildEquationsView());
        this.add(rightCornerButtons);
    }

    private EquationsView buildEquationsView() {
        final EquationsDataAccessInterface equationsDAO = new EquationsDataAccessObject();
        final EquationsViewModel equationsViewModel = new EquationsViewModel();
        final EquationsView equationsView = new EquationsView(equationsViewModel);

        final EquationsOutputBoundary equationsOutputBoundary = new EquationsPresenter(equationsViewModel);
        final EquationsInteractor equationsInteractor = new EquationsInteractor(equationsDAO, equationsOutputBoundary);
        final EquationsController controller = new EquationsController(equationsInteractor);

        equationsView.setEquationsController(controller);

        return equationsView;
    }

    public void setMainController(MainController controller) {this.mainController = controller;}
}
