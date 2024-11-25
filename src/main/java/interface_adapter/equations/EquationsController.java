package interface_adapter.equations;

import entity.ODESystem;
import use_case.equations.EquationsInputBoundary;

import java.util.Arrays;

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
                ODESystem system = new ODESystem(equations, Arrays.copyOfRange(ODESystem.VARIABLES, 0, equations.length));
                equationsInteractor.extractCriticalPoints(system);
                break;
        }
    }


}
