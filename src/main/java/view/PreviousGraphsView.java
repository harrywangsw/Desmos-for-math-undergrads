package view;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.List;

public class PreviousGraphsView extends JPanel implements PropertyChangeListener {
    public PreviousGraphsView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    /**
     * Updates the view with the given list of image file paths.
     */
    public void displayGraphs(List<String> imagePaths) {
        removeAll();

        for (String imagePath : imagePaths) {
            JPanel graphPanel = new JPanel(new BorderLayout());
            graphPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Extract the timestamp from the file name
            String timestamp = extractTimestampFromPath(imagePath);

            // Image
            JLabel imageLabel;
            try {
                ImageIcon imageIcon = new ImageIcon(imagePath);
                imageLabel = new JLabel(imageIcon);
            } catch (Exception e) {
                imageLabel = new JLabel("Image not found: " + imagePath);
            }

            // Timestamp label
            JLabel timestampLabel = new JLabel("Created: " + timestamp);
            timestampLabel.setHorizontalAlignment(SwingConstants.CENTER);

            // Add components to graph panel
            graphPanel.add(imageLabel, BorderLayout.CENTER);
            graphPanel.add(timestampLabel, BorderLayout.SOUTH);

            add(graphPanel);
        }

        revalidate();
        repaint();
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

    /**
     * Handles property change events from the ViewModel.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName()) && evt.getNewValue() instanceof List) {
            @SuppressWarnings("unchecked")
            List<String> imagePaths = (List<String>) evt.getNewValue();
            displayGraphs(imagePaths);
        }
    }
}
