package use_case;

import entity.ODESystem;
import use_case.equations.APIAccessException;
import use_case.equations.EquationsDataAccessInterface;
import use_case.equations.EquationsInputBoundary;
import use_case.equations.EquationsOutputBoundary;

/**
 * Equations Interactor for the use cases of extracting critical points and
 * solving the differential equations analytically.
 */
public class EquationsInteractor implements EquationsInputBoundary {

    private final EquationsDataAccessInterface equationsDataAccessInterface;
    private final EquationsOutputBoundary equationsOutputBoundary;

    public EquationsInteractor(EquationsDataAccessInterface equationsDataAccessInterface,
                               EquationsOutputBoundary equationsOutputBoundary) {
        this.equationsDataAccessInterface = equationsDataAccessInterface;
        this.equationsOutputBoundary = equationsOutputBoundary;
    }

    @Override
    public void executeSolve(ODESystem system) {
        System.out.println("Solving equations");
        try {
            final String[] solutions = equationsDataAccessInterface.getSolution(system);
            equationsOutputBoundary.prepareSolutionsSuccessView(solutions);
        }
        catch (APIAccessException exception) {
            exception.printStackTrace();
            equationsOutputBoundary.prepareSolutionsFailureView("Error analyzing solutions: " + exception.getMessage());
        }
    }

    @Override
    public void extractCriticalPoints(ODESystem system) {
        System.out.println("Extracting critical points");
        try {
            final String[] criticalPoints = equationsDataAccessInterface.getCritPoints(system);
            equationsOutputBoundary.prepareCritPointsSuccessView(criticalPoints);
        }
        catch (APIAccessException exception) {
            exception.printStackTrace();
            equationsOutputBoundary.prepareCritPointsFailureView(
                    "Errror extracting critical points: " + exception.getMessage());
        }
    }
}
