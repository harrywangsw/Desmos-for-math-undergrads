package interface_adapter.previous_graphs;

import use_case.main.MainInputBoundary;
import use_case.previous_graphs.PreviousGraphsInputBoundary;

import java.util.Arrays;
import java.util.Map;

public class PreviousGraphsController {

    private final MainInputBoundary mainInteractor;


    public PreviousGraphsController(MainInputBoundary mainInteractor) {
        this.mainInteractor = mainInteractor;
    }

    public void execute(Map<String, String> graph) {
        String equationMapped = graph.get("equation");
        if (equationMapped != null && !equationMapped.isEmpty()) {
            String[] equations = Arrays.stream(equationMapped.split(";"))
                    .map(String::trim)
                    .toArray(String[]::new);

            mainInteractor.executePlot(equations);
        } else {
            System.out.println("No equations provided!");
        }
    }
}
