import java.awt.*;
import java.util.Random;

public class AlgoVisualizer {

    private int N;
    private Circle[] circles;
    static private int R = 50;
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){

        this.frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);

        this.N = N;
        circles = new Circle[N];

        for(int i = 0 ; i < N ; i ++ ) {
            int x = (int)(Math.random()*(frame.getCanvasWidth()-2*R)) + R;
            int y = (int)(Math.random()*(frame.getCanvasHeight()-2*R)) + R;
            int vx = (int)(Math.random()*11) - 5;
            int vy = (int)(Math.random()*11) - 5;
            circles[i] = new Circle(x, y, R, vx, vy);
        }
    }

    public void run(){

        while(true){
            // 绘制数据
            frame.setCircles(circles);
            AlgoVisHelper.pause(20);

            // 更新数据
            for(Circle circle: circles)
                circle.move(0, 0, frame.getCanvasWidth(), frame.getCanvasHeight());
        }
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 10;

        EventQueue.invokeLater(() -> {
            AlgoVisualizer vis = new AlgoVisualizer(sceneWidth, sceneHeight, N);
            new Thread(() -> {
                vis.run();
            }).start();
        });
    }
}
