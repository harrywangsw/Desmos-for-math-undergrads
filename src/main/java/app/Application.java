package app;

import javax.swing.*;

public class Application {

    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addPhasePortraitView()
                .addPreviousGraphsView()
                .addMainView()
                .build();

        application.setVisible(true);
    }
}
