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
        ODESystem system = new ODESystem(equations, Arrays.copyOfRange(ODESystem.VARIABLES, 0, equations.length));
        switch (task) {

            case "solve":
                equationsInteractor.executeSolve(system);
                break;

            case "critpoints":
                equationsInteractor.extractCriticalPoints(system);
                break;
        }
    }


}