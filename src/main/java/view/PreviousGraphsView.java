package view;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.List;

import java.util.Map;

import javax.swing.*;

import interface_adapter.previous_graphs.PreviousGraphsController;
import interface_adapter.previous_graphs.PreviousGraphsViewModel;

public class PreviousGraphsView extends JPanel {
    private final PreviousGraphsViewModel viewModel;
    private PreviousGraphsController controller;

public class PreviousGraphsView extends JPanel implements PropertyChangeListener{
    private final PreviousGraphsViewModel previousGraphsViewModel;
    private final String viewName = "Previous Graphs";
    public PreviousGraphsView(PreviousGraphsViewModel previousGraphsViewModel) {
        this.previousGraphsViewModel = previousGraphsViewModel;
        this.previousGraphsViewModel.addPropertyChangeListener(this);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

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

                JLabel timestampLabel = new JLabel("Created: " + timestamp);
                timestampLabel.setHorizontalAlignment(SwingConstants.CENTER);

                graphPanel.add(imageLabel, BorderLayout.CENTER);
                graphPanel.add(timestampLabel, BorderLayout.SOUTH);

                add(graphPanel);
            }

            revalidate();
            repaint();
        } else{
            JLabel cautionLabel = new JLabel("No previous graphs found");
            add(cautionLabel);
        }


        }

    /**
     * Extracts the timestamp from the file path.
     * Assumes the timestamp is part of the file name in the format "YYYY-MM-DD_HH-MM-SS".
     */
    private String extractTimestampFromPath(String path) {
        File file = new File(path);
        String fileName = file.getName();
        int underscoreIndex = fileName.indexOf('_');
        if (underscoreIndex > 0) {
            String timestamp = fileName.substring(0, underscoreIndex).replace('_', ' ');
            return timestamp;
        } else {
            return "Unknown";
        }
    }
//
//    /**
//     * Handles property change events from the ViewModel.
//     */
//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        final PreviousGraphsState previousGraphsState = (PreviousGraphsState) evt.getNewValue();
//    }

    public String getViewName(){
        return viewName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final PreviousGraphsState previousGraphsState = (PreviousGraphsState) evt.getNewValue();
    }
}
