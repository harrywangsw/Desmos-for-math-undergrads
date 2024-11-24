//import javax.swing.*;
//import java.awt.*;
//import java.util.List;
//
//public class ImageView {
////    private final FetchImagesController controller;
////    private final FetchImagesPresenter presenter;
////
////    public ImageGalleryView(FetchImagesController controller, FetchImagesPresenter presenter) {
////        this.controller = controller;
////        this.presenter = presenter;
////    }
////
////    public void display(String folderPath) {
////        controller.handleFetchImages(folderPath);
////        List<Image> images = presenter.getViewModel();
////
//        JFrame frame = new JFrame("Image Gallery");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(800, 600);
//
//        JPanel imagePanel = new JPanel();
//        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
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
//        frame.add(scrollPane);
//
//        frame.setVisible(true);
//    }
//}
