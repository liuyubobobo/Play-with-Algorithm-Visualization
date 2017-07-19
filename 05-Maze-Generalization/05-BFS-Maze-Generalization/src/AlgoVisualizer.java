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

        data.maze[1][0] = MazeData.ROAD;                    // 设置迷宫入口
        data.maze[data.N()-2][data.M()-1] = MazeData.ROAD;  // 设置迷宫出口

        Queue<Position> queue = new LinkedList<Position>();
        queue.add(new Position(1, 1));
        data.visited[1][1] = true;
        while(queue.size() != 0){
            Position cur = queue.remove();

            for(int i = 0 ; i < 4 ; i ++){
                int newX = cur.x + d[i][0]*2;
                int newY = cur.y + d[i][1]*2;
                if(data.inArea(newX, newY) && !data.visited[newX][newY]){
                    data.maze[cur.x+d[i][0]][cur.y+d[i][1]] = MazeData.ROAD;
                    queue.add(new Position(newX, newY));
                    data.visited[newX][newY] = true;
                    this.setData();
                    AlgoVisHelper.pause(DELAY);
                }
            }
        }

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