import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;

public class AlgoVisualizer {

    private static int DELAY = 40;

    private Circle circle;
    private LinkedList<Point> points;
    private AlgoFrame frame;
    private int N;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){

        if(sceneWidth != sceneHeight)
            throw new IllegalArgumentException("This demo must be run in a square window!");

        this.N = N;

        circle = new Circle(sceneWidth/2, sceneHeight/2, sceneWidth/2);
        points = new LinkedList<Point>();

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Get Pi with Monte Carlo", sceneWidth, sceneHeight);

            new Thread(() -> {
                run();
            }).start();
        });
    }

    public void run(){

        for(int i = 0 ; i < N ; i ++){

            frame.render(circle, points);
            AlgoVisHelper.pause(DELAY);

            int x = (int)(Math.random() * frame.getCanvasWidth());
            int y = (int)(Math.random() * frame.getCanvasHeight());

            Point p = new Point(x, y);
            points.add(p);
        }

    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 10000;

        AlgoVisualizer vis = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}
