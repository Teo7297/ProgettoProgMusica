package keyboard;

import jm.music.data.Note;
import jm.util.Play;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class NotePlay extends Observable implements Observer {
    private final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(6);
    private Queue<Note> CQ;

    public NotePlay() {
        this.CQ = new LinkedList<>();
        addObserver(this);
    }

    public void execute(Note n) {
        this.CQ.add(n);
        this.setChanged();
        this.notifyObservers();
        //executor.shutdown();
    }

    @Override
    public void update(Observable o, Object arg) {
        executor.submit(() -> {
            Play.midi(this.CQ.poll());
        });
    }
}
