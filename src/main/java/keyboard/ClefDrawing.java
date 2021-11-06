package keyboard;

import java.awt.*;
import java.util.HashMap;
import java.util.Random;

public class ClefDrawing {
    private static final HashMap<String, Integer> clefNamesHeights = new HashMap<String, Integer>(){{
        //y needed to place clef correctly on screen
        put("G treble", 113);
        put("C soprano", 174);
        put("C mezzo-soprano", 157);
        put("C alto", 138);
        put("C tenor", 120);
        put("F baritone", 156);
        put("F bass", 138);
    }};
    private final Image[] clefsImages;
    private String clefName;
    private String clefType;
    private final MusicSheetGraphics msg;


    public ClefDrawing(String clefName, String clefType, MusicSheetGraphics msg, Image[] clefsImages){
        this.clefsImages = clefsImages;
        this.clefName = clefName;
        this.clefType = clefType;
        this.msg = msg;
    }

    public void drawClef(Graphics2D g2){
        int clefIndex = -1;
        switch (clefName){
            case "G":
                clefIndex = 1;
                break;
            case "C":
                clefIndex = 0;
                break;
            case "F":
                clefIndex = 2;
                break;
            default:
                System.err.println("Error drawing clef!");
        }
        g2.drawImage(clefsImages[clefIndex], 25, clefNamesHeights.get(this.clefName+" "+this.clefType), this.msg);
    }

    public String getClefName() {
        return this.clefName;
    }

    public String getClefType() {
        return this.clefType;
    }

    public void setClefName(String clefName) {
        this.clefName = clefName;
    }

    public void setClefType(String clefType) {
        this.clefType = clefType;
    }

    public void changeClef(){
        Object[] clefs = clefNamesHeights.keySet().toArray();
        String[] newClef = ((String) clefs[new Random().nextInt(clefNamesHeights.size())]).split(" ");
        this.clefName = newClef[0];
        this.clefType = newClef[1];
    }
}
