package Game;

import jm.music.data.Note;
import keyboard.*;
import jm.music.data.*;
import jm.JMC;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Locale;


public class GamePanel extends JPanel implements JMC{

    private NoteDrawing currentNote;
    private JPanel sheetPanel;
    private JPanel keyboardPanel;

    private String username;
    private int time;

    public GamePanel(String username, int level, int time, String clef){
        super(new GridLayout(2,1));
        this.setPreferredSize(new Dimension(700,700));

        String[] sclef = clef.split(" - ");
        this.sheetPanel = new MusicSheetGraphics(sclef[0], sclef[1].toLowerCase(), level, false);
        this.add(this.sheetPanel);

        this.keyboardPanel = new KeyboardPanel();
        this.add(this.keyboardPanel);

        assignKeyBinds((KeyboardPanel) this.keyboardPanel);

        this.currentNote = ((MusicSheetGraphics)sheetPanel).getCurrentNote();
    }

    private void assignKeyBinds(KeyboardPanel keyboard){
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
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
        });
    }
}
