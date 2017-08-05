import javafx.scene.input.MouseButton;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AlgoVisualizer {

    private static int DELAY = 40;

    private SystemData data;
    private AlgoFrame frame;

    public AlgoVisualizer(AlgoFrame frame, SystemData data){

        this.frame = frame;
        this.data = data;
        this.setData(data);
    }

    public void run(){

        this.setData(data);
        AlgoVisHelper.pause(DELAY);
    }

    private void setData(SystemData data){
        frame.setData(data);
    }

    public static void main(String[] args) {

        int sceneWidth = 1200;
        int sceneHeight = 800;
        int N = 50;

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Fractal Visualizer", sceneWidth,sceneHeight);

            SystemData data = new SystemData(N, sceneWidth, sceneHeight);

            AlgoVisualizer vis = new AlgoVisualizer(frame, data);
            new Thread(() -> {
                vis.run();
            }).start();
        });
    }
}