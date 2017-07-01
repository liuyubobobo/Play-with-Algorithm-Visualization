import java.awt.EventQueue;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;

        int N = 100;
        Circle[] circles = new Circle[N];
        int R = 50;

        Random rand = new Random();
        for(int i = 0 ; i < N ; i ++ ) {
            int x = rand.nextInt(sceneWidth-2*R) + R;
            int y = rand.nextInt(sceneHeight-2*R) + R;
            circles[i] = new Circle(x, y, R);
        }

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Welcome", sceneWidth,sceneHeight);
            frame.setCircles(circles);
        });
    }
}
