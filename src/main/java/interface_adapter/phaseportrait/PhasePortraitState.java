package interface_adapter.phaseportrait;

import entity.OdeSystem;
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
    private OdeSystem system;

    public PhasePortraitState(OdeSystem system, JFreeChart plot, List<List<Float>> unit_vectors, float left_bound, float right_bound, float upper_bound, float lower_bound, float vector_scale) {
        this.system = system;
        this.plot = plot;
        this.left_bound = left_bound;
        this.right_bound = right_bound;
        this.upper_bound = upper_bound;
        this.lower_bound = lower_bound;
        this.unit_vectors = unit_vectors;
        this.vector_scale = vector_scale;
    }

    public PhasePortraitState(OdeSystem system) {
        this.system = system;
    }

    public void setchart(JFreeChart chart) {
        this.plot = chart;
    }

    public void setscale(float scale) {
        this.vector_scale = scale;
    }

    public void setUnitvectors(List<List<Float>> vectors) {this.unit_vectors = vectors;}

    public float getscale() {return vector_scale;};
    public List<List<Float>> getunit_vectors() {return unit_vectors;};
    public JFreeChart getplot(){return plot;};
    public OdeSystem getsystem(){return system;};
    public float getleft_bound() {return left_bound;}
    public float getright_bound() {return right_bound;}
    public float getupper_bound() {return upper_bound;}
    public float getlower_bound() {return lower_bound;}
}
