package keyboard;

import Game.NoteTimer;
import jm.music.data.Note;
import jm.util.Play;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Random;

public class NoteDrawing {
    private static final int[] lineDownThresholds = new int[]{321, 303, 285, 267, 251, 233};
    private static final int[] lineUpThresholds = new int[]{125, 107, 89, 71, 53, 35};
    public static final String[] notes = new String[]{"C","D","E","F","G","A","B"};
    private static final HashMap<String, String> jmusicNotations = new HashMap<String, String>(){{
        put("C#", "C#");
        put("Db", "C#");
        put("D#", "Eb");
        put("Eb", "Eb");
        put("E#", "F");
        put("Fb", "E");
        put("F#", "F#");
        put("Gb", "F#");
        put("G#", "Ab");
        put("Ab", "Ab");
        put("A#", "Bb");
        put("Bb", "Bb");
    }};
    private static final String[] notations = new String[]{"\u266F", "\u266D", "\uD834\uDD2A", "\uD834\uDD2B"};
    private static final HashMap<String, Integer> notesHeights = new HashMap<String, Integer>(){{
        put("C",243);
        put("D",234);
        put("E",225);
        put("F",216);
        put("G",207);
        put("A",198);
        put("B",189);
    }};
    private final int x;
    private final int extraStart;
    private final int extraEnd;
    private String currentNote;
    private static final HashMap<String, Integer> CMap = new HashMap<String, Integer>(){{
        //deltas for note height (+9 = 1 nota) (+63 = 1 ottava)
        put("trebleDelta", 18);
        put("sopranoDelta", 36);
        put("mezzo-sopranoDelta", 54);
        put("altoDelta", 72);
        put("tenorDelta", 90);
        put("baritoneDelta", 108);
        put("bassDelta", 126);
    }};
    private static int maxNotationNumber;
    private int currentNotationNumber;
    private String currentNotation;
    private static final Font font =  new Font("Arimo", Font.PLAIN, 36);
    private static final Font fontDS =  new Font("Arimo", Font.PLAIN, 60);
    private int currentOctave;
    private final ClefDrawing clefDrawing;
    private boolean isFirst;
    private boolean isFirstRound;


    public NoteDrawing(int x, int extraStart, int extraEnd, ClefDrawing clefDrawing, boolean isFirst){
        this.isFirstRound = true;
        this.currentNotation = "";
        this.currentNotationNumber = 0;
        this.isFirst = isFirst;
        this.clefDrawing = clefDrawing;
        this.currentOctave = 4;
        this.x = x;
        this.extraStart = extraStart;
        this.extraEnd = extraEnd;
        this.currentNote = this.clefDrawing.getClefName();
    }

    public static void setMaxNotationNumber(){
        maxNotationNumber = 2;
        if(MusicSheetGraphics.level < 5){
            maxNotationNumber = 0;
        } else if(MusicSheetGraphics.level < 9){
            maxNotationNumber = 1;
        }
    }

    public void drawNote(Graphics2D g2){
        int y = notesHeights.get(this.currentNote) - CMap.get(this.clefDrawing.getClefType()+"Delta") + ((4 - this.currentOctave) * 63); //4 = ottava "base", 63 = distanza sull'asse y per spostarsi di 7 note
        drawExtraLines(y, this.extraStart, this.extraEnd, g2);
        Ellipse2D circle = new Ellipse2D.Double(x,y,18,16);
        //g2.rotate(Math.toRadians(35), x + 18 / 2, y + 16 / 2);
        g2.fill(circle);
        if(!this.currentNotation.equals(""))
            drawNotation(g2, this.x, y);
    }


    private void drawExtraLines(int y, int start, int end, Graphics2D g2){
        //Draw the extra lines
        for (int i = 0; i<6; i++){
            if ((y+8) >= lineDownThresholds[i]){
                Line2D line = new Line2D.Double(new Point2D.Double(start, lineDownThresholds[i]), new Point2D.Double(end,lineDownThresholds[i]));
                g2.draw(line);
            }else if ((y+8) <= lineUpThresholds[i]){
                Line2D line = new Line2D.Double(new Point2D.Double(start, lineUpThresholds[i]), new Point2D.Double(end,lineUpThresholds[i]));
                g2.draw(line);
            }
        }
    }

    private void drawNotation(Graphics2D g2, int x, int y){
        FontRenderContext frc = g2.getFontRenderContext();
        TextLayout tl;
        if(this.currentNotation.equals("\uD834\uDD2A")) {
            tl = new TextLayout(this.currentNotation, fontDS, frc);
            y = y + 28;
        } else if (this.currentNotation.equals("\uD834\uDD2B")){
            tl = new TextLayout(this.currentNotation, font, frc);
            x = x - 10;
        } else {
            tl = new TextLayout(this.currentNotation, font, frc);
        }
        tl.draw(g2, x - 20, y + 15);
    }

