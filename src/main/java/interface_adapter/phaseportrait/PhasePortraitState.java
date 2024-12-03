package interface_adapter.phaseportrait;

import java.util.List;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.jfree.chart.JFreeChart;

import entity.OdeSystem;

@Nullable
public class PhasePortraitState {
    private float vectorScale;
    private float leftBound;
    private float rightBound;
    private float upperBound;
    private float lowerBound;
    private int vectorAmount;
    private JFreeChart plot;
    private List<List<Float>> unitVectors;
    private OdeSystem system;

    public PhasePortraitState(OdeSystem system, JFreeChart plot, List<List<Float>> unitVectors, float leftBound,
                              float rightBound, float upperBound, float lowerBound, float vectorScale) {
        this.system = system;
        this.plot = plot;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
        this.unitVectors = unitVectors;
        this.vectorScale = vectorScale;
    }

    public PhasePortraitState(OdeSystem system) {
        this.system = system;
    }

    /**
     * Set chart.
     * @param chart chart
     */
    public void setchart(JFreeChart chart) {
        this.plot = chart;
    }

    /**
     * Set scale.
     * @param scale scale
     */
    public void setscale(float scale) {
        this.vectorScale = scale;
    }

    public void setUnitvectors(List<List<Float>> vectors) {
        this.unitVectors = vectors;
    }

    /**
     * Get scale.
     * @return vector scale
     */
    public float getscale() {
        return vectorScale;
    }

    /**
     * Get unit vectors.
     * @return unit vectors
     */

    public List<List<Float>> getunitVectors() {
        return unitVectors;
    }

    /**
     * Get plot.
     * @return plot
     */

    public JFreeChart getplot() {
        return plot;
    }

    /**
     * Get system.
     * @return system
     */

    public OdeSystem getsystem() {
        return system;
    }

    /**
     * Get left bound.
     * @return left bound
     */

    public float getleftBound() {
        return leftBound;
    }

    /**
     * Get right bound.
     * @return right bound
     */
    public float getrightBound() {
        return rightBound;
    }

    /**
     * Get upper bound.
     * @return upper bound
     */

    public float getupperBound() {
        return upperBound;
    }

    /**
     * Get lower bound.
     * @return lower bound
     */

    public float getlowerBound() {
        return lowerBound;
    }
}
