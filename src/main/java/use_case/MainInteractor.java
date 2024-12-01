package use_case;

import use_case.main.GraphDataAccessInterface;
import use_case.main.MainInputBoundary;
import view.GraphView;

public class MainInteractor implements MainInputBoundary {

    private final GraphDataAccessInterface graphDataAccessInterface;
    private final MainOutputBoundary mainOutputBoundary;

    public MainInteractor(GraphDataAccessInterface graphDataAccessInterface, MainOutputBoundary mainOutputBoundary) {
        this.graphDataAccessInterface = graphDataAccessInterface;
        this.mainOutputBoundary = mainOutputBoundary;
    }


    @Override
    public void executePlot() {
        System.out.println("Temporary Plot");
        try {
            GraphView.plotGraph().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void executePhasePotrait() {
        System.out.println("Temporary Draw Phase Portrait");
    }

    @Override
    public void executeHelp() {
        System.out.println("You just need to believe hard enough.");
    }

    @Override
    public void executePreviousGraphs() {
        System.out.println("This will eventually allow a new window to pop up that shows previously obtained graphs");

    }
}
