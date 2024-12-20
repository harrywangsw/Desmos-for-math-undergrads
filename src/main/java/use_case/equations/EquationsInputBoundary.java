package use_case.equations;

import entity.OdeSystem;

/**
 * The Input Boundary for the Equations use cases.
 */
public interface EquationsInputBoundary {
    /**
     * Executes the solve system use case.
     * @param system of equations to get solutions for.
     */
    void executeSolve(OdeSystem system);

    /**
     * Executes the extract critical points use case.
     * @param system of equations to extract critical points for.
     */
    void extractCriticalPoints(OdeSystem system);
}
