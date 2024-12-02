package use_case.main;

public interface MainInputBoundary {

    void executePlot(String[] equations);

    void executePhasePotrait(String[] equations) throws Exception;

    void executeHelp();

    void executePreviousGraphs();

}
