package use_case;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


class PreviousGraphsInteractorTest {

    private TestOutputBoundary outputBoundary;
    private PreviousGraphsInteractor interactor;

    static class TestOutputBoundary implements PreviousGraphsOutputBoundary {
        private List<String> receivedPaths = new ArrayList<>();

        @Override
        public void displayGraphs(List<String> graphPaths) {
            this.receivedPaths = graphPaths;
        }

        @Override
        public void preparePreviousGraphFailView(String error) {

        }

        public List<String> getReceivedPaths() {
            return receivedPaths;
        }
    }

    @BeforeEach
    void setUp() {
        outputBoundary = new TestOutputBoundary();
        interactor = new PreviousGraphsInteractor(outputBoundary);
    }

    @Test
    void testExecutePreviousGraphs() throws IOException {
        File tempDir = new File("./graphs");
        if (!tempDir.exists()) {
            assertTrue(tempDir.mkdirs());
        }

        File file1 = new File(tempDir, "graph1.png");
        File file2 = new File(tempDir, "graph2.jpg");
        assertTrue(file1.createNewFile());
        assertTrue(file2.createNewFile());

        List<String> expectedPaths = Arrays.asList(file1.getAbsolutePath(), file2.getAbsolutePath());

        interactor.executePreviousGraphs();

        List<String> receivedPaths = outputBoundary.getReceivedPaths();
        assertEquals(expectedPaths.size(), receivedPaths.size());
        Assertions.assertTrue(receivedPaths.containsAll(expectedPaths));

        Assertions.assertTrue(file1.delete());
        Assertions.assertTrue(file2.delete());
        Assertions.assertTrue(tempDir.delete());
    }
}
