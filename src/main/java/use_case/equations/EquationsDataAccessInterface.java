package use_case.equations;

import entity.ODESystem;

/**
 * Data Access Interface for the Equations use cases.
 */
public interface EquationsDataAccessInterface {
    /**
     * Gets the solutions for the given system using the API.
     *
     * @param system The system of ODEs to find the solution for.
     * @return the solutions to the given system of ODES
     * @throws APIAccessException if there is an error access the API or the data returned by it
     */
    String[] getSolution(ODESystem system) throws APIAccessException;

    /**
     * Gets the critical points for the given system using the API.
     *
     * @param system The system of ODEs to find the critical points for.
     * @return the critical points of the given system of ODES
     * @throws APIAccessException if there is an error access the API or the data returned by it
     */
    String[] getCritPoints(ODESystem system) throws APIAccessException;
}
