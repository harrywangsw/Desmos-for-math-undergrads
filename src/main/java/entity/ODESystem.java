package entity;

/**
 * ODESystem Entity to represent a system of ordinary differential equations and their associated variables.
 */
public class ODESystem {
    public static final String[] VARIABLES = new String[]{"x", "y", "z", "a", "b", "c", "d", "e", "f", "g", "h",
        "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w"};

    private String[] equations;
    private String[] variables;
    private Float[] initialconditions;

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

    public Float[] getInitialConditions() {return initialconditions;}
}
