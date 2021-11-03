package keyboard;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class ClefDrawing {
    private static final HashMap<String, Integer> CMap = new HashMap<String, Integer>(){{
        //y needed to place clef correctly on screen
        put("treble", 113);
        put("soprano", 174);
        put("mezzo_soprano", 157);
        put("alto", 138);
        put("tenor", 120);
        put("baritone", 156);
        put("bass", 138);
    }};
    private static final String[] clefNames = new String[]{
            "chiavesol treble",
            "chiavedo soprano",
            "chiavedo mezzo_soprano",
            "chiavedo alto",
            "chiavedo tenor",
            "chiavefa baritone",
            "chiavefa bass"
    };
    private String clefName;
    private String clefType;
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
        g2.drawImage(scaled, 25, CMap.get(clefType), this.msg);
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
        String[] newClef = clefNames[new Random().nextInt(clefNames.length)].split(" ");
        this.clefName = newClef[0];
        this.clefType = newClef[1];
    }
}
