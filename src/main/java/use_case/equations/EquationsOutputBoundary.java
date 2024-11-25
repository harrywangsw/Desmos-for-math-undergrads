package use_case.equations;

public interface EquationsOutputBoundary {
    void prepareCritPointsSuccessView(String[] criticalPoints);

    void prepareCritPointsFailureView();
}
