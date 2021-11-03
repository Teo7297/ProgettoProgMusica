import jm.music.data.*;
import jm.JMC;
import jm.util.Play;

import java.util.Random;

public class Test implements JMC{
    public static void main(String[] args) {
        for(int i=0; i < 100; i++){
            System.out.println(new Random().nextInt(2));
        }

    }

}