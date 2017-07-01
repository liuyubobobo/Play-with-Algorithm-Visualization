import java.awt.EventQueue;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Welcome", sceneWidth,sceneHeight);

            // init data
            int N = 10;
            Circle[] circles = new Circle[N];
            int R = 50;

            Random rand = new Random();
            for(int i = 0 ; i < N ; i ++ ) {
                int x = rand.nextInt(sceneWidth-2*R) + R;
                int y = rand.nextInt(sceneHeight-2*R) + R;
                int vx = rand.nextInt(11) - 5;
                int vy = rand.nextInt(11) - 5;
                circles[i] = new Circle(x, y, R, vx, vy);
            }

            new Thread(() -> {
                while(true) {
                    // 绘制数据
                    frame.setCircles(circles);
                    AlgoVisHelper.pause(20);

                    // 更新数据
                    for(Circle circle: circles)
                        circle.go(0, 0, sceneWidth, sceneHeight);
                }
            }).start();
        });

    }
}
