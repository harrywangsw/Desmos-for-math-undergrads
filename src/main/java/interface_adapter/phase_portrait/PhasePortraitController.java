package interface_adapter.phase_portrait;

import entity.ODESystem;
import use_case.PhasePortraitInteractor;

public class PhasePortraitController {

    private final PhasePortraitInteractor phasePortraitInteractor;

    public PhasePortraitController(PhasePortraitInteractor phasePortraitInteractor) {
        this.phasePortraitInteractor = phasePortraitInteractor;
    }

    public void execute(String task, ODESystem system) {
        switch(task){
            case "create":

        }
    }
}
