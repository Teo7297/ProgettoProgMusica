package Game;

import javafx.event.EventDispatcher;
import jm.music.data.Note;
import keyboard.*;
import jm.JMC;
import mainMenu.MainFrame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class GamePanel extends JPanel implements JMC, ActionListener {

    private final NoteDrawing currentNote;
    private final JPanel sheetPanel;

    private final String username;
    private final int time;
    private final int level;
    private final String clef;
    private KeyEventDispatcher ked;

    public static Timer gameTimer;

    public GamePanel(String username, int level, int time, String clef){
        super(new GridLayout(2,1));
        this.setPreferredSize(new Dimension(700,700));

        String[] sclef = clef.split(" - ");
        this.sheetPanel = new MusicSheetGraphics(sclef[0], sclef[1].toLowerCase(), level, false);
        this.add(this.sheetPanel);

        KeyboardPanel keyboardPanel = new KeyboardPanel();
        this.add(keyboardPanel);

        assignKeyBinds(keyboardPanel);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this.ked);

        this.currentNote = ((MusicSheetGraphics)sheetPanel).getCurrentNote();

        this.username = username;
        this.time = time;
        this.level = level;
        this.clef = clef;

        gameTimer = new Timer(time * 1000, this);
        gameTimer.setRepeats(false);
        gameTimer.start();
    }

    private void assignKeyBinds(KeyboardPanel keyboard){
        this.ked = new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
                synchronized (this) {
                    if (ke.getID() == KeyEvent.KEY_PRESSED) {
                        Note n;
                        switch (ke.getKeyCode()){
                            case KeyEvent.VK_A:
                                n = new Note(C4 ,QUARTER_NOTE);
                                keyboard.C_Key.doClick();
                                break;
                            case KeyEvent.VK_S:
                                n = new Note(D4 ,QUARTER_NOTE);
                                keyboard.D_Key.doClick();
                                break;
                            case KeyEvent.VK_D:
                                n = new Note(E4 ,QUARTER_NOTE);
                                keyboard.E_Key.doClick();
                                break;
                            case KeyEvent.VK_F:
                                n = new Note(F4 ,QUARTER_NOTE);
                                keyboard.F_Key.doClick();
                                break;
                            case KeyEvent.VK_G:
                                n = new Note(G4 ,QUARTER_NOTE);
                                keyboard.G_Key.doClick();
                                break;
                            case KeyEvent.VK_H:
                                n = new Note(A4 ,QUARTER_NOTE);
                                keyboard.A_Key.doClick();
                                break;
                            case KeyEvent.VK_J:
                                n = new Note(B4 ,QUARTER_NOTE);
                                keyboard.B_Key.doClick();
                                break;
                            case KeyEvent.VK_W:
                                n = new Note(CS4 ,QUARTER_NOTE);
                                keyboard.Cs_Key.doClick();
                                break;
                            case KeyEvent.VK_E:
                                n = new Note(DS4 ,QUARTER_NOTE);
                                keyboard.Ds_Key.doClick();
                                break;
                            case KeyEvent.VK_T:
                                n = new Note(FS4 ,QUARTER_NOTE);
                                keyboard.Fs_Key.doClick();
                                break;
                            case KeyEvent.VK_Y:
                                n = new Note(GS4 ,QUARTER_NOTE);
                                keyboard.Gs_Key.doClick();
                                break;
                            case KeyEvent.VK_U:
                                n = new Note(AS4 ,QUARTER_NOTE);
                                keyboard.As_Key.doClick();
                                break;
                            default:
                                n = null;
                        }
                        if(n != null) {
                            currentNote.play(n);
                            ((MusicSheetGraphics) sheetPanel).evaluatePlayerInput(n.getName());
                        }
                    }
                    return false;
                }
            }
        };
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("GAME OVER");
        Score score = new Score(((MusicSheetGraphics)this.sheetPanel).getCurrentScore(), username, time, level);
        KeyboardFocusManager.getCurrentKeyboardFocusManager();
        KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(this.ked);
        int ans = JOptionPane.showOptionDialog(SwingUtilities.getRoot(this), "Score: "+score.getPoints()+"!\nContinue to next level?","TIME'S UP", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Si", "No"},null);
        if(ans == 0){
            //go to next level
            ((MainFrame)SwingUtilities.getRoot(this)).registerScore(score);
            ((MainFrame)SwingUtilities.getRoot(this)).loadGame(this.username, this.level+1, this.time, this.clef);
        } else
            ((MainFrame)SwingUtilities.getRoot(this)).loadMainMenu(score);
    }
}
