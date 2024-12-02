package use_case.phaseportrait;

import java.util.List;

import entity.OdeSystem;
import interface_adapter.phaseportrait.PhasePortraitViewModel;
import org.jfree.chart.JFreeChart;
import use_case.equations.APIAccessException;

/**
 * A interface.
 */
public interface PhasePortraitInputBoundary {
    /**
     * A method for creating the vectors in the phase portrait.
     * @param expression expressions on the rhs of the ode
     * @param variable varaibles
     * @return a 2d list of floats
     * @throws Exception when the newton api call returns error
     */
    List<List<Float>> createphasevectors(String[] expression, String[] variable) throws Exception;

    /**
     * Creat the JFreechart that contains the phase portrait.
     * @param vectors the vectors to be plotted
     * @return the plot
     */
    JFreeChart createchart(List<List<Float>> vectors);

    /**
     * Changes the scale of the vectors.
     * @param vectors the vectors in the phase portrait
     * @param vector_size the desired size of the vectors
     * @return the plot
     */
    JFreeChart changescale(List<List<Float>> vectors, float vector_size);

    /**
     * Changes the viewing window of the phase portrait.
     * @param expression expressions on the rhs of the ode
     * @param variable varaibles
     * @param upb upper bound
     * @param lob lower bound
     * @param leftb left bound
     * @param rib right bound
     * @param vector_size desired vector size
     * @throws APIAccessException when the newton api call returns error
     */
    void changeviewbox(String[] expression, String[] variable, float upb, float lob, float leftb,
                       float rib, float vector_size) throws APIAccessException;

    void makePhase(PhasePortraitViewModel viewModel, PhasePortraitOutputBoundary outputboundary, OdeSystem sys) throws Exception;
}
