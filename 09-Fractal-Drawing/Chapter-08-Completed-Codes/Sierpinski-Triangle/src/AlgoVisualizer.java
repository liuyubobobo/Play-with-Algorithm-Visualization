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

    public void addAlgoKeyListener(){
        frame.addKeyListener(new AlgoKeyListener());
    }

    private class AlgoKeyListener extends KeyAdapter{

        @Override
        public void keyReleased(KeyEvent event){
            //System.out.println("Key released:" + event);
            if(event.getKeyChar() >= '0' && event.getKeyChar() <= '9'){
                int depth = event.getKeyChar() - '0';
                data.depth = depth;
                setData(data);
            }
        }
    }

    public static void main(String[] args) {

        int depth = 6;
        int sceneWidth = 800;
        int sceneHeight = (int)(Math.sin(60.0*Math.PI/180.0)*sceneWidth);

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Fractal Visualizer", sceneWidth,sceneHeight);

            FractalData data = new FractalData(depth);

            AlgoVisualizer vis = new AlgoVisualizer(frame, data);
            vis.addAlgoKeyListener();
            new Thread(() -> {
                vis.run();
            }).start();
        });
    }
}