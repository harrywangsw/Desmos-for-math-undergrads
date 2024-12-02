package use_case.main;

import use_case.note.DataAccessException;

public interface MainInputBoundary {

    /**
     * Load all graphs.
     * @param equations equations
     */

    void executePlot(String[] equations);

    /**
     * Execute phase portrait.
     * @param equations equations
     * @throws Exception Exception
     */

    void executePhasePortrait(String[] equations) throws Exception;
    /**
     * Execute Help.
     */

    void executeHelp();

    /**
     * Execute previous graphs.
     * @throws DataAccessException DataAccessException
     */
    void executePreviousGraphs() throws DataAccessException;

}
