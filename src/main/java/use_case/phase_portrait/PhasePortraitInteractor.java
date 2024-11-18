package use_case.phase_portrait;

import interface_adapter.PhasePortraitPresenter;

public class PhasePortraitInteractor {
    public static void main(String[] args) throws Exception {
        PhasePortraitPresenter presenter = new PhasePortraitPresenter();
        String[] vars = {"x"};
        PhasePortraitPresenter.plot_vectors(presenter.create_phase_vectors("-2x", vars));
    }
}
