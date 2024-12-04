package view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import interface_adapter.previous_graphs.PreviousGraphsController;
import interface_adapter.previous_graphs.PreviousGraphsViewModel;

public class PreviousGraphsView extends JPanel {
    private final PreviousGraphsViewModel viewModel;
    private PreviousGraphsController controller;

    public PreviousGraphsView(PreviousGraphsViewModel viewModel) {
        this.viewModel = viewModel;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    /**
     * Sets the controller for this view.
     * @param controller controller
     */
    public void setController(PreviousGraphsController controller) {
        this.controller = controller;
    }

    /**
     * Updates the view with the given list of graphs.
     * @param graphList graph list
     */
    public void displayGraphs(List<Map<String, String>> graphList) {
        removeAll();
        // Clear previous graphs

        for (Map<String, String> graph : graphList) {
            final String equation = graph.get("equation");
            final String imagePath = graph.get("path_to_image");

            // Create a panel for each graph
            final JPanel graphPanel = new JPanel(new BorderLayout());
            final int border = 10;
            graphPanel.setBorder(BorderFactory.createEmptyBorder(border, border, border, border));

            // Image
            JLabel imageLabel;
            try {
                final ImageIcon imageIcon = new ImageIcon(imagePath);
                imageLabel = new JLabel(imageIcon);
            }
            catch (Exception e) {
                imageLabel = new JLabel("Image not found: " + imagePath);
            }

            // Equation
            final JLabel equationLabel = new JLabel(equation);
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
