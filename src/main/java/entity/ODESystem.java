package entity;

public class ODESystem {
    private String[] equations;
    private String[] variables;

    public ODESystem(String[] equations, String[] variables) {
        this.equations = equations;
        this.variables = variables;
    }

    public String[] getEquations() {
        return equations;
    }

    public String[] getVariables() {
        return variables;
    }
}
