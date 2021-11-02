package keyboard;

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
    private static final String[] notes = new String[]{"C","D","E","F","G","A","B"};
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
    private final int position;
    private final int x;
    private final int extraStart;
    private final int extraEnd;
    private String currentNote;
    private final int clefDelta;
    private static final HashMap<String, String> ClefToNoteMap = new HashMap<String, String>(){{
        put("chiavedo","C");
        put("chiavefa","F");
        put("chiavesol","G");
    }};
    private static final HashMap<String, Integer> CMap = new HashMap<String, Integer>(){{
        //y needed to place clef correctly on screen
        put("chiavesoltreble", 113);
        //put("french_violin", 130);
        put("chiavedosoprano", 102);
        put("chiavedomezzo_soprano", 120);
        put("chiavedoalto", 138);
        put("chiavedotenor", 157);
        //put("baritone_C", 175);
        put("chiavefabaritone", 156);
        put("chiavefabass", 138);
        //put("subbass", 120);

        //deltas for note height (+9 = 1 nota)
        put("trebleDelta", 18);
        //put("french_violinDelta", );
        put("sopranoDelta", 36);
        put("mezzo_sopranoDelta", 54);
        put("altoDelta", 72);
        put("tenorDelta", 90);
        //put("baritone_CDelta", );
        put("baritoneDelta", 45); //108 4 ottava
        put("bassDelta", 63); //126 4 ottava
        //put("subbassDelta", );
    }};
    private static int maxNotationNumber;
    private int currentNotationNumber;
    private String currentNotation;
    private static final Font font =  new Font("Arimo", Font.PLAIN, 36);
    private static final Font fontDS =  new Font("Arimo", Font.PLAIN, 60);


    public NoteDrawing(int position, int x, int extraStart, int extraEnd, String clefType, String clefName){
        this.clefDelta = CMap.get(clefType+"Delta");
        this.position = position;
        this.x = x;
        this.extraStart = extraStart;
        this.extraEnd = extraEnd;
        this.currentNote = ClefToNoteMap.get(clefName);
        maxNotationNumber = 2;
        if(MusicSheetGraphics.level < 5){
            maxNotationNumber = 0;
        } else if(MusicSheetGraphics.level < 9){
            maxNotationNumber = 1;
        }
    }

    public void drawNote(Graphics2D g2){
        int y = notesHeights.get(currentNote)-clefDelta;
        drawExtraLines(y, this.extraStart, this.extraEnd, g2);
        Ellipse2D circle = new Ellipse2D.Double(x,y,18,16);
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
        int position = -1, start = 0, end = 0;
        for (int i=0; i<notes.length; i++){
            if (notes[i].equals(this.currentNote)){
                position = i;
                break;
            }
        }
        start = Math.max(position - MusicSheetGraphics.level, 0);
        end = Math.min(position + MusicSheetGraphics.level + 1, notes.length);
        this.currentNote = notes[new Random().nextInt(end - start) + start];
        //this.currentNote = "C";

        this.currentNotation = "";
        this.currentNotationNumber = 0;
        if (maxNotationNumber > 0){
            double rand = Math.random();
            this.currentNotationNumber = 0;
            if(rand < .2){
                this.currentNotation = notations[new Random().nextInt(2*maxNotationNumber)];
            }
        }
        checkValidity();
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

    public void setCurrentNote(String currentNote, String notation, int notationNumber){
        this.currentNote = currentNote;
        this.currentNotation = notation;
        this.currentNotationNumber = notationNumber;
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

    public String getName(){
        //TODO probabilmente conviene fare una mappa con i diesis/bemolle supportati da sta merda di jmusic
        switch (this.currentNotation){
            case "\u266F":
                return this.currentNote + "#";
            case "\u266D":
                return this.currentNote + "b";
            case "\uD834\uDD2A": //ss
                return notes[getNotePosition(this.currentNote)+2];
            case "\uD834\uDD2B": //bb
                return notes[getNotePosition(this.currentNote)-2];
            default:
                return "";
        }
    }

    /*//-1, 0, 1
    public int changeOctaveDelta(){
        switch (octaveDeltas[new Random().nextInt(3)]){
            case 0:
                if(this.position == 0)
                    octaveDeltaSound = 1.;
                return 0;
            case -1:
                if(this.position == 0)
                    octaveDeltaSound = 0.5;
                return 63;
            case 1:
                if(this.position == 0)
                    octaveDeltaSound = 2.;
                return  -63;
            default:
                System.err.println("Given delta value not supported");
                return 0;
        }
    }*/
}
