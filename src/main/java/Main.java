import jm.music.data.CPhrase;
import jm.music.data.Note;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.util.Play;
import jm.JMC;
import jm.util.View;
import keyboard.Keyboard;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import jm.JMC;

import static jm.constants.Pitches.C4;

public class Main extends Observable implements Observer {
    private final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    private Queue<Note> CQ;

    public Main() {
        this.CQ = new LinkedList<>();
        addObserver(this);
    }

    private void execute() {
        for (int i = 0; i < 5; ++i) {
            this.CQ.add(new Note(C4, 1));
            this.setChanged();
            this.notifyObservers(i);

            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }

    @Override
    public void update(Observable o, Object arg) {
        executor.submit(() -> {
                Play.midi(this.CQ.poll());
        });
    }
}