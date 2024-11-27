package use_case.phase_portrait;

import java.util.List;

public interface PhasePortraitDataAccessInterface {
    float evaluate_single_ODE_at_point(String exp, String[] vars, List<Float> point) throws Exception;
    List<List<Float>> euler_solve(String[] exp, String[] vars, Float[] ic, float end_time) throws Exception;
}
