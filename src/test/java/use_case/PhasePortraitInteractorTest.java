package use_case;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jfree.data.xy.VectorSeries;
import org.jfree.data.xy.VectorSeriesCollection;
import org.jfree.chart.renderer.xy.VectorRenderer;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.JFreeChart;
import use_case.phase_portrait.PhasePortraitDataAccessInterface;
import use_case.phase_portrait.PhasePortraitInputBoundary;
import use_case.phase_portrait.PhasePortraitOutputBoundary;

import static org.junit.Assert.*;

public class PhasePortraitInteractorTest {


    @Test
    public void test_create_phase_vectors() throws Exception {
        PhasePortraitDataAccessInterface testInterface = new PhasePortraitDataAccessInterface() {
            List<List<Float>> testList = List.of(
                    List.of(0f, 0f, 1f, 0f),
                    List.of(1f, 1f, 10f, 1f),
                    List.of(-1f, -5f, -1f, 0f),
                    List.of(0.5f, 0.5f, 0.5f, 0.5f)
            );

            @Override
            public float evaluate_single_ODE_at_point(String exp, String[] vars, List<Float> point) throws Exception {
                return 10;
            }

            @Override
            public List<List<Float>> euler_solve(String[] exp, String[] vars, Float[] ic, float end_time) throws Exception {
                return testList;
            }
        };

        PhasePortraitOutputBoundary testOutput = new PhasePortraitOutputBoundary() {
            @Override
            public void create_phaseportrait_view(JFreeChart plot) throws Exception {

            }

            @Override
            public void change_chart(JFreeChart plot, float vector_scale) {

            }
        };
        PhasePortraitInteractor phasePortraitInteractor = new PhasePortraitInteractor(testInterface, testOutput);
        String[] expression = {"100", "50"};
        String[] variable = {"x", "y"};
        List<List<Float>> vectors = phasePortraitInteractor.create_phase_vectors(expression, variable);
        assertNotNull(vectors);
        assertEquals(100, vectors.size());
        for (List<Float> vector : vectors) {
            assertEquals(4, vector.size());
        }
    }

    @Test
    public void test_create_chart() {
        PhasePortraitDataAccessInterface testInterface = new PhasePortraitDataAccessInterface() {
            List<List<Float>> testList = List.of(
                    List.of(0f, 0f, 1f, 0f),
                    List.of(1f, 1f, 10f, 1f),
                    List.of(-1f, -5f, -1f, 0f),
                    List.of(0.5f, 0.5f, 0.5f, 0.5f)
            );

            @Override
            public float evaluate_single_ODE_at_point(String exp, String[] vars, List<Float> point) throws Exception {
                return 10;
            }

            @Override
            public List<List<Float>> euler_solve(String[] exp, String[] vars, Float[] ic, float end_time) throws Exception {
                return testList;
            }
        };

        PhasePortraitOutputBoundary testOutput = new PhasePortraitOutputBoundary() {
            @Override
            public void create_phaseportrait_view(JFreeChart plot) throws Exception {

            }

            @Override
            public void change_chart(JFreeChart plot, float vector_scale) {

            }
        };
        List<List<Float>> vectors = List.of(
                List.of(0f, 0f, 1f, 1f),
                List.of(1f, 1f, -1f, -1f)
        );

        PhasePortraitInteractor phasePortraitInteractor = new PhasePortraitInteractor(testInterface, testOutput);
        JFreeChart chart = phasePortraitInteractor.create_chart(vectors);

        assertNotNull(chart);
        assertEquals("Phase portrait", chart.getTitle().getText());
    }

    @Test
    public void test_change_scale() {
        PhasePortraitDataAccessInterface testInterface = new PhasePortraitDataAccessInterface() {
            List<List<Float>> testList = List.of(
                    List.of(0f, 0f, 1f, 0f),
                    List.of(1f, 1f, 10f, 1f),
                    List.of(-1f, -5f, -1f, 0f),
                    List.of(0.5f, 0.5f, 0.5f, 0.5f)
            );

            @Override
            public float evaluate_single_ODE_at_point(String exp, String[] vars, List<Float> point) throws Exception {
                return 10;
            }

            @Override
            public List<List<Float>> euler_solve(String[] exp, String[] vars, Float[] ic, float end_time) throws Exception {
                return testList;
            }
        };

        PhasePortraitOutputBoundary testOutput = new PhasePortraitOutputBoundary() {
            @Override
            public void create_phaseportrait_view(JFreeChart plot) throws Exception {

            }

            @Override
            public void change_chart(JFreeChart plot, float vector_scale) {

            }
        };
        List<List<Float>> unitVectors = List.of(
                List.of(0f, 0f, 1f, 0f),
                List.of(1f, 1f, 0f, 1f)
        );
        PhasePortraitInteractor phasePortraitInteractor = new PhasePortraitInteractor(testInterface, testOutput);
        float vectorScale = 2.0f;
        JFreeChart chart = phasePortraitInteractor.change_scale(unitVectors, vectorScale);
        assertNotNull(chart);
    }

}