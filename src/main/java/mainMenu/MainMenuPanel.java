package mainMenu;

import Game.Score;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

/**
 * This is the JPanel of the main menu of the game,
 * it contains the form for the player to fill with
 * the requested information and the highest scores tab.
 */
public class MainMenuPanel extends JPanel implements ActionListener {

    private String[] scores;
    private JList<String> scoresList;
    private JScrollPane scrollPane;
    private static final String CONFIG_FILE = "config.properties";

    public MainMenuPanel(){
        super();
        this.setPreferredSize(new Dimension(720, 300));
        this.setLayout(new BorderLayout());

        InputStream is = this.getClass().getClassLoader().getResourceAsStream(CONFIG_FILE);
        Properties p = new Properties();
        try {
            p.load(is);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Properties file not found, impossible to detect current version number");
        }
        this.add(new JLabel("Version " + p.getProperty("Version")), BorderLayout.PAGE_END);

        //scrollable panel for best scores
        updateScores(false);

        this.scoresList = new JList<>(this.scores);
        this.scoresList.setFont(new Font("Arimo", Font.PLAIN, 18));
        this.scrollPane = new JScrollPane(this.scoresList);
        this.scrollPane.setPreferredSize(new Dimension(360, 300));
        this.add(this.scrollPane, BorderLayout.EAST);


        //input form
        this.add(new Form(), BorderLayout.WEST);
    }

    /**
     * Update the high scores scrollable tab.
     * @param updateUI
     * Decide if a UI update is necessary (load eventual new high scores)
     *
     *
     */
    public void updateScores(boolean updateUI) {
        JSONArray arr = MainFrame.scoresJson.getJSONArray("scores");
        this.scores = new String[arr.length()];
        ArrayList<Score> scoresArr = new ArrayList<>(this.scores.length);

        for (int i=0; i< this.scores.length; i++){
            JSONObject o = (JSONObject) arr.get(i);
            scoresArr.add( new Score((int)o.get("points"), (String)o.get("user"), (int)o.get("time"), (int)o.get("level")) );
        }
        scoresArr.sort(Collections.reverseOrder());

        for(int i=0; i< this.scores.length; i++){
            this.scores[i] = (i+1) + ". " + scoresArr.get(i).toString();
        }
        if(updateUI)
            myUpdateUI();
    }

    private void myUpdateUI(){
        this.remove(this.scrollPane);
        this.scoresList = new JList<>(this.scores);
        this.scrollPane = new JScrollPane(this.scoresList);
        this.add(this.scrollPane);
    }
    //remember to repaint()
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}