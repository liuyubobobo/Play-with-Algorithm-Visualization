import javafx.geometry.Pos;

import java.awt.*;
import java.util.*;

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

        if(go())
            System.out.println("The maze solved.");
        else
            System.out.println("The maze has no solution.");

        findPath();

        this.setData(false, Position.invalidPosition(), false, Position.invalidPosition());
        AlgoVisHelper.pause(DELAY);
    }

    public boolean go(){

        boolean isSolved = false;

        Stack<Position> stack = new Stack<Position>();
        stack.push(new Position(data.getEntranceX(), data.getEntranceY()));
        data.visited[data.getEntranceX()][data.getEntranceY()] = true;
        this.setData(false, Position.invalidPosition(), true, stack.peek());
        AlgoVisHelper.pause(DELAY);

        while(!stack.empty()){
            Position cur = stack.pop();
            this.setData(true, cur, false, cur);
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
                    data.prev[newX][newY] = cur;
                    this.setData(false, Position.invalidPosition(), true, stack.peek());
                    AlgoVisHelper.pause(DELAY);
                }
            }
        }

        return isSolved;
    }

    private void findPath(){

        data.clearTag(data.path);
        data.clearTag(data.inStack);

        Position cur = new Position(data.getExitX(), data.getExitY());
        while(cur != null){
            data.path[cur.x][cur.y] = true;
            this.setData(true, cur, false, Position.invalidPosition());
            AlgoVisHelper.pause(DELAY);

            cur = data.prev[cur.x][cur.y];
        }

        return;
    }

    private void setData(boolean isPath, Position pathPos, boolean inStack, Position stackPos){
        if(data.inArea(pathPos.x, pathPos.y))
            data.path[pathPos.x][pathPos.y] = isPath;
        if(data.inArea(stackPos.x, stackPos.y))
            data.inStack[stackPos.x][stackPos.y] = inStack;
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