package use_case.equations;

import entity.ODESystem;

public interface EquationsDataAccessInterface {
    String[] getSolution(ODESystem system) throws APIAccessException;

    String[] getCritPoints(ODESystem system) throws APIAccessException;
}