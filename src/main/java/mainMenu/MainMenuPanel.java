package mainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel implements ActionListener {
    public MainMenuPanel(){
        super();
        this.setPreferredSize(new Dimension(720, 300));
        this.setLayout(new BorderLayout());
        this.add(new JLabel("Version 1.0 Septiclavio Learning Serious Game"), BorderLayout.PAGE_START);
        this.add(new JLabel("Version 1.0 Septiclavio Learning Serious Game"), BorderLayout.PAGE_END);

        //scrollable panel for best scores
        JList<String> scoresList = new JList<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
        scoresList.setFont(new Font("Arimo", Font.PLAIN, 36));
        JScrollPane scrollPane = new JScrollPane(scoresList);
        scrollPane.setPreferredSize(new Dimension(360, 300));
        this.add(scrollPane, BorderLayout.EAST);

        //input form
        this.add(new Form(), BorderLayout.WEST);
    }

    //remember to repaint()
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}