package interface_adapter.phase_portrait;

import entity.ODESystem;
import org.jfree.chart.JFreeChart;
import use_case.NoteOutputBoundary;
import use_case.PhasePortraitInteractor;
import use_case.note.NoteDataAccessInterface;
import use_case.phase_portrait.PhasePortraitOutputBoundary;

import java.util.List;

public class PhasePortraitController {

    private final PhasePortraitInteractor phasePortraitInteractor;

    public PhasePortraitController(PhasePortraitInteractor phasePortraitInteractor, PhasePortraitOutputBoundary phasePortraitOutputBoundary) {
        this.phasePortraitInteractor = phasePortraitInteractor;
    }

    public void change_scale(List<List<Float>> unit_vectors, float vector_size) {
        phasePortraitInteractor.change_scale(unit_vectors, vector_size);
    }

    public void change_viewbox(ODESystem system, float lb, float ub, float leftbound, float rightbound, float vector_scale) throws Exception {
        phasePortraitInteractor.change_bounds(system.getEquations(), system.getVariables(), lb, ub, leftbound, rightbound, vector_scale);
    }
}
