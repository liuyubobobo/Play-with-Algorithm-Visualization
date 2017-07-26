import javafx.scene.input.MouseButton;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AlgoVisualizer {

    private static int DELAY = 40;

    private CircleData data;
    private AlgoFrame frame;

    public AlgoVisualizer(AlgoFrame frame, CircleData data){

        this.frame = frame;
        this.data = data;
        this.setData(data);
    }

    public void run(){

        this.setData(data);
        AlgoVisHelper.pause(DELAY);
    }

    private void setData(CircleData data){
        frame.setData(data);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Fractal Visualizer", sceneWidth,sceneHeight);

            //CircleData data = new CircleData(sceneWidth/2, sceneHeight/2, sceneWidth/2-2, 10, 10);
            CircleData data = new CircleData(sceneWidth/2, sceneHeight/2, sceneWidth/2-2, 500, 2);

            AlgoVisualizer vis = new AlgoVisualizer(frame, data);
            new Thread(() -> {
                vis.run();
            }).start();
        });
    }
}