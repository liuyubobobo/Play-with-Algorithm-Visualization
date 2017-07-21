import java.awt.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.Arrays;
import java.util.Queue;

public class AlgoVisualizer {

    private static int DELAY = 40;

    private MazeData data;
    private AlgoFrame frame;
    private final int d[][] = {{-1,0},{0,1},{1,0},{0,-1}};

    public AlgoVisualizer(AlgoFrame frame, MazeData data){

        this.frame = frame;
        this.data = data;
    }

    public void run(){

        go(1, 0);

        this.setData(-1, -1);
        AlgoVisHelper.pause(DELAY);
    }

    public void go(int x, int y){

        if(!data.inArea(x,y))
            throw new IllegalArgumentException("x,y are out of index in go function!");

        data.visited[x][y] = true;
        this.setData(x, y);
        AlgoVisHelper.pause(DELAY);

        for(int i = 0 ; i < 4 ; i ++){
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            if(data.inArea(newX, newY) && data.maze[newX][newY] == MazeData.ROAD && !data.visited[newX][newY])
                go(newX, newY);
        }

        return;
    }


    private void setData(int x, int y){
        if(data.inArea(x, y))
            data.path[x][y] = true;
        frame.setData(data);
    }

    public static void main(String[] args) {

        String filename = "maze_101_101.txt";
        MazeData data = new MazeData(filename);
        //data.print();

        int blockSide = 8;
        int sceneHeight = data.N() * blockSide;
        int sceneWidth = data.M() * blockSide;

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Maze Solver Visualization", sceneWidth,sceneHeight);

            AlgoVisualizer vis = new AlgoVisualizer(frame, data);
            new Thread(() -> {
                vis.run();
            }).start();
        });
    }
}