package keyboard;

import jm.music.data.*;
import jm.util.Play;
import jm.JMC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

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

    public Keyboard() {

        C_Key.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A,0), "C_Note");
        C_Key.getActionMap().put("C_Note", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("C");
                Play.midi(new Note(C4, WHOLE_NOTE));
                /*int[] chord = {F2,F4};
                CPhrase ph = new CPhrase(0.0);
                ph.addChord(chord, 1.);
                Play.midi(new Part(ph));*/
            }
        });
        D_Key.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S,0), "D_Note");
        D_Key.getActionMap().put("D_Note", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("D");
                Play.midi(new Note(D4, WHOLE_NOTE));
            }
        });
        E_Key.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D,0), "E_Note");
        E_Key.getActionMap().put("E_Note", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("E");
                Play.midi(new Note(E4, WHOLE_NOTE));
            }
        });
        F_Key.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F,0), "F_Note");
        F_Key.getActionMap().put("F_Note", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("F");
                Play.midi(new Note(F4, WHOLE_NOTE));
            }
        });
        G_Key.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_G,0), "G_Note");
        G_Key.getActionMap().put("G_Note", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("G");
                Play.midi(new Note(G4, WHOLE_NOTE));
            }
        });
        A_Key.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_H,0), "A_Note");
        A_Key.getActionMap().put("A_Note", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("A");
                Play.midi(new Note(A4, WHOLE_NOTE));
            }
        });
        B_Key.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_J,0), "B_Note");
        B_Key.getActionMap().put("B_Note", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("B");
                Play.midi(new Note(B4, WHOLE_NOTE));
            }
        });
        Cs_Key.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W,0), "Cs_Note");
        Cs_Key.getActionMap().put("Cs_Note", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("C#");
                Play.midi(new Note(CS4, WHOLE_NOTE));
            }
        });
        Ds_Key.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_E,0), "Ds_Note");
        Ds_Key.getActionMap().put("Ds_Note", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("D#");
                Play.midi(new Note(DS4, WHOLE_NOTE));
            }
        });
        Fs_Key.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_T,0), "Fs_Note");
        Fs_Key.getActionMap().put("Fs_Note", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("F#");
                Play.midi(new Note(FS4, WHOLE_NOTE));
            }
        });
        Gs_Key.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Y,0), "Gs_Note");
        Gs_Key.getActionMap().put("Gs_Note", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("G#");
                Play.midi(new Note(GS4, WHOLE_NOTE));
            }
        });
        As_Key.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_U,0), "As_Note");
        As_Key.getActionMap().put("As_Note", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("A#");
                Play.midi(new Note(AS4, WHOLE_NOTE));
            }
        });
    }

    public static void main(String[] args) {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
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
}
