package use_case;

import org.junit.Test;

import java.util.List;

import org.jfree.chart.JFreeChart;
import use_case.equations.APIAccessException;
import use_case.phaseportrait.PhasePortraitDataAccessInterface;
import use_case.phaseportrait.PhasePortraitOutputBoundary;

import static org.junit.Assert.*;

public class PhasePortraitInteractorTest {


    @Test
    public void test_createphasevectors() throws Exception {
        PhasePortraitDataAccessInterface testInterface = new PhasePortraitDataAccessInterface() {
            List<List<Float>> testList = List.of(
                    List.of(0f, 0f, 1f, 0f),
                    List.of(1f, 1f, 10f, 1f),
                    List.of(-1f, -5f, -1f, 0f),
                    List.of(0.5f, 0.5f, 0.5f, 0.5f)
            );

            @Override
            public float evaluatesingleOdeatpoint(String exp, String[] vars, List<Float> point) throws APIAccessException {
                return 10;
            }

            @Override
            public List<List<Float>> eulersolve(String[] exp, String[] vars, Float[] ico, float end_time) throws APIAccessException {
                return testList;
            }
        };

        PhasePortraitOutputBoundary testOutput = new PhasePortraitOutputBoundary() {

            @Override
            public void changechart(JFreeChart plot, float vector_scale) {

            }
        };
        PhasePortraitInteractor phasePortraitInteractor = new PhasePortraitInteractor(testInterface, testOutput);
        String[] expression = {"100", "50"};
        String[] variable = {"x", "y"};
        List<List<Float>> vectors = phasePortraitInteractor.createphasevectors(expression, variable);
        assertNotNull(vectors);
        assertEquals(100, vectors.size());
        for (List<Float> vector : vectors) {
            assertEquals(4, vector.size());
        }
    }

    @Test
    public void test_createchart() {
        PhasePortraitDataAccessInterface testInterface = new PhasePortraitDataAccessInterface() {
            List<List<Float>> testList = List.of(
                    List.of(0f, 0f, 1f, 0f),
                    List.of(1f, 1f, 10f, 1f),
                    List.of(-1f, -5f, -1f, 0f),
                    List.of(0.5f, 0.5f, 0.5f, 0.5f)
            );

            @Override
            public float evaluatesingleOdeatpoint(String exp, String[] vars, List<Float> point) throws APIAccessException {
                return 10;
            }

            @Override
            public List<List<Float>> eulersolve(String[] exp, String[] vars, Float[] ico, float end_time) throws APIAccessException {
                return testList;
            }
        };

        PhasePortraitOutputBoundary testOutput = new PhasePortraitOutputBoundary() {

            @Override
            public void changechart(JFreeChart plot, float vector_scale) {

            }
        };
        List<List<Float>> vectors = List.of(
                List.of(0f, 0f, 1f, 1f),
                List.of(1f, 1f, -1f, -1f)
        );

        PhasePortraitInteractor phasePortraitInteractor = new PhasePortraitInteractor(testInterface, testOutput);
        JFreeChart chart = phasePortraitInteractor.createchart(vectors);

        assertNotNull(chart);
        assertEquals("Phase portrait", chart.getTitle().getText());
    }

    @Test
    public void test_changescale() {
        PhasePortraitDataAccessInterface testInterface = new PhasePortraitDataAccessInterface() {
            List<List<Float>> testList = List.of(
                    List.of(0f, 0f, 1f, 0f),
                    List.of(1f, 1f, 10f, 1f),
                    List.of(-1f, -5f, -1f, 0f),
                    List.of(0.5f, 0.5f, 0.5f, 0.5f)
            );

            @Override
            public float evaluatesingleOdeatpoint(String exp, String[] vars, List<Float> point) throws APIAccessException {
                return 10;
            }

            @Override
            public List<List<Float>> eulersolve(String[] exp, String[] vars, Float[] ico, float end_time) throws APIAccessException {
                return testList;
            }
        };

        PhasePortraitOutputBoundary testOutput = new PhasePortraitOutputBoundary() {

            @Override
            public void changechart(JFreeChart plot, float vector_scale) {

            }
        };
        List<List<Float>> unitVectors = List.of(
                List.of(0f, 0f, 1f, 0f),
                List.of(1f, 1f, 0f, 1f)
        );
        PhasePortraitInteractor phasePortraitInteractor = new PhasePortraitInteractor(testInterface, testOutput);
        float vectorScale = 2.0f;
        JFreeChart chart = phasePortraitInteractor.changescale(unitVectors, vectorScale);
        assertNotNull(chart);
    }

}