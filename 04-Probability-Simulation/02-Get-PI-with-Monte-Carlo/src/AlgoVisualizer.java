import javafx.scene.input.MouseButton;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.PriorityQueue;
import java.util.Arrays;

public class AlgoVisualizer {

    private static int DELAY = 40;

    private MonteCarloData data;
    private AlgoFrame frame;
    boolean running = true;

    public AlgoVisualizer(AlgoFrame frame, MonteCarloData data){

        this.frame = frame;
        this.data = data;

        this.setData();
    }

    public void run(){

        int N = 50000;
        for(int i = 0 ; i < N ; i ++){
            int x = (int)(Math.random() * frame.getCanvasWidth());
            int y = (int)(Math.random() * frame.getCanvasHeight());
            data.addPoint(x, y);

            if( data.N() % 10 == 0) {
                this.setData();
                AlgoVisHelper.pause(DELAY);

                double area = (double)data.getInsideCircleNum() / data.N() * (frame.getCanvasHeight() * frame.getCanvasWidth());
                System.out.println("PI: " + area / (data.getR() * data.getR()) );
            }

        }

//        double area = (double)data.getInsideCircleNum() / data.N() * (frame.getCanvasHeight() * frame.getCanvasWidth());
//        System.out.println("PI: " + area / (data.getR() * data.getR()) );
//        setData();
    }

    private void setData(){
        frame.setData(data);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Monte Carlo Visualizer", sceneWidth,sceneHeight);

            MonteCarloData data = new MonteCarloData(sceneWidth/2, sceneHeight/2, 300);

            AlgoVisualizer vis = new AlgoVisualizer(frame, data);
            new Thread(() -> {
                vis.run();
            }).start();
        });
    }
}
