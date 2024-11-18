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
               System.out.println("Temporary Plot");
               break;
           case "Draw Phase Portrait":
               System.out.println("Temporary Draw Phase Portrait");

           case "help":
               System.out.println("You just need to believe hard enough.");
       }
    }
}
