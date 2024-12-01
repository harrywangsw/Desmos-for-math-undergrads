//package view;
//import interface_adapter.fetch_images.FetchImagesController;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class FetchImagesView extends JPanel {
//    private FetchImagesController fetchImagesController;
//
//    public FetchImagesView() {
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//
//        for (Image image : images) {
//            JLabel imageLabel = new JLabel(new ImageIcon(image.getPath()));
//            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//            imagePanel.add(imageLabel);
//            imagePanel.add(Box.createRigidArea(new Dimension(0, 10)));
//        }
//
//        JScrollPane scrollPane = new JScrollPane(imagePanel);
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//
////        frame.add(scrollPane);
////
////        frame.setVisible(true);
//    }
//}
