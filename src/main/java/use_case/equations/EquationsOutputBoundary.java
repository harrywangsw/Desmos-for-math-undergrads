package use_case.equations;

/**
 * Output Boundary for the Equations use cases.
 */
public interface EquationsOutputBoundary {
    /**
     * Prepares the success view for the critical points use case.
     * @param criticalPoints the critical points extracted from the equations
     */
    void prepareCritPointsSuccessView(String[] criticalPoints);

    /**
     * Prepares the failure view for the critical points use case.
     * @param error the error causing the request to fail.
     */
    void prepareCritPointsFailureView(String error);

    /**
     * Prepares the success view for the solutions use case.
     * @param solutions calculated from the given equations.
     */
    void prepareSolutionsSuccessView(String[] solutions);

    /**
     * Prepares the failure view for the solutions use case.
     * @param error the error causing the request to fail.
     */
    void prepareSolutionsFailureView(String error);
}