    public void generateNextNote(){
        // first note == clef note
        if(!isFirst) {
            if (MusicSheetGraphics.level > 5 && Math.random() < .2)
                this.clefDrawing.changeClef();
            else if (MusicSheetGraphics.level > 5 && Math.random() < .33)
                this.changeOctave();

            //lower eventual F clef octave
            if(this.clefDrawing.getClefName().equals("F") && this.currentOctave > 4)
                this.currentOctave = 4;

            //maximum distance for the next note
            int position = -1, start, end;
            for (int i = 0; i < notes.length; i++) {
                if (notes[i].equals(this.currentNote)) {
                    position = i;
                    break;
                }
            }
            start = Math.max(position - MusicSheetGraphics.level, 0);
            end = Math.min(position + MusicSheetGraphics.level + 1, notes.length);

            //avoid the same note back to back
            String oldNote = this.currentNote;
            while(oldNote.equals(this.currentNote))
                this.currentNote = notes[new Random().nextInt(end - start) + start];

            // set the notation
            this.currentNotation = "";
            this.currentNotationNumber = 0;
            if (maxNotationNumber > 0) {
                double rand = Math.random();
                this.currentNotationNumber = 0;
                if (rand < .2) {
                    this.currentNotation = notations[new Random().nextInt(2 * maxNotationNumber)];
                }
                checkValidity();
            }
        } else {
            isFirst = false;
        }
        if(isFirstRound && this.getClefDrawing().getClefName().equals("F"))
            this.currentOctave = 3;
        this.isFirstRound = false;
        NoteTimer.startTimer();
    }

    private void changeOctave(){
        if(this.clefDrawing.getClefName().equals("F")){
            this.currentOctave = new Random().nextInt(3) + 2;
        } else {
            this.currentOctave = new Random().nextInt(3) + 3;
        }
    }

    //check if the notation is valid (no over/under scale)
    private void checkValidity(){
        // "\u266F", "\u266D", "\uD834\uDD2A", "\uD834\uDD2B"
        switch (this.currentNotation){
            case "\u266F":
            case "\uD834\uDD2A": //ss
                if(this.currentNote.equals("B"))
                    this.currentNotation = "";
                break;
            case "\u266D":
            case "\uD834\uDD2B": //bb
                if(this.currentNote.equals("C"))
                    this.currentNotation = "";
                break;
        }
    }

    public void setCurrentNote(NoteDrawing nextNote){
        this.clefDrawing.setClefName(nextNote.getClefDrawing().getClefName());
        this.clefDrawing.setClefType(nextNote.getClefDrawing().getClefType());
        this.currentOctave = nextNote.getCurrentOctave();
        this.currentNote = nextNote.getCurrentNote();
        this.currentNotation = nextNote.getCurrentNotation();
        this.currentNotationNumber = nextNote.getCurrentNotationNumber();
    }

    public String getCurrentNote(){
        return this.currentNote;
    }

    public String getCurrentNotation(){
        return this.currentNotation;
    }

    public int getCurrentNotationNumber(){
        return this.currentNotationNumber;
    }

    private int getNotePosition(String name){
        for (int i=0; i < notes.length; i++) {
            if (notes[i].equals(name))
                return i;
        }
        return -1;
    }

    public String getStringName(){
        switch (this.currentNotation){
            case "\u266F":
                return jmusicNotations.get(this.currentNote + "#");
            case "\u266D":
                return jmusicNotations.get(this.currentNote + "b");
            case "\uD834\uDD2A": //x
                //special case E##
                if(this.currentNote.equals("E")){
                    return "F#";
                }
                return notes[getNotePosition(this.currentNote) + 1];
            case "\uD834\uDD2B": //bb
                //special case Fbb
                if(this.currentNote.equals("F")){
                    return "Eb";
                }
                return notes[getNotePosition(this.currentNote) - 1];
            default:
                return this.currentNote;
        }
    }

    public int getCurrentOctave(){
        return this.currentOctave;
    }

    public void play(Note n){
        double v = (this.currentOctave < 4 ? .5 : 2.);
        double freqMultiplier = 1;
        for (int i = 0; i<Math.abs(4 - this.currentOctave); i++){
            freqMultiplier *= v;
        }
        if(freqMultiplier != 0)
            n.setFrequency(n.getFrequency() * freqMultiplier);
        Play.midi(n);
    }

    public ClefDrawing getClefDrawing() {
        return this.clefDrawing;
    }

    public boolean hasNotations(){
        return this.currentNotationNumber > 0;
    }
}
