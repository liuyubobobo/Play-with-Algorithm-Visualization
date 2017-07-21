import java.awt.*;
import java.util.*;

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

        if(go())
            System.out.println("The maze solved.");
        else
            System.out.println("The maze has no solution.");

        this.setData(false, -1, -1);
        AlgoVisHelper.pause(DELAY);
    }

    public boolean go(){

        boolean isSolved = false;

        Stack<Position> stack = new Stack<Position>();
        stack.push(new Position(data.getEntranceX(), data.getEntranceY()));
        data.visited[data.getEntranceX()][data.getEntranceY()] = true;

        while(!stack.empty()){
            Position cur = stack.pop();
            this.setData(true, cur.x, cur.y);
            AlgoVisHelper.pause(DELAY);

            if(cur.x == data.getExitX() && cur.y == data.getExitY()){
                isSolved = true;
                break;
            }

            for(int i = 0 ; i < 4 ; i ++){
                int newX = cur.x + d[i][0];
                int newY = cur.y + d[i][1];
                if(data.inArea(newX, newY) && !data.visited[newX][newY] && data.maze[newX][newY] == MazeData.ROAD){
                    stack.push(new Position(newX, newY));
                    data.visited[newX][newY] = true;
                }
            }
        }

        return isSolved;
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