package Game;

/**
 * Class used for the player scores, implements Comparable to allow sorting of the leaderboard by points value
 */
public class Score implements Comparable<Score>{
    private final int points;
    private final String username;
    private final int time;
    private final int level;

    public Score(int points, String username, int time, int level) {
        this.points = points;
        this.username = username;
        this.time = time;
        this.level = level;
    }

    public int getPoints() {
        return points;
    }

    public String getUsername() {
        return username;
    }

    public int getTime() {
        return time;
    }

    public int getLevel(){
        return this.level;
    }

    @Override
    public int compareTo(Score o) {
        return this.getPoints() - o.getPoints();
    }

    @Override
    public String toString(){
        return this.getUsername() + ": " + this.points;
    }
}
