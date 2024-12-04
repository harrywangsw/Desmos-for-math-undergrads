package use_case;

import java.util.List;

import org.junit.Test;


public class MainInteractorTest {
    @Test
    public void testExecutePlotRunsWithoutError() {
        MainOutputBoundary mainOutputMock = new MainOutputBoundary() {
            @Override
            public void preparePreviousGraphView() {
                System.out.println("This will prepare the previous graph view");
            }

        };
        PreviousGraphsOutputBoundary previousGraphsOutputMock = new PreviousGraphsOutputBoundary() {
            @Override
            public void displayGraphs(List<String> graphList) {
                System.out.println("This will display the graphs");

            }

            @Override
            public void preparePreviousGraphFailView(String error) {
                System.out.println("This will prepare the previous graph fail");

            }
        };

        MainInteractor mainInteractor = new MainInteractor(mainOutputMock, previousGraphsOutputMock);

        String[] equations = {"2x", "2y"};

        try {
            mainInteractor.executePlot(equations);
            System.out.println("executePlot ran without error.");
        } catch (Exception e) {
            assert false : "Exception occurred in executePlot: " + e.getMessage();
        }
    }

    @Test
    public void testExecutePhasePortraitRunsWithoutError() {

        MainOutputBoundary mainOutputMock = new MainOutputBoundary() {
            @Override
            public void preparePreviousGraphView() {
                System.out.println("This will prepare the previous graph view");
            }
        };
        PreviousGraphsOutputBoundary previousGraphsOutputMock = new PreviousGraphsOutputBoundary() {
            @Override
            public void displayGraphs(List<String> graphList) {
                System.out.println("This will display the graphs");

            }

            @Override
            public void preparePreviousGraphFailView(String error) {
                System.out.println("This will prepare the previous graph fail");

            }
        };

        MainInteractor mainInteractor = new MainInteractor(mainOutputMock, previousGraphsOutputMock);

        String[] equations = {"x", "y"};

        try {
            mainInteractor.executePhasePortrait(equations);
            System.out.println("executePhasePortrait ran without error.");
        } catch (Exception e) {
            assert false : "Exception occurred in executePhasePortrait: " + e.getMessage();
        }
    }

    @Test
    public void testExecuteHelpRunsWithoutError() {
        MainOutputBoundary mainOutputMock = new MainOutputBoundary() {
            @Override
            public void preparePreviousGraphView() {
                System.out.println("This will prepare the previous graph view");
            }
        };
        PreviousGraphsOutputBoundary previousGraphsOutputMock = new PreviousGraphsOutputBoundary() {
            @Override
            public void displayGraphs(List<String> graphList) {
                System.out.println("This will display the graphs");

            }

            @Override
            public void preparePreviousGraphFailView(String error) {
                System.out.println("This will prepare the previous graph fail");

            }
        };

        MainInteractor mainInteractor = new MainInteractor(mainOutputMock, previousGraphsOutputMock);

        try {
            mainInteractor.executeHelp();
            System.out.println("executeHelp ran without error.");
        } catch (Exception e) {
            assert false : "Exception occurred in executeHelp: " + e.getMessage();
        }
    }

    @Test
    public void testExecutePreviousGraphsRunsWithoutError() {
       MainOutputBoundary mainOutputMock = new MainOutputBoundary() {
            @Override
            public void preparePreviousGraphView() {
                System.out.println("This will prepare the previous graph view");
            }
        };
        PreviousGraphsOutputBoundary previousGraphsOutputMock = new PreviousGraphsOutputBoundary() {
            @Override
            public void displayGraphs(List<String> graphList) {
                System.out.println("This will display the graphs");

            }

            @Override
            public void preparePreviousGraphFailView(String error) {
                System.out.println("This will prepare the previous graph fail");

            }
        };

        MainInteractor mainInteractor = new MainInteractor(mainOutputMock, previousGraphsOutputMock);

        try {
            mainInteractor.executePreviousGraphs();
            System.out.println("executePreviousGraphs ran without error.");
        } catch (Exception e) {
            assert false : "Exception occurred in executePreviousGraphs: " + e.getMessage();
        }
    }
}
