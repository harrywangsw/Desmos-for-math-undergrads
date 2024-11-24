package use_case;

import use_case.equations.EquationsDataAccessInterface;
import use_case.equations.EquationsInputBoundary;
import use_case.equations.EquationsOutputBoundary;

public class EquationsInteractor implements EquationsInputBoundary {

    private final EquationsDataAccessInterface equationsDataAccessInterface;
    private final EquationsOutputBoundary equationsOutputBoundary;
    // Note: this program has it hardcoded which user object it is getting data for;
    // you could change this if you wanted to generalize the code. For example,
    // you might allow a user of the program to create a new note, which you
    // could store as a "user" through the API OR you might maintain all notes
    // in a JSON object stored in one common "user" stored through the API.

    public EquationsInteractor(EquationsDataAccessInterface noteDataAccessInterface,
                          EquationsOutputBoundary noteOutputBoundary) {
        this.equationsDataAccessInterface = noteDataAccessInterface;
        this.equationsOutputBoundary = noteOutputBoundary;
    }

    @Override
    public void executeSolve() {
        System.out.println("Solving equations");
    }

    @Override
    public void extractCriticalPoints() {
        System.out.println("Extracting critical points");

    }
}
