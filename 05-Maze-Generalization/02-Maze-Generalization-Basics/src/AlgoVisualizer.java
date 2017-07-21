import java.awt.*;
import java.util.Random;
import java.util.Arrays;

public class AlgoVisualizer {

    private static int DELAY = 40;

    private MazeData data;
    private AlgoFrame frame;

    public AlgoVisualizer(AlgoFrame frame, MazeData data){

        this.frame = frame;
        this.data = data;
    }

    public void run(){

        this.setData();
        AlgoVisHelper.pause(DELAY);
    }

    private void setData(){
        frame.setData(data);
    }

    public static void main(String[] args) {

        int sceneWidth = 808;
        int sceneHeight = 808;
        int blockSide = 8;

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Maze Generation Visualization", sceneWidth,sceneHeight);

            int N = sceneHeight/blockSide;
            int M = sceneWidth/blockSide;

            MazeData data = new MazeData(N, M);
            AlgoVisualizer vis = new AlgoVisualizer(frame, data);
            new Thread(() -> {
                vis.run();
            }).start();
        });
    }
}