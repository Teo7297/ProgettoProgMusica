package keyboard;

import jm.music.data.*;
import jm.JMC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static java.awt.EventQueue.invokeLater;


public class Keyboard implements JMC{
    private JPanel keyboardPanel;
    private JButton B_Key;
    private JButton C_Key;
    private JButton D_Key;
    private JButton E_Key;
    private JButton F_Key;
    private JButton G_Key;
    private JButton A_Key;
    private JButton Cs_Key;
    private JPanel externalPanel;
    private JButton Ds_Key;
    private JButton Fs_Key;
    private JButton As_Key;
    private JButton Gs_Key;
    private JPanel sheetPanel;
    private NotePlay notePlay;

    public Keyboard() {
        assignKeyBinds();
    }

    public static void main(String[] args) {
        invokeLater(Keyboard::initFrame);
    }

    private static void initFrame(){
        //------------------
        // frame settings
        // size: (610 x 700)
        //------------------
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Cannot find installed look and feels to apply");
        }
        JFrame frame = new JFrame("Keyboard");
        frame.setContentPane(new Keyboard().externalPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocation((int)(size.getWidth()/2 - frame.getWidth()/2), (int)(size.getHeight()/2 - frame.getHeight()/2)); //must be after pack()
        frame.setVisible(true);
    }

    // TODO: set onRelease events, evitare che tenere premuto accodi 10000 note (see https://stackoverflow.com/questions/28843656/calling-a-java-abstractaction-from-a-button-mouse-release)
    // TODO: vedi dove mettere il doClick dei bottoni per l'animazione di click
    private void assignKeyBinds(){
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
                synchronized (this) {
                    if (ke.getID() == KeyEvent.KEY_PRESSED) {
                            switch (ke.getKeyCode()){
                                case KeyEvent.VK_A:
                                    notePlay.execute(new Note(C4 ,QUARTER_NOTE));
                                    break;
                                case KeyEvent.VK_S:
                                    notePlay.execute(new Note(D4,  QUARTER_NOTE));
                                    break;
                                case KeyEvent.VK_D:
                                    notePlay.execute(new Note(E4,  QUARTER_NOTE));
                                    break;
                                case KeyEvent.VK_F:
                                    notePlay.execute(new Note(F4,  QUARTER_NOTE));
                                    break;
                                case KeyEvent.VK_G:
                                    notePlay.execute(new Note(G4,  QUARTER_NOTE));
                                    break;
                                case KeyEvent.VK_H:
                                    notePlay.execute(new Note(A4,  QUARTER_NOTE));
                                    break;
                                case KeyEvent.VK_J:
                                    notePlay.execute(new Note(B4,  QUARTER_NOTE));
                                    break;
                                case KeyEvent.VK_W:
                                    notePlay.execute(new Note(CS4, QUARTER_NOTE));
                                    break;
                                case KeyEvent.VK_E:
                                    notePlay.execute(new Note(DS4, QUARTER_NOTE));
                                    break;
                                case KeyEvent.VK_T:
                                    notePlay.execute(new Note(FS4, QUARTER_NOTE));
                                    break;
                                case KeyEvent.VK_Y:
                                    notePlay.execute(new Note(GS4, QUARTER_NOTE));
                                    break;
                                case KeyEvent.VK_U:
                                    notePlay.execute(new Note(AS4, QUARTER_NOTE));
                                    break;
                            }
                    }
                    return false;
                }
            }
        });
    }

    private void createUIComponents() {
        sheetPanel = new MusicSheetGraphics("chiavefa", "baritone", 10, true);
        notePlay = new NotePlay((MusicSheetGraphics)sheetPanel);
    }
}
