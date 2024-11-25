package entity;

public class ODESystem {
    private String[] equations;
    private String[] variables;

    public static final String[] VARIABLES = {"x", "y", "z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w"};

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
