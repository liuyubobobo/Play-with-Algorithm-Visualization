import java.awt.*;

public class AlgoVisualizer {

    private static int DELAY = 40;

    private FractalData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int depth){

        data = new FractalData(depth);
        int sceneWidth = (int)Math.pow(3, depth);
        int sceneHeight = (int)Math.pow(3, depth);

        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Fractal Visualizer", sceneWidth,sceneHeight);

            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run(){

        setData();
    }

    private void setData(){
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        int depth = 6;

        AlgoVisualizer vis = new AlgoVisualizer(depth);
    }
}