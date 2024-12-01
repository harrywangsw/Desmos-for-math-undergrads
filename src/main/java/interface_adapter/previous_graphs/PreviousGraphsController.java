package interface_adapter.previous_graphs;

import use_case.main.MainInputBoundary;
import use_case.previous_graphs.PreviousGraphsInputBoundary;

import java.util.Map;

public class PreviousGraphsController {

    private final MainInputBoundary mainInteractor;


    public PreviousGraphsController(MainInputBoundary mainInteractor) {
        this.mainInteractor = mainInteractor;
    }

    public void execute(Map<String, String> graph){
        mainInteractor.executePlot();
    }
}
