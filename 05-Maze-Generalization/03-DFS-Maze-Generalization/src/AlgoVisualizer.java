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

        data.maze[data.entranceX][data.entranceY] = MazeData.ROAD;                    // 设置迷宫入口
        data.maze[data.exitX][data.exitY] = MazeData.ROAD;  // 设置迷宫出口

        genMazeByDFS(data.entranceX, data.entranceY + 1);

        this.setData();
        AlgoVisHelper.pause(DELAY);
    }

    private final int d[][] = {{-1,0},{0,1},{1,0},{0,-1}};
    public void genMazeByDFS(int x, int y){

        if(x < 0 || x >= data.N() || y < 0 || y >= data.M())
            throw new IllegalArgumentException("x, y out of index in genMaze function!");

        if(x%2 ==0 || y%2 == 0)
            throw new IllegalArgumentException("invalid x, y value in genMaze function!");

        data.visited[x][y] = true;
        for(int i = 0 ; i < 4 ; i ++){
            int newX = x + d[i][0]*2;
            int newY = y + d[i][1]*2;
            if(data.inArea(newX, newY) && !data.visited[newX][newY]){
                data.maze[x+d[i][0]][y+d[i][1]] = MazeData.ROAD;
                this.setData();
                AlgoVisHelper.pause(DELAY);
                genMazeByDFS(newX, newY);
            }
        }
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

            int N = sceneWidth/blockSide;
            int M = sceneWidth/blockSide;

            MazeData data = new MazeData(N, M);
            AlgoVisualizer vis = new AlgoVisualizer(frame, data);
            new Thread(() -> {
                vis.run();
            }).start();
        });
    }
}