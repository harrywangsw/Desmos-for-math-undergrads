package use_case;

import app.PhasePortraitAppBuilder;
import entity.ODESystem;
import use_case.main.GraphDataAccessInterface;
import use_case.main.MainInputBoundary;
import view.GraphView;

import java.util.Arrays;

public class MainInteractor implements MainInputBoundary {

    private final GraphDataAccessInterface graphDataAccessInterface;
    private final MainOutputBoundary mainOutputBoundary;

    public MainInteractor(GraphDataAccessInterface graphDataAccessInterface, MainOutputBoundary mainOutputBoundary) {
        this.graphDataAccessInterface = graphDataAccessInterface;
        this.mainOutputBoundary = mainOutputBoundary;
    }


    @Override
    public void executePlot(String[] equations) {
        final ODESystem system = new ODESystem(equations,
                Arrays.copyOfRange(ODESystem.VARIABLES, 0, equations.length));
        System.out.println("Temporary Plot");
        try {
            GraphView.plotGraph(system).setVisible(true);   //TODO
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void executePhasePotrait(String[] equations) throws Exception {
        final ODESystem system = new ODESystem(equations,
                Arrays.copyOfRange(ODESystem.VARIABLES, 0, equations.length));
        PhasePortraitAppBuilder.makePhase(system);
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
