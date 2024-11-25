package interface_adapter.equations;

import entity.ODESystem;
import use_case.equations.EquationsInputBoundary;

public class EquationsController {
    private final EquationsInputBoundary equationsInteractor;

    public EquationsController(EquationsInputBoundary equationsInteractor) {
        this.equationsInteractor = equationsInteractor;
    }

    public void execute(String task, String[] equations) {
        switch (task) {

            case "solve":
                equationsInteractor.executeSolve();
                break;

            case "critpoints":
                ODESystem system = new ODESystem(equations, new String[0]);
                equationsInteractor.extractCriticalPoints(system);
                break;
        }
    }


}
