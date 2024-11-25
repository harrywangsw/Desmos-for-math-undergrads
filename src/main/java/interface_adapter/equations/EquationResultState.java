package interface_adapter.equations;

import entity.ODESystem;

public class EquationResultState {
    private ODESystem odeSystem;
    private String[] criticalPoints;
    private String[] solutions;

    public EquationResultState(String[] equations, String[] variables) {
        this.setOdeSystem(equations, variables);
    }

    public ODESystem getOdeSystem() {
        return odeSystem;
    }

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
}
