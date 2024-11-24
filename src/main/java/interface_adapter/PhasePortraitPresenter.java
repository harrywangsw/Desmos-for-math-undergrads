package interface_adapter;
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
import org.jfree.chart.ChartFrame;

public class PhasePortraitPresenter {
    private Boolean show_vectors;
    private float left_bound = -1.0f;
    private float right_bound = 1.0f;
    private float upper_bound = 1.0f;
    private float lower_bound = -1.0f;
    private int vector_amount = 20;
    private float dt = 0.001f;

    ///assume variable has one element, expression is the expression for the second derivative
    public List<List<Float>> create_phase_vectors(String expression, String[] variable) throws Exception {
        List<List<Float>> vectors = new ArrayList<>();
        for (int i = 0; i < vector_amount; i++){
            for(int j = 0; j < variable.length; j++){
                List<Float> point = new ArrayList<>();
                point.add((right_bound-left_bound)/vector_amount*i+left_bound);
                point.add((upper_bound-lower_bound)/vector_amount*j+lower_bound);
                float dx = point.get(1)*dt;
                String[] vars = {variable[0], variable[0]+"_dot"};
                float dv = newton.evaluate_single_ODE_at_point(expression, vars, point);
                vectors.add(Arrays.asList(point.get(0), point.get(1), dx, dv));
            }
        }
        return vectors;
    }

    public static void plot_vectors(List<List<Float>> vectors) {
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

        // create and display a frame...
        ChartFrame frame = new ChartFrame("First", theChart);
        frame.pack();
        frame.setVisible(true);
    }
}