package interface_adapter.equations;

import use_case.equations.EquationsInputBoundary;

public class EquationsController {
    private final EquationsInputBoundary equationsInteractor;

    public EquationsController(EquationsInputBoundary equationsInteractor) {
        this.equationsInteractor = equationsInteractor;
    }

    public void execute(String task) {
        switch (task) {

            case "solve":
                equationsInteractor.executeSolve();
                break;

            case "critpoints":
                equationsInteractor.extractCriticalPoints();
                break;
        }
    }


}
