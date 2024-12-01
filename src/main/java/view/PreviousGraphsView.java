package view;


import interface_adapter.previous_graphs.PreviousGraphsController;
import interface_adapter.previous_graphs.PreviousGraphsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

public class PreviousGraphsView extends JPanel {
    private final PreviousGraphsViewModel viewModel;
    private PreviousGraphsController controller;

    public PreviousGraphsView(PreviousGraphsViewModel viewModel) {
        this.viewModel = viewModel;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    /**
     * Sets the controller for this view.
     */
    public void setController(PreviousGraphsController controller) {
        this.controller = controller;
    }

    /**
     * Updates the view with the given list of graphs.
     */
    public void displayGraphs(List<Map<String, String>> graphList) {
        removeAll(); // Clear previous graphs

        for (Map<String, String> graph : graphList) {
            String equation = graph.get("equation");
            String imagePath = graph.get("path_to_image");

            // Create a panel for each graph
            JPanel graphPanel = new JPanel(new BorderLayout());
            graphPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Image
            JLabel imageLabel;
            try {
                ImageIcon imageIcon = new ImageIcon(imagePath);
                imageLabel = new JLabel(imageIcon);
            } catch (Exception e) {
                imageLabel = new JLabel("Image not found: " + imagePath);
            }

            // Equation
            JLabel equationLabel = new JLabel(equation);
            equationLabel.setHorizontalAlignment(SwingConstants.CENTER);

            // Add components to graph panel
            graphPanel.add(imageLabel, BorderLayout.CENTER);
            graphPanel.add(equationLabel, BorderLayout.SOUTH);

            // Add a mouse listener to handle clicks
            graphPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (controller != null) {
                        controller.execute(graph);
                    }
                }
            });

            add(graphPanel);
        }

        revalidate();
        repaint();
    }
}
