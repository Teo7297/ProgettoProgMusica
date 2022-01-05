package Game;

import java.sql.Timestamp;

public class NoteTimer {
    private static Timestamp startTime;

    public static void startTimer(){
        startTime = new Timestamp(System.currentTimeMillis());
    }

    public static int getTimerSeconds(){
        return (int)((new Timestamp(System.currentTimeMillis()).getTime() / 1000) - (startTime.getTime() / 1000));
    }

    public static void restartTimer(){
        startTime.setTime(System.currentTimeMillis());
    }
}
