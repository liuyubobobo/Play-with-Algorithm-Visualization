import java.awt.*;
import java.awt.event.*;

public class AlgoVisualizer {

    private static int DELAY = 40;

    private FractalData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int depth, double splitAngle){

        data = new FractalData(depth, splitAngle);

        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Fractal Visualizer", sceneWidth,sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run(){

        setData(data.depth);
    }

    private void setData(int depth){
        data.depth = depth;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
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
                setData(depth);
            }
        }
    }

    public static void main(String[] args) {

        int depth = 6;
        double splitAngle = 60.0;
        int sceneWidth = 800;
        int sceneHeight = 800;

        AlgoVisualizer vis = new AlgoVisualizer(sceneWidth, sceneHeight, depth, splitAngle);
    }
}
