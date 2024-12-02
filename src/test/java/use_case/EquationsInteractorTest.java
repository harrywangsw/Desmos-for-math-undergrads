package use_case;

import entity.OdeSystem;

import org.junit.Test;
import use_case.equations.ApiAccessException;
import use_case.equations.EquationsDataAccessInterface;
import use_case.equations.EquationsOutputBoundary;

import static org.junit.Assert.*;

public class EquationsInteractorTest {

    @Test
    public void testExecuteSolveSuccess() {
        EquationsDataAccessInterface noteDAO = new EquationsDataAccessInterface() {
            String[] testStr = new String[]{"10", "15"};
            @Override
            public String[] getSolution(OdeSystem system) throws ApiAccessException {
                return testStr;
            }

            @Override
            public String[] getCritPoints(OdeSystem system) throws ApiAccessException {
                return testStr;
            }
        };

        EquationsOutputBoundary noteOB = new EquationsOutputBoundary() {
            @Override
            public void prepareCritPointsSuccessView(String[] criticalPoints) {
                assertEquals(criticalPoints.length, 2);
                assertEquals(criticalPoints[0], "10");
                assertEquals(criticalPoints[1], "15");

            }

            @Override
            public void prepareCritPointsFailureView(String error) {fail(null);

            }

            @Override
            public void prepareSolutionsSuccessView(String[] solutions) {
                assertEquals(solutions.length, 2);
                assertEquals(solutions[0], "10");
                assertEquals(solutions[1], "15");

            }

            @Override
            public void prepareSolutionsFailureView(String error) {fail(null);

            }
        };
        EquationsInteractor equationsInteractor = new EquationsInteractor(noteDAO, noteOB);
        String[] testEquations = {"x=10", "y=15"};
        String[] testVariables = {"x", "y"};
        OdeSystem system = new OdeSystem(testEquations, testVariables);
        equationsInteractor.executeSolve(system);
    }
    @Test
    public void testExtractCriticalPointsSuccess() {
        EquationsDataAccessInterface noteDAO = new EquationsDataAccessInterface() {
            String[] testStr = new String[]{"10", "15"};
            @Override
            public String[] getSolution(OdeSystem system) throws ApiAccessException {
                return testStr;
            }

            @Override
            public String[] getCritPoints(OdeSystem system) throws ApiAccessException {
                return testStr;
            }
        };

        EquationsOutputBoundary noteOB = new EquationsOutputBoundary() {
            @Override
            public void prepareCritPointsSuccessView(String[] criticalPoints) {
                assertEquals(criticalPoints.length, 2);
                assertEquals(criticalPoints[0], "10");
                assertEquals(criticalPoints[1], "15");

            }

            @Override
            public void prepareCritPointsFailureView(String error) {fail(null);

            }

            @Override
            public void prepareSolutionsSuccessView(String[] solutions) {
                assertEquals(solutions.length, 2);
                assertEquals(solutions[0], "10");
                assertEquals(solutions[1], "15");

            }

            @Override
            public void prepareSolutionsFailureView(String error) {fail(null);

            }
        };
        EquationsInteractor equationsInteractor = new EquationsInteractor(noteDAO, noteOB);
        String[] testEquations = {"x=10", "y=15"};
        String[] testVariables = {"x", "y"};
        OdeSystem system = new OdeSystem(testEquations, testVariables);
        equationsInteractor.extractCriticalPoints(system);
    }
}
