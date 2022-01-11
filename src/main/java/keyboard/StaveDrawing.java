package keyboard;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class StaveDrawing {

    /**
     * This class is responsible for the handling of the stave during the match.
     */
    public StaveDrawing(){}
    public void drawStave(Graphics2D g2){
        Line2D line1 = new Line2D.Double(new Point2D.Double(15, 143), new Point2D.Double(2000,143));
        Line2D line2 = new Line2D.Double(new Point2D.Double(15, 161), new Point2D.Double(2000,161));
        Line2D line3 = new Line2D.Double(new Point2D.Double(15, 179), new Point2D.Double(2000,179));
        Line2D line4 = new Line2D.Double(new Point2D.Double(15, 197), new Point2D.Double(2000,197));
        Line2D line5 = new Line2D.Double(new Point2D.Double(15, 215), new Point2D.Double(2000,215));
        Line2D lineV = new Line2D.Double(new Point2D.Double(15, 143), new Point2D.Double(15,215));
        g2.draw(line1);
        g2.draw(line2);
        g2.draw(line3);
        g2.draw(line4);
        g2.draw(line5);
        g2.draw(lineV);
    }
}
