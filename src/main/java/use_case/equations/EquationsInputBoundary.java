package use_case.equations;

import entity.ODESystem;

public interface EquationsInputBoundary {
    void executeSolve();

    void extractCriticalPoints(ODESystem system);
}
