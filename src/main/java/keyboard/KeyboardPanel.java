package keyboard;

import jm.music.data.Note;
import jm.util.Play;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KeyboardPanel extends JPanel {
    public JButton C_Key;
    public JButton D_Key;
    public JButton E_Key;
    public JButton F_Key;
    public JButton G_Key;
    public JButton A_Key;
    public JButton B_Key;
    public JButton Cs_Key;
    public JButton Ds_Key;
    public JButton Fs_Key;
    public JButton Gs_Key;
    public JButton As_Key;

    private JLayeredPane jLayeredPane;
    private Font keyFont;
    private Font sKeyFont;

    public KeyboardPanel(){
        super();

        this.jLayeredPane = new JLayeredPane();
        this.jLayeredPane.setPreferredSize(new Dimension(700,350));
        this.jLayeredPane.setBackground(Color.white);

        this.keyFont = new Font("Tahoma", Font.BOLD, 36 );
        this.sKeyFont = new Font("Tahoma", Font.BOLD, 16 );
        drawKeys();
        this.add(this.jLayeredPane);
    }

    private void drawKeys(){
        C_Key = new JButton();
        D_Key = new JButton();
        E_Key = new JButton();
        F_Key = new JButton();
        G_Key = new JButton();
        A_Key = new JButton();
        B_Key = new JButton();
        Cs_Key = new JButton();
        Ds_Key = new JButton();
        Fs_Key = new JButton();
        Gs_Key = new JButton();
        As_Key = new JButton();

        setMainKey(C_Key, "C", 0);
        setMainKey(D_Key, "D", 100);
        setMainKey(E_Key, "E", 200);
        setMainKey(F_Key, "F", 300);
        setMainKey(G_Key, "G", 400);
        setMainKey(A_Key, "A", 500);
        setMainKey(B_Key, "B", 600);

        setSecondaryKey(Cs_Key, "C#", 70);
        setSecondaryKey(Ds_Key, "D#", 170);
        setSecondaryKey(Fs_Key, "F#", 370);
        setSecondaryKey(Gs_Key, "G#", 470);
        setSecondaryKey(As_Key, "A#", 570);


    }

    private void setMainKey(JButton k, String text, int x){
        k.setText(text);
        k.setVerticalAlignment(SwingConstants.BOTTOM);
        k.setBackground(Color.white);
        k.setBounds(x,0,100,320);
        k.setFocusable(false);
        k.setFont(this.keyFont);

        this.jLayeredPane.add(k, JLayeredPane.DEFAULT_LAYER);
    }

    private void setSecondaryKey(JButton k, String text, int x){
        k.setText(text);
        k.setVerticalAlignment(SwingConstants.BOTTOM);
        k.setBackground(Color.black);
        k.setBounds(x,0,60,200);
        k.setFocusable(false);
        k.setVisible(true);
        k.setFont(this.sKeyFont);
        k.setForeground(Color.white);
        this.jLayeredPane.add(k, JLayeredPane.POPUP_LAYER);
    }
}


