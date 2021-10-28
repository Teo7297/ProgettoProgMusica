package keyboard;

import javax.swing.*;
import java.awt.*;

public class Keyboard {
    private JPanel keyboardPanel;
    private JButton B;
    private JButton C;
    private JButton D;
    private JButton E;
    private JButton F;
    private JButton G;
    private JButton A;
    private JButton Cd;
    private JPanel externalPanel;

    public static void drawUI(){
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
