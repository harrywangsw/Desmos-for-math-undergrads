package use_case;

import entity.ODESystem;
import use_case.equations.APIAccessException;
import use_case.equations.EquationsDataAccessInterface;
import use_case.equations.EquationsInputBoundary;
import use_case.equations.EquationsOutputBoundary;

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
            String[] solutions = equationsDataAccessInterface.getSolution(system);
            equationsOutputBoundary.prepareSolutionsSuccessView(solutions);
        }
        catch (APIAccessException e) {
            e.printStackTrace();
            equationsOutputBoundary.prepareSolutionsFailureView();
        }
    }

    @Override
    public void extractCriticalPoints(ODESystem system) {
        System.out.println("Extracting critical points");
        try {
            String[] criticalPoints = equationsDataAccessInterface.getCritPoints(system);
            equationsOutputBoundary.prepareCritPointsSuccessView(criticalPoints);
        }
        catch (APIAccessException e) {
            e.printStackTrace();
            equationsOutputBoundary.prepareCritPointsFailureView();
        }
    }
}