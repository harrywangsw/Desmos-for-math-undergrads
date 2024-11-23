package interface_adapter.main;


import use_case.main.MainInputBoundary;

public class MainController {

    private final MainInputBoundary mainInteractor;

    public MainController(MainInputBoundary mainInteractor) {
        this.mainInteractor = mainInteractor;
    }

    /**
     * Execute all the main tasks from the main use cases
     * @param task the task to be executed
     */
    public void execute(String task) {
       switch (task) {
           case "Plot":
               mainInteractor.executePlot();
               break;
           case "Draw Phase Portrait":
               mainInteractor.executePhasePotrait();
               break;

           case "Show Previous Graphs":
               mainInteractor.executePreviousGraphs();

           case "help":
               mainInteractor.executeHelp();
               break;
       }
    }
}
