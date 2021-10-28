import jm.music.data.CPhrase;
import jm.music.data.Note;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.util.Play;
import jm.JMC;
import jm.util.View;
import keyboard.Keyboard;

public class Main implements JMC {

    public static void main(String[] args) throws InterruptedException {
        Phrase phr = new Phrase();
        for(int i = 0; i< 50; i++) {
            Note n = new Note((int)(Math.random()*60 + 30), SQ * (int)(Math.random()*8 + 1));
            phr.addNote(n);
        }
        View.notate(phr);
    }

    /*int[] chord = {F2,F4};
        CPhrase ph = new CPhrase(0.0);
        ph.addChord(chord, 1.);
        Part pt = new Part(ph);

        View.notate(pt);

        pt.removePhrase(0);
        pt.add(new Phrase(new Note(C4, 1)));
        while(true){
            pt.add(new Phrase(new Note(C4, 1)));
            Thread.sleep(500);
            pt.add(new Phrase(new Note(D4, 1)));
            Thread.sleep(500);
        }*/


}
