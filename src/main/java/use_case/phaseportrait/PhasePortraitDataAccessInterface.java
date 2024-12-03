package use_case.phaseportrait;

import java.util.List;

import use_case.equations.ApiAccessException;

/**
 * A dataaccess interface.
 */
public interface PhasePortraitDataAccessInterface {
    /**
     * Evaluates a single-variabele ODE at a point.
     * @param exp expression of the ODE
     * @param vars variable
     * @param point the point
     * @return the result of the evaluation, x_dot
     * @throws ApiAccessException when newton api call returns error
     */
    float evaluatesingleOdeatpoint(String exp, String[] vars, List<Float> point) throws ApiAccessException;

    /**
     * Solve the ode numerically using forward euler.
     * @param exp expression
     * @param vars variable
     * @param ico initial conditions
     * @param end_time end time for the euler algorithm
     * @return the solutions for the time series
     * @throws ApiAccessException when newton api returns errors
     */
    List<List<Float>> eulersolve(String[] exp, String[] vars, Float[] ico, float end_time) throws ApiAccessException;
}
