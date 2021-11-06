package mainMenu;

import javax.swing.*;
import java.awt.*;

public class Form extends JPanel {
    public Form(){
        super();
        this.setName("MenuForm");
        this.setLayout(new GridLayout(5,1));
        this.setPreferredSize(new Dimension(360, 300));

        JPanel usernamePanel = new JPanel(null);
        JPanel levelPanel = new JPanel(null);
        JPanel timePanel = new JPanel(null);
        JPanel clefPanel = new JPanel(null);
        JPanel playPanel = new JPanel(null);

        JLabel userLabel = new JLabel("Username", SwingConstants.RIGHT);
        userLabel.setBounds(10,10,150, 20);
        usernamePanel.add(userLabel,0);
        JTextField userTextField = new JTextField();
        userTextField.setBounds(170,10,150, 20);
        usernamePanel.add(userTextField, 1);

        JLabel levelLabel = new JLabel("Select level", SwingConstants.RIGHT);
        levelLabel.setBounds(10,10,150, 20);
        levelPanel.add(levelLabel,0);
        JComboBox<String> levelComboBox = new JComboBox<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
        levelComboBox.setBounds(170,10,150, 20);
        levelPanel.add(levelComboBox,1);

        JLabel timeLabel = new JLabel("Select time", SwingConstants.RIGHT);
        timeLabel.setBounds(10,10,150, 20);
        timePanel.add(timeLabel,0);
        JComboBox<String> timeComboBox = new JComboBox<>(new String[]{"10 seconds","20 seconds","30 seconds","40 seconds","50 seconds","60 seconds"});
        timeComboBox.setBounds(170,10,150, 20);
        timePanel.add(timeComboBox,1);

        JLabel clefLabel = new JLabel("Select initial clef", SwingConstants.RIGHT);
        clefLabel.setBounds(10,10,150, 20);
        clefPanel.add(clefLabel,0);
        JComboBox<String> clefComboBox = new JComboBox<>(new String[]{"G - Treble", "C - Soprano", "C - Mezzo-Soprano", "C - Alto", "C - Tenor", "F - Baritone", "F - Bass",});
        clefComboBox.setBounds(170,10,150, 20);
        clefPanel.add(clefComboBox,1);

        JButton play = new JButton("PLAY");
        play.setBounds(105,0,150, 50);
        play.setFocusable(false);
        play.addActionListener((event)->{
            boolean error = false;
            System.out.println("START GAME :)");
            String username = userTextField.getText();
            String level = (String) levelComboBox.getSelectedItem();
            String time = (String) timeComboBox.getSelectedItem();
            String clef = (String) clefComboBox.getSelectedItem();
            if(username.equals("")){
                System.err.println("Fill username field");
                error = true;
            }
            if(!error){
                MainFrame frame = (MainFrame) SwingUtilities.getRoot(this);
                frame.loadGame(username, Integer.parseInt(level), Integer.parseInt(time.substring(0,2)), clef);
            }
        });
        playPanel.add(play);

        this.add(usernamePanel);
        this.add(levelPanel);
        this.add(timePanel);
        this.add(clefPanel);
        this.add(playPanel);
    }
}
