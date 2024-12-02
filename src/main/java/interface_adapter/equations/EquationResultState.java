package interface_adapter.equations;

import javax.swing.ImageIcon;

import entity.OdeSystem;

/**
 * The State of the result after processing equations.
 */
public class EquationResultState {
    private OdeSystem odeSystem;
    private ImageIcon[] criticalPoints;
    private ImageIcon[] solutions;
    private String error;

    public EquationResultState(String[] equations, String[] variables) {
        this.setOdeSystem(equations, variables);
    }

    public OdeSystem getOdeSystem() {
        return odeSystem;
    }

    /**
     * Sets the ODESystem in the State.
     * @param equations the equations given by the user.
     * @param variables the variables associated with the equations.
     */
    public void setOdeSystem(String[] equations, String[] variables) {
        this.odeSystem = new OdeSystem(equations, variables);
    }

    public ImageIcon[] getCriticalPoints() {
        return criticalPoints;
    }

    public void setCriticalPoints(ImageIcon[] criticalPoints) {
        this.criticalPoints = criticalPoints;
    }

    public ImageIcon[] getSolutions() {
        return solutions;
    }

    public void setSolutions(ImageIcon[] solutions) {
        this.solutions = solutions;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
