package keyboard;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ClefDrawing {
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

        //deltas for note height
        put("trebleDelta", 18);
        //put("french_violinDelta", );
        put("sopranoDelta", 36);
        put("mezzo_sopranoDelta", 54);
        put("altoDelta", 72);
        put("tenorDelta", 90);
        //put("baritone_CDelta", );
        put("baritoneDelta", 108);
        put("bassDelta", 126);
        //put("subbassDelta", );
    }};

    private final String clefName;
    private final String clefType;
    private final MusicSheetGraphics msg;

    public ClefDrawing(String clefName, String clefType, MusicSheetGraphics msg){
        this.clefName = clefName;
        this.clefType = clefType;
        this.msg = msg;
    }

    public void drawClef(Graphics2D g2){
        int scaleX = 1, scaleY = 1;
        switch (clefName){
            case "chiavesol":
                scaleX = 55;
                scaleY = 135;
                break;
            case "chiavedo":
                scaleX = 70;
                scaleY = 82;
                break;
            case "chiavefa":
                scaleX = 55;
                scaleY = 67;
                break;
            default:
                System.err.println("Badly typed clef img file name, please insert the name of the PNG file without the extension (.png)");
        }
        BufferedImage clef = null;
        try {
            clef = ImageIO.read(new File("src/main/img/"+clefName+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image scaled = null;
        if (clef != null) {
            scaled = clef.getScaledInstance(scaleX,scaleY, Image.SCALE_SMOOTH);
        } else{
            System.err.println("Clef BufferedImage is null! Please check that the file name and path are correct");
        }
        g2.drawImage(scaled, 25, CMap.get(clefName+clefType), this.msg);
    }
}
