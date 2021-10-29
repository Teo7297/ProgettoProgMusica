package keyboard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MusicSheetGraphics extends JPanel implements ActionListener {
    Timer t = new Timer(5, this);
    double x = 0, y = 100, velX = 2, velY = 0;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("src/main/img/spartito-removebg-preview.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //test moving circle
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(myPicture, 0, 0, this);
        Ellipse2D circle = new Ellipse2D.Double(x,y,40,40);
        g2.fill(circle);
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(x<0 || x > 570){
            velX = -velX;
        }
        if(y<0||y > 560){
            velY = -velY;
        }
        x += velX;
        y += velY;

        repaint();
    }
}
