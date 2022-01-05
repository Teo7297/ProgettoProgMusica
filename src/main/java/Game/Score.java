package Game;

public class Score implements Comparable{
    private int points;
    private String username;
    private int time;
    private int level;

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
    public int compareTo(Object o) {
        return this.getPoints() - ((Score)o).getPoints();
    }

    @Override
    public String toString(){
        return this.getUsername() + ": " + this.points;
    }
}
