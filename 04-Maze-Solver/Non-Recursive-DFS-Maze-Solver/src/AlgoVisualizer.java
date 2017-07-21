import java.awt.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.Arrays;
import java.util.Queue;

public class AlgoVisualizer {

    private static int DELAY = 5;

    private MazeData data;
    private AlgoFrame frame;
    private final int d[][] = {{-1,0},{0,1},{1,0},{0,-1}};

    public AlgoVisualizer(AlgoFrame frame, MazeData data){

        this.frame = frame;
        this.data = data;
    }

    public void run(){

        go();

        this.setData(false, -1, -1);
        AlgoVisHelper.pause(DELAY);
    }

    public boolean go(){

        return true;
    }


    private void setData(boolean isPath, int x, int y){
        if(data.inArea(x, y))
            data.path[x][y] = isPath;
        frame.setData(data);
    }

    public static void main(String[] args) {

        String filename = "maze_101_101.txt";
        MazeData data = new MazeData(filename);
        //data.print();

        int blockSide = 8;
        int sceneWidth = data.N() * blockSide;
        int sceneHeight = data.M() * blockSide;

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Maze Solver Visualization", sceneWidth,sceneHeight);

            AlgoVisualizer vis = new AlgoVisualizer(frame, data);
            new Thread(() -> {
                vis.run();
            }).start();
        });
    }
}