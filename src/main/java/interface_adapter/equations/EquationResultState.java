package interface_adapter.equations;

import entity.ODESystem;

/**
 * The State of the result after processing equations.
 */
public class EquationResultState {
    private ODESystem odeSystem;
    private String[] criticalPoints;
    private String[] solutions;
    private String error;

    public EquationResultState(String[] equations, String[] variables) {
        this.setOdeSystem(equations, variables);
    }

    public ODESystem getOdeSystem() {
        return odeSystem;
    }

    /**
     * Sets the ODESystem in the State.
     * @param equations the equations given by the user.
     * @param variables the variables associated with the equations.
     */
    public void setOdeSystem(String[] equations, String[] variables) {
        this.odeSystem = new ODESystem(equations, variables);
    }

    public String[] getCriticalPoints() {
        return criticalPoints;
    }

    public void setCriticalPoints(String[] criticalPoints) {
        this.criticalPoints = criticalPoints;
    }

    public String[] getSolutions() {
        return solutions;
    }

    public void setSolutions(String[] solutions) {
        this.solutions = solutions;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
