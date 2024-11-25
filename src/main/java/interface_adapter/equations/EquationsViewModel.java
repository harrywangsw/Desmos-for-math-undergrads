package interface_adapter.equations;

import interface_adapter.ViewModel;

public class EquationsViewModel extends ViewModel<EquationResultState> {

    public EquationsViewModel() {
        super("equations");
        setState(new EquationResultState(new String[]{"0"}, new String[]{"x"}));
    }
}
