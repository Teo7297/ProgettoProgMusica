package keyboard;

import Game.GamePanel;
import Game.NoteTimer;
import mainMenu.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MusicSheetGraphics extends JPanel implements ActionListener{

    private boolean correct;
    private boolean wrong;
    public static int level;
    private final NoteDrawing currentNote;
    private final NoteDrawing nextNote;
    private final NoteDrawing next2Note;
    private final NoteDrawing next3Note;
    private final StaveDrawing staveDrawing;
    private int errors;
    private int currentScore;
    private final JLabel scoreLabel;
    private final GamePanel gamePanel;

    private static boolean GODMODE;

    public MusicSheetGraphics(String clefName, String clefType, int level_, boolean godmode, GamePanel gamePanel){
        super(null);
        this.gamePanel = gamePanel;
        this.setPreferredSize(new Dimension(700,350));
        this.setBackground(Color.white);
        this.scoreLabel = new JLabel("Score: 0");
        this.scoreLabel.setBounds(0,0,100,30);
        this.add(this.scoreLabel);
        JButton homeButton = new JButton("Exit");
        homeButton.setBounds(600, 10, 80,30);
        homeButton.addActionListener((e)->{
            this.gamePanel.removeKL();
            ((MainFrame)SwingUtilities.getRoot(this)).loadMainMenu(null);
            this.gamePanel.removeAll();
        });
        this.add(homeButton);

        JLabel levelLabel = new JLabel("Level: " + level_);
        levelLabel.setBounds(100, 0, 80,30);
        this.add(levelLabel);

        GODMODE = godmode;
        level = level_;

        this.correct = false;
        this.wrong = false;
        this.errors = 0;
        this.currentScore = 0;

        this.staveDrawing = new StaveDrawing();

        Image[] clefImages = loadClefImages();

        this.currentNote = new NoteDrawing(120, 115, 142, new ClefDrawing(clefName, clefType, this, clefImages), false);
        this.nextNote = new NoteDrawing(240, 235, 262, new ClefDrawing(clefName, clefType, this, clefImages), false);
        this.next2Note = new NoteDrawing(360, 355, 382, new ClefDrawing(clefName, clefType, this, clefImages), false);
        this.next3Note = new NoteDrawing(480, 475, 502, new ClefDrawing(clefName, clefType, this, clefImages), true);

        NoteDrawing.setMaxNotationNumber();

        this.next3Note.generateNextNote();
        this.currentNote.setCurrentNote(this.next3Note);
        this.next3Note.generateNextNote();
        this.nextNote.setCurrentNote(this.next3Note);
        this.next3Note.generateNextNote();
        this.next2Note.setCurrentNote(this.next3Note);
        this.next3Note.generateNextNote();

    }

    private Image[] loadClefImages() {
        String[] names = new String[]{"chiavedo", "chiavesol", "chiavefa"};
        Image[] res = new Image[3];
        int scaleX = 1, scaleY = 1;
        for (int i=0; i<3; i++){
            switch (names[i]){
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
                clef = ImageIO.read(new File("img/"+names[i]+".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (clef != null) {
                res[i] = clef.getScaledInstance(scaleX,scaleY, Image.SCALE_SMOOTH);
            } else{
                System.err.println("Clef BufferedImage is null! Please check that the file name and path are correct");
            }
        }
        return res;
    }

    public void evaluatePlayerInput(String noteName){
        if ((this.currentNote.getStringName()).equals(noteName) || GODMODE) {
            this.correct = true;
            this.currentScore += (100 - (5*((this.errors * 2) + NoteTimer.getTimerSeconds()))) * level;   //score formula
            NoteTimer.restartTimer();
            this.errors = 0;
        } else {
            this.wrong = true;
            errors += 1;
        }
        repaint();
        Timer t = new Timer(100, this);
        t.setRepeats(false);
        t.start();
    }

    public int getCurrentScore(){
        return this.currentScore;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2.5f));
        g2.setPaint(Color.black);
        this.scoreLabel.setText("Score: " + this.currentScore);
        staveDrawing.drawStave(g2);
        this.currentNote.getClefDrawing().drawClef(g2);
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
            this.currentNote.setCurrentNote(nextNote);
            this.nextNote.setCurrentNote(next2Note);
            this.next2Note.setCurrentNote(next3Note);
            this.next3Note.generateNextNote();
        } else {
            this.wrong = false;
        }
        repaint();
    }
}