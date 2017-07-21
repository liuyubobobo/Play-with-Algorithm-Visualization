import java.awt.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.Arrays;
import java.util.Queue;

public class AlgoVisualizer {

    private static int DELAY = 5;

    private MineSweeperData data;
    private AlgoFrame frame;
    private final int d[][] = {{-1,0},{0,1},{1,0},{0,-1}};

    public AlgoVisualizer(AlgoFrame frame, MineSweeperData data){

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

        int sceneWidth = 800;
        int sceneHeight = 800;
        int blockSide = 8;

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Mine Sweeper", sceneWidth,sceneHeight);

            int N = sceneWidth/blockSide;
            int M = sceneWidth/blockSide;
            int mineNumber = (int)(0.01*N*M);

            MineSweeperData data = new MineSweeperData(N, M, mineNumber);
            AlgoVisualizer vis = new AlgoVisualizer(frame, data);
            new Thread(() -> {
                vis.run();
            }).start();
        });
    }
}
