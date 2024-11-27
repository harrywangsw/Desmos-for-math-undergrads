package interface_adapter.phase_portrait;

import entity.ODESystem;
import org.jfree.chart.JFreeChart;

import java.util.List;

public class PhasePortraitState {
    private float vector_scale;
    private float left_bound;
    private float right_bound;
    private float upper_bound;
    private float lower_bound;
    private int vector_amount;
    private JFreeChart plot;
    private List<List<Float>> unit_vectors;
    private ODESystem system;

    public void setchart(JFreeChart chart) {
        this.plot = chart;
    }

    public void setscale(float scale) {
        this.vector_scale = scale;
    }

    public float getscale() {return vector_scale;};
    public List<List<Float>> getunit_vectors() {return unit_vectors;};
    public JFreeChart getplot(){return plot;};
    public ODESystem getsystem(){return system;};
}
