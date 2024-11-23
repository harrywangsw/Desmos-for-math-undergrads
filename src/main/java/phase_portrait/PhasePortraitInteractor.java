package phase_portrait;
import app.newton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jfree.data.xy.VectorSeries;
import org.jfree.data.xy.VectorSeriesCollection;
import org.jfree.chart.renderer.xy.VectorRenderer;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.JFreeChart;
import use_case.phase_portrait.PhasePortraitInputBoundary;

import static java.lang.Math.sqrt;

public class PhasePortraitInteractor implements PhasePortraitInputBoundary {
    private Boolean show_vectors;
    private float left_bound;
    private float right_bound;
    private float upper_bound ;
    private float lower_bound ;
    private int vector_amount ;

    public PhasePortraitInteractor() {
        this.left_bound = -1.0f;
        this.right_bound = 1.0f;
        this.upper_bound = 1.0f;
        this.lower_bound = -1.0f;
        this.vector_amount = 10;
    }

    ///assume variable and expression both have two elements(planar system), expression is the expression for the derivative
    public List<List<Float>> create_phase_vectors(String[] expression, String[] variable) throws Exception {
        List<List<Float>> vectors = new ArrayList<>();
        List<List<Float>> unit_vect = new ArrayList<>();
        for (int i = 0; i < vector_amount; i++){
            for(int j = 0; j < vector_amount; j++){
                List<Float> point = new ArrayList<>();
                point.add((right_bound-left_bound)/vector_amount*i+left_bound);
                point.add((upper_bound-lower_bound)/vector_amount*j+lower_bound);
                String[] vars = {variable[0], variable[1]};
                float dx = newton.evaluate_single_ODE_at_point(expression[0], vars, point);
                float dy = newton.evaluate_single_ODE_at_point(expression[1], vars, point);
                float magn = (float) sqrt(dx*dx+dy*dy);
                if(magn == 0){
                    unit_vect.add(Arrays.asList(0f, 0f));
                    vectors.add(Arrays.asList(point.get(0), point.get(1), 0f, 0f));
                    break;
                }
                vectors.add(Arrays.asList(point.get(0), point.get(1), dx/magn, dy/magn));
            }
        }
        return vectors;
    }

    public JFreeChart plot_vectors(List<List<Float>> vectors) {
        // create a dataset...
        // First we create a dataset



        // We create a vector series collection
        VectorSeriesCollection dataSet= new VectorSeriesCollection();

        VectorSeries vectorSeries=new VectorSeries("First Series");

        for (List<Float> vector : vectors) {
            vectorSeries.add(vector.get(0), vector.get(1), vector.get(2), vector.get(3));
        }

        dataSet.addSeries(vectorSeries);



        VectorRenderer r = new VectorRenderer();
        //r.setBasePaint(Color.white);
        //r.setSeriesPaint(0, Color.blue);

        XYPlot xyPlot = new XYPlot(dataSet, new NumberAxis("Axis X"), new NumberAxis("Axis Y"), r);

        // Create a Chart
        JFreeChart theChart;

        theChart = new JFreeChart(xyPlot);
        theChart.setTitle("Phase portrait");

        return theChart;
    }

    @Override
    public JFreeChart change_scale(List<List<Float>> unit_vectors, float vector_size) {
        List<List<Float>> vectors = new ArrayList<>();
        for (int i = 0; i < unit_vectors.size(); i++){
            vectors.add(Arrays.asList(unit_vectors.get(i).get(0), unit_vectors.get(i).get(1), unit_vectors.get(i).get(2)*vector_size, unit_vectors.get(i).get(3)*vector_size));
        }
        return plot_vectors(vectors);
    }
}