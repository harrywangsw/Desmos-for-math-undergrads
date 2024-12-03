package app;

import javax.swing.*;

public class Application {

    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addMainView()
                .addPhasePortraitView()
                .addPreviousGraphsView()
                .build();

        application.pack();
        application.setVisible(true);
    }
}