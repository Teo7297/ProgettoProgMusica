package mainMenu;

import Game.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.io.*;

import Game.Score;
import org.json.*;

import static javax.swing.SwingUtilities.invokeLater;

public class MainFrame extends JFrame {

    private static CardLayout cardLayout;
    private static JPanel homeContainer;
    private static final String SCORES_FILE_NAME = "scores.json";
    public static JSONObject scoresJson;
    private final JPanel homePanel;
    private File scoreFile;

    public MainFrame() {
        super("Septiclavio Learning Serious Game");
        this.setPreferredSize(new Dimension(720,340));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loadScoresJSON();

        cardLayout = new CardLayout();
        homeContainer = new JPanel(cardLayout);
        this.homePanel = new MainMenuPanel();
        homeContainer.add(homePanel, "Main Menu",0);
        this.add(homeContainer);

        this.setResizable(false);
        this.pack();
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((int)(size.getWidth()/2 - this.getWidth()/2), (int)(size.getHeight()/2 - this.getHeight()/2)); //must be after pack()
        this.setVisible(true);
    }

    private void loadScoresJSON() {
        try {
            this.scoreFile = new File(SCORES_FILE_NAME);
            if (this.scoreFile.createNewFile()) {
                scoresJson = new JSONObject();
                scoresJson.put("scores", new JSONArray());
                storeScoresJSON();
                System.out.println("New file created: " + this.scoreFile.getName());
            } else {
                System.out.println("Existing file found, reading...");
                BufferedReader br = new BufferedReader(new FileReader(this.scoreFile));
                scoresJson = new JSONObject(new JSONTokener(br));
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void storeScoresJSON() {
        try {
            FileWriter fw = new FileWriter(this.scoreFile);
            fw.write(scoresJson.toString());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGame(String username, int level, int time, String clef){
        homeContainer.add(new GamePanel(username, level, time, clef), "Game",1);
        cardLayout.show(homeContainer, "Game");
        this.setSize(new Dimension(700,700));
    }

    public void loadMainMenu(Score score){
        if(score == null)
            GamePanel.gameTimer.stop();
        else{
            //register score
            registerScore(score);
            System.out.println("user: " + score.getUsername());
            System.out.println("points: " + score.getPoints());
            System.out.println("time: " + score.getTime() + " seconds");
        }
        cardLayout.show(homeContainer, "Main Menu");
        this.setSize(new Dimension(720,340));
    }

    public void registerScore(Score score){
        JSONArray arr = scoresJson.getJSONArray("scores");
        JSONObject o = new JSONObject();
        o.put("user", score.getUsername());
        o.put("points", score.getPoints());
        o.put("time", score.getTime());
        o.put("level", score.getLevel());
        arr.put(o);
        scoresJson.clear();
        scoresJson.put("scores", arr);
        storeScoresJSON();

        ((MainMenuPanel)this.homePanel).updateScores(true);
    }

    public static void main(String[] args) {
        invokeLater(MainFrame::new);
    }

}