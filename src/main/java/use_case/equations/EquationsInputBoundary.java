package use_case.equations;

import entity.ODESystem;

public interface EquationsInputBoundary {
    void executeSolve(ODESystem system);

    void extractCriticalPoints(ODESystem system);
}