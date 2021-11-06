package mainMenu;

import Game.GamePanel;

import javax.swing.*;
import java.awt.*;

import static javax.swing.SwingUtilities.invokeLater;

public class MainFrame extends JFrame {

    private static CardLayout cardLayout;
    private static JPanel homeContainer;

    public MainFrame(){
        super("Septiclavio Learning Serious Game");
        this.setPreferredSize(new Dimension(720,340));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        homeContainer = new JPanel(cardLayout);
        JPanel homePanel = new MainMenuPanel();
        homeContainer.add(homePanel, "Main Menu");
        this.add(homeContainer);

        this.setResizable(false);
        this.pack();
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int)(size.getWidth()/2 - this.getWidth()/2), (int)(size.getHeight()/2 - this.getHeight()/2)); //must be after pack()
        this.setVisible(true);
    }

    public void loadGame(String username, int level, int time, String clef){

        homeContainer.add(new GamePanel(username, level, time, clef), "Game");
        cardLayout.show(homeContainer, "Game");
        this.setSize(new Dimension(700,700));
    }

    public void loadMainMenu(){
        cardLayout.show(homeContainer, "Main Menu");
    }

    public static void main(String[] args) {
        invokeLater(MainFrame::new);
    }

}