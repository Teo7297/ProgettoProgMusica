package Game;

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
        this.sheetPanel = new MusicSheetGraphics(sclef[0], sclef[1].toLowerCase(), level, false, this);
        this.add(this.sheetPanel);

        KeyboardPanel keyboardPanel = new KeyboardPanel();
        setListeners(keyboardPanel);
        assignKeyBinds(keyboardPanel);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this.ked);
        this.add(keyboardPanel);

        this.currentNote = ((MusicSheetGraphics)sheetPanel).getCurrentNote();

        this.username = username;
        this.time = time;
        this.level = level;
        this.clef = clef;

        gameTimer = new Timer(time * 1000, this);
        gameTimer.setRepeats(false);
        gameTimer.start();
    }

    public void removeKL(){
        KeyboardFocusManager.getCurrentKeyboardFocusManager();
        KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(this.ked);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("GAME OVER");
        Score score = new Score(((MusicSheetGraphics)this.sheetPanel).getCurrentScore(), username, time, level);
        removeKL();
        int ans = 1;
        if(this.level < 10)
            ans = JOptionPane.showOptionDialog(SwingUtilities.getRoot(this),
                    "Score: "+score.getPoints()+"!\nContinue to next level?",
                    "TIME'S UP",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new String[]{"Si", "No"},
                    null);
        else
            JOptionPane.showMessageDialog(SwingUtilities.getRoot(this), "Congratulations, you completed the highest available level!");
        if(ans == 0){
            //go to next level
            ((MainFrame)SwingUtilities.getRoot(this)).registerScore(score);
            ((MainFrame)SwingUtilities.getRoot(this)).loadGame(this.username, this.level+1, this.time, this.clef);
        } else
            ((MainFrame)SwingUtilities.getRoot(this)).loadMainMenu(score);

        this.removeAll();
    }

    private void assignKeyBinds(KeyboardPanel keyboard){
        this.ked = new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
                synchronized (this) {
                    if (ke.getID() == KeyEvent.KEY_PRESSED) {
                        switch (ke.getKeyCode()){
                            case KeyEvent.VK_A:
                                keyboard.C_Key.doClick();
                                break;
                            case KeyEvent.VK_S:
                                keyboard.D_Key.doClick();
                                break;
                            case KeyEvent.VK_D:
                                keyboard.E_Key.doClick();
                                break;
                            case KeyEvent.VK_F:
                                keyboard.F_Key.doClick();
                                break;
                            case KeyEvent.VK_G:
                                keyboard.G_Key.doClick();
                                break;
                            case KeyEvent.VK_H:
                                keyboard.A_Key.doClick();
                                break;
                            case KeyEvent.VK_J:
                                keyboard.B_Key.doClick();
                                break;
                            case KeyEvent.VK_W:
                                keyboard.Cs_Key.doClick();
                                break;
                            case KeyEvent.VK_E:
                                keyboard.Ds_Key.doClick();
                                break;
                            case KeyEvent.VK_T:
                                keyboard.Fs_Key.doClick();
                                break;
                            case KeyEvent.VK_Y:
                                keyboard.Gs_Key.doClick();
                                break;
                            case KeyEvent.VK_U:
                                keyboard.As_Key.doClick();
                                break;
                            default:
                        }

                    }
                    return false;
                }
            }
        };
    }

    private void setListeners(KeyboardPanel k){
        k.C_Key.addActionListener(e -> {
            Note n = new Note(C4 ,QUARTER_NOTE);
            currentNote.play(n);
            ((MusicSheetGraphics) sheetPanel).evaluatePlayerInput(n.getName());
        });
        k.D_Key.addActionListener(e -> {
            Note n = new Note(D4 ,QUARTER_NOTE);
            currentNote.play(n);
            ((MusicSheetGraphics) sheetPanel).evaluatePlayerInput(n.getName());
        });
        k.E_Key.addActionListener(e -> {
            Note n = new Note(E4 ,QUARTER_NOTE);
            currentNote.play(n);
            ((MusicSheetGraphics) sheetPanel).evaluatePlayerInput(n.getName());
        });
        k.F_Key.addActionListener(e -> {
            Note n = new Note(F4 ,QUARTER_NOTE);
            currentNote.play(n);
            ((MusicSheetGraphics) sheetPanel).evaluatePlayerInput(n.getName());
        });
        k.G_Key.addActionListener(e -> {
            Note n = new Note(G4 ,QUARTER_NOTE);
            currentNote.play(n);
            ((MusicSheetGraphics) sheetPanel).evaluatePlayerInput(n.getName());
        });
        k.A_Key.addActionListener(e -> {
            Note n = new Note(A4 ,QUARTER_NOTE);
            currentNote.play(n);
            ((MusicSheetGraphics) sheetPanel).evaluatePlayerInput(n.getName());
        });
        k.B_Key.addActionListener(e -> {
            Note n = new Note(B4 ,QUARTER_NOTE);
            currentNote.play(n);
            ((MusicSheetGraphics) sheetPanel).evaluatePlayerInput(n.getName());
        });
        k.Cs_Key.addActionListener(e -> {
            Note n = new Note(CS4 ,QUARTER_NOTE);
            currentNote.play(n);
            ((MusicSheetGraphics) sheetPanel).evaluatePlayerInput(n.getName());
        });
        k.Ds_Key.addActionListener(e -> {
            Note n = new Note(DS4 ,QUARTER_NOTE);
            currentNote.play(n);
            ((MusicSheetGraphics) sheetPanel).evaluatePlayerInput(n.getName());
        });
        k.Fs_Key.addActionListener(e -> {
            Note n = new Note(FS4 ,QUARTER_NOTE);
            currentNote.play(n);
            ((MusicSheetGraphics) sheetPanel).evaluatePlayerInput(n.getName());
        });
        k.Gs_Key.addActionListener(e -> {
            Note n = new Note(GS4 ,QUARTER_NOTE);
            currentNote.play(n);
            ((MusicSheetGraphics) sheetPanel).evaluatePlayerInput(n.getName());
        });
        k.As_Key.addActionListener(e -> {
            Note n = new Note(AS4 ,QUARTER_NOTE);
            currentNote.play(n);
            ((MusicSheetGraphics) sheetPanel).evaluatePlayerInput(n.getName());
        });
    }
}
