package view;
import javax.swing.*;

public class MainView extends JPanel {
    private final JButton runButton = new JButton("Run");
    private final JButton helpButton = new JButton("Help");
    private final JTextArea inputFunctionArea = new JTextArea();

    public MainView() {
        final JPanel rightCornerButtons = new JPanel();
            rightCornerButtons.add(runButton);
            rightCornerButtons.add(helpButton);

    }
}
