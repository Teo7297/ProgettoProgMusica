package keyboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MusicSheetGraphics extends JPanel implements ActionListener{

    private boolean correct;
    private boolean wrong;
    public static int level;
    private static int maxNotationNumber;
    private static double rand;
    private final NoteDrawing currentNote;
    private final NoteDrawing nextNote;
    private final NoteDrawing next2Note;
    private final NoteDrawing next3Note;
    private final ClefDrawing clefDrawing;
    private final StaveDrawing staveDrawing;

    private static boolean GODMODE;

    public MusicSheetGraphics(String clefName, String clefType, int level_, boolean godmode){
        GODMODE = godmode;
        rand = 0.;
        level = level_;

        this.correct = false;
        this.wrong = false;

        this.clefDrawing = new ClefDrawing(clefName, clefType, this);
        this.staveDrawing = new StaveDrawing();

        this.currentNote = new NoteDrawing(120, 115, 142, this.clefDrawing);
        this.nextNote = new NoteDrawing(240, 235, 262, this.clefDrawing);
        this.next2Note = new NoteDrawing(360, 355, 382, this.clefDrawing);
        this.next3Note = new NoteDrawing(480, 475, 502, this.clefDrawing);

        currentNote.generateNextNote();
        nextNote.generateNextNote();
        next2Note.generateNextNote();
        next3Note.generateNextNote();
    }

    public void evaluatePlayerInput(String noteName){

        //TODO: da cambiare la logica di comparazione, Note trasforma a caso alcune note in # e alcune in b
        if((currentNote.getCurrentNote()).equals(noteName) || GODMODE) {
            this.correct = true;
            //CORRECT set score
        } else{
            this.wrong = true;
            //WRONG set score
        }
        Timer t = new Timer(200, this);
        t.setRepeats(false);
        t.start();
    }



    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2.5f));
        g2.setPaint(Color.black);
        staveDrawing.drawStave(g2);
        clefDrawing.drawClef(g2);
        if(this.correct){
            g2.setPaint(Color.green);
        } else if(this.wrong){
            g2.setPaint(Color.red);
        }
        this.currentNote.drawNote(g2);
        g2.setPaint(new Color(179, 179, 204));
        this.nextNote.drawNote(g2);
        if(level < 9) {
            this.next2Note.drawNote(g2);
            if(level < 5) {
                this.next3Note.drawNote(g2);
            }
        }
    }

    public NoteDrawing getCurrentNote(){
        return this.currentNote;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.correct) {
            this.correct = false;
            this.currentNote.setCurrentNote(nextNote.getCurrentNote(), nextNote.getCurrentNotation(), nextNote.getCurrentNotationNumber(), nextNote.getCurrentOctave());
            this.nextNote.setCurrentNote(next2Note.getCurrentNote(), next2Note.getCurrentNotation(), next2Note.getCurrentNotationNumber(), next2Note.getCurrentOctave());
            this.next2Note.setCurrentNote(next3Note.getCurrentNote(), next3Note.getCurrentNotation(), next3Note.getCurrentNotationNumber(), next3Note.getCurrentOctave());
            this.next3Note.generateNextNote();

            this.clefDrawing.changeClef();
        } else {
            this.wrong = false;
        }
        repaint();
    }
}