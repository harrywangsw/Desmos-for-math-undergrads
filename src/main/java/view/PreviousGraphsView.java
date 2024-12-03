package view;

import interface_adapter.previous_graphs.PreviousGraphsState;
import interface_adapter.previous_graphs.PreviousGraphsViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.List;

public class PreviousGraphsView extends JPanel implements PropertyChangeListener{
    private final PreviousGraphsViewModel previousGraphsViewModel;
    private final String viewName = "Previous Graphs";
    public PreviousGraphsView(PreviousGraphsViewModel previousGraphsViewModel) {
        this.previousGraphsViewModel = previousGraphsViewModel;
        this.previousGraphsViewModel.addPropertyChangeListener(this);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        removeAll();

        final PreviousGraphsState previousGraphsState = previousGraphsViewModel.getState();
        List<String> imagePaths = previousGraphsState.getPreviousGraphs();
        if (imagePaths != null) {
            for (String imagePath : imagePaths) {
                JPanel graphPanel = new JPanel(new BorderLayout());
                graphPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                String timestamp = extractTimestampFromPath(imagePath);

                JLabel imageLabel;
                try {
                    ImageIcon imageIcon = new ImageIcon(imagePath);
                    imageLabel = new JLabel(imageIcon);
                } catch (Exception e) {
                    imageLabel = new JLabel("Image not found: " + imagePath);
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
