package interface_adapter.main;

import use_case.main.MainInputBoundary;
import use_case.note.DataAccessException;

public class MainController {

    private final MainInputBoundary mainInteractor;

    public MainController(MainInputBoundary mainInteractor) {
        this.mainInteractor = mainInteractor;
    }

    /**
     * Execute all the main tasks from the main use cases.
     * @param task the task to be executed
     * @param equations the equations
     * @throws DataAccessException e
     */

    public void execute(String task, String[] equations) throws DataAccessException {
        switch (task) {
            case "Plot":
                mainInteractor.executePlot(equations);
                break;

            case "Draw Phase Portrait":
                try {
                    mainInteractor.executePhasePotrait(equations);
                }
                catch (Exception e) {
                    System.out.println("Can't plot phase portrait");
                }
                break;

            case "Show Previous Graphs":
                mainInteractor.executePreviousGraphs();
                break;

            case "help":
                mainInteractor.executeHelp();
                break;
        }
    }
}
