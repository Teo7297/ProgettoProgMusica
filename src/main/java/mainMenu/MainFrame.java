package mainMenu;

import Game.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.io.*;

import Game.Score;
import org.json.*;

import static javax.swing.SwingUtilities.invokeLater;

/**
 * This is the main class of this project, execute it to run the application.
 */

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

    /**
     * Load scores from JSON file, if not found create a new one
     */
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

    /**
     * Store the scores file
     */
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

    /**
     * Use the given parameters to load a new game (Panel) and start it
     * @param username
     * username of the player
     * @param level
     * selected level
     * @param time
     * selected play time
     * @param clef
     * selected clef
     */
    public void loadGame(String username, int level, int time, String clef){
        homeContainer.add(new GamePanel(username, level, time, clef), "Game",1);
        cardLayout.show(homeContainer, "Game");
        this.setSize(new Dimension(700,700));
    }

    /**
     * Leave the game match and load the main menu,
     * the score is registered only if the match has been completed (time's up).
     * @param score
     * Result score of the game
     */
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

    /**
     * Register the match score (in memory) and calls storeScoresJSON()
     * to store it in a file.
     * @param score
     * Result score of the game
     *
     */
    public void registerScore(Score score){
        boolean registered = false;
        JSONArray arr = scoresJson.getJSONArray("scores");
        for(int i=0; i<arr.length(); i++){
            JSONObject element = ((JSONObject) arr.get(i));
            if (element.getString("user").equals(score.getUsername())){
                if(element.getInt("points") > score.getPoints())
                    registered = true;
                else
                    arr.remove(i);
                break;
            }
        }
        if(!registered) {
            JSONObject o = new JSONObject();
            o.put("user", score.getUsername());
            o.put("points", score.getPoints());
            o.put("time", score.getTime());
            o.put("level", score.getLevel());
            arr.put(o);
            scoresJson.clear();
            scoresJson.put("scores", arr);
            storeScoresJSON();
            System.out.println(arr);
        }
        ((MainMenuPanel)this.homePanel).updateScores(true);
    }

    public static void main(String[] args) {
        invokeLater(MainFrame::new);
    }

}