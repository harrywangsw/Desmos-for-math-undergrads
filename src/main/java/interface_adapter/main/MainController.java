package interface_adapter.main;


import use_case.note.NoteInputBoundary;

public class MainController {

    private final NoteInputBoundary noteInteractor;

    public MainController(MainInputBoundary mainInteractor) {
        this.noteInteractor = noteInteractor;
    }

    /**
     * Executes the Note related Use Cases.
     * @param note the note to be recorded
     */
    public void execute(String note) {
        if (note != null) {
            noteInteractor.executeSave(note);
        }
        else {
            noteInteractor.executeRefresh();
        }
    }
}
