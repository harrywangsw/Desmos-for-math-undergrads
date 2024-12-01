package use_case;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.VectorRenderer;
import org.jfree.data.xy.VectorSeries;
import org.jfree.data.xy.VectorSeriesCollection;

import use_case.equations.APIAccessException;
import use_case.phaseportrait.PhasePortraitDataAccessInterface;
import use_case.phaseportrait.PhasePortraitInputBoundary;
import use_case.phaseportrait.PhasePortraitOutputBoundary;

/**
 * Implements inputboundary.
 */
public class PhasePortraitInteractor implements PhasePortraitInputBoundary {
    private final PhasePortraitDataAccessInterface phasePortraitDataAccessInterface;
    private final PhasePortraitOutputBoundary phasePortraitOutputBoundary;
    private final int defaultvectoramount = 10;
    private float leftbound;
    private float rightbound;
    private float upperbound;
    private float lowerbound;
    private final int vectoramount;

    public PhasePortraitInteractor(PhasePortraitDataAccessInterface phasePortraitDataAccessInterface,
                                   PhasePortraitOutputBoundary phasePortraitOutputBoundary) {
        this.phasePortraitDataAccessInterface = phasePortraitDataAccessInterface;
        this.phasePortraitOutputBoundary = phasePortraitOutputBoundary;
        this.leftbound = -1.0f;
        this.rightbound = 1.0f;
        this.upperbound = 1.0f;
        this.lowerbound = -1.0f;
        this.vectoramount = defaultvectoramount;
    }

    /**
     * Create Phase vectors.
     *
     * @param expression expressions on the rhs of the ode
     * @param variable   varaibles
     * @return 2d arrau containing the vectors
     * @throws Exception when newton api call returns errors
     */
    public List<List<Float>> createphasevectors(String[] expression,
                                                String[] variable) throws APIAccessException {
        List<List<Float>> vectors = new ArrayList<>();
        List<List<Float>> unitvect = new ArrayList<>();
        for (int i = 0; i < vectoramount; i++) {
            for (int j = 0; j < vectoramount; j++) {
                List<Float> point = new ArrayList<>();
                point.add((rightbound - leftbound) / vectoramount * i + leftbound);
                point.add((upperbound - lowerbound) / vectoramount * j + lowerbound);
                String[] vars = {variable[0], variable[1]};
                float dx = phasePortraitDataAccessInterface.evaluatesingleOdeatpoint(expression[0], vars, point);
                float dy = phasePortraitDataAccessInterface.evaluatesingleOdeatpoint(expression[1], vars, point);
                float magn = (float) java.lang.Math.sqrt(dx * dx + dy * dy);
                if (magn == 0) {
                    unitvect.add(Arrays.asList(0f, 0f));
                    vectors.add(Arrays.asList(point.get(0), point.get(1), 0f, 0f));
                    continue;
                }
                vectors.add(Arrays.asList(point.get(0), point.get(1), dx / magn, dy / magn));
            }
        }
        return vectors;
    }

    /**
     * Create phase plot.
     * @param vectors the vectors to be plotted
     * @return JFreechart containing the cplot
     */
    public JFreeChart createchart(List<List<Float>> vectors) {
        // create a dataset...
        // First we create a dataset
        // We create a vector series collection
        VectorSeriesCollection dataSet = new VectorSeriesCollection();

        VectorSeries vectorSeries = new VectorSeries("First Series");

        for (List<Float> vector : vectors) {
            vectorSeries.add(vector.get(0), vector.get(1), vector.get(2), vector.get(2 + 1));
        }

        dataSet.addSeries(vectorSeries);
        VectorRenderer r = new VectorRenderer();

        XYPlot xyPlot = new XYPlot(dataSet, new NumberAxis("Axis X"), new NumberAxis("Axis Y"), r);
        JFreeChart theChart;

        theChart = new JFreeChart(xyPlot);
        theChart.setTitle("Phase portrait");

        return theChart;
    }

    @Override
    public JFreeChart changescale(List<List<Float>> unit_vectors, float vector_scale) {
        List<List<Float>> vectors = new ArrayList<>();
        for (int i = 0; i < unit_vectors.size(); i++) {
            vectors.add(Arrays.asList(unit_vectors.get(i).get(0), unit_vectors.get(i).get(1),
                    unit_vectors.get(i).get(2) * vector_scale, unit_vectors.get(i).get(1 + 2) * vector_scale));
        }
        phasePortraitOutputBoundary.changechart(createchart(vectors), vector_scale, unit_vectors);
        return createchart(vectors);
    }

    @Override
    public void changeviewbox(String[] expression, String[] variable, float upb, float lb,
                              float leftb, float rb, float vector_size) throws APIAccessException {
        this.upperbound = upb;
        this.lowerbound = lb;
        this.leftbound = leftb;
        this.rightbound = rb;
        List<List<Float>> vectors = createphasevectors(expression, variable);
        phasePortraitOutputBoundary.changechart(createchart(vectors), vector_size, vectors);
    }
}
