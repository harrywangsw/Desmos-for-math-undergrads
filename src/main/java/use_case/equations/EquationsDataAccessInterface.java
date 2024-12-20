package use_case.equations;

import entity.OdeSystem;

/**
 * Data Access Interface for the Equations use cases.
 */
public interface EquationsDataAccessInterface {
    /**
     * Gets the solutions for the given system using the API.
     *
     * @param system The system of ODEs to find the solution for.
     * @return the solutions to the given system of ODES
     * @throws ApiAccessException if there is an error access the API or the data returned by it
     */
    String[] getSolution(OdeSystem system) throws ApiAccessException;

    /**
     * Gets the critical points for the given system using the API.
     *
     * @param system The system of ODEs to find the critical points for.
     * @return the critical points of the given system of ODES
     * @throws ApiAccessException if there is an error access the API or the data returned by it
     */
    String[] getCritPoints(OdeSystem system) throws ApiAccessException;
}
