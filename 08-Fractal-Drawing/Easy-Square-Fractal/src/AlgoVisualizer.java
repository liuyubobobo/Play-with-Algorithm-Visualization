import javafx.scene.input.MouseButton;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AlgoVisualizer {

    private static int DELAY = 40;

    private FractalData data;
    private AlgoFrame frame;

    public AlgoVisualizer(AlgoFrame frame, FractalData data){

        this.frame = frame;
        this.data = data;
        this.setData(data);
    }

    public void run(){

        this.setData(data);
        AlgoVisHelper.pause(DELAY);
    }

    private void setData(FractalData data){
        frame.setData(data);
    }

    public static void main(String[] args) {

        int depth = 6;
        int sceneWidth = (int)Math.pow(3, depth);
        int sceneHeight = (int)Math.pow(3, depth);

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Fractal Visualizer", sceneWidth,sceneHeight);

            FractalData data = new FractalData(depth);

            AlgoVisualizer vis = new AlgoVisualizer(frame, data);
            new Thread(() -> {
                vis.run();
            }).start();
        });
    }
}