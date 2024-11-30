package interface_adapter.equations;

import java.util.Arrays;

import entity.ODESystem;
import use_case.equations.EquationsInputBoundary;

/**
 * Controller for the Equations use case that executes task based on user input.
 */
public class EquationsController {
    private final EquationsInputBoundary equationsInteractor;

    public EquationsController(EquationsInputBoundary equationsInteractor) {
        this.equationsInteractor = equationsInteractor;
    }

    /**
     * Executes the task requested by the user.
     * @param task The task to be executed
     * @param equations The equations input by the user
     */
    public void execute(String task, String[] equations) {
        final ODESystem system = new ODESystem(equations,
                Arrays.copyOfRange(ODESystem.VARIABLES, 0, equations.length));
        switch (task) {
            case "solve":
                equationsInteractor.executeSolve(system);
                break;

            case "critpoints":
                equationsInteractor.extractCriticalPoints(system);
                break;

            default:
                break;
        }
    }
}
