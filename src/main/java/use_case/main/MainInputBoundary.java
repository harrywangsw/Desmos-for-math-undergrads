package use_case.main;

import use_case.note.DataAccessException;

public interface MainInputBoundary {

    void executePlot(String[] equations);

    void executePhasePotrait(String[] equations) throws Exception;

    void executeHelp();

    void executePreviousGraphs() throws DataAccessException;

}
