package keyboard;

import jm.music.data.Note;
import jm.util.Play;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class NotePlay extends Observable implements Observer {
    private final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(12);
    public Note N;
    private MusicSheetGraphics sheetPanel;

    public NotePlay(MusicSheetGraphics sheetPanel) {
        addObserver(this);
        this.sheetPanel = sheetPanel;
    }

    public void execute(Note n) {
        this.N = n;
        this.setChanged();
        this.notifyObservers();
        //executor.shutdown();
    }

    @Override
    public void update(Observable o, Object arg) {
        executor.submit(() -> {
            //N.setFrequency(N.getFrequency());
            Play.midi(((NotePlay)o).N);
            sheetPanel.evaluatePlayerInput(N.getName());
        });
    }
}
