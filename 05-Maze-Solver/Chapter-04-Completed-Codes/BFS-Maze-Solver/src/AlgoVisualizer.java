import javafx.geometry.Pos;

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

        this.setData(false, Position.invalidPosition(), false, Position.invalidPosition());
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

        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(data.getEntranceX(), data.getEntranceY()));
        data.visited[data.getEntranceX()][data.getEntranceY()] = true;
        this.setData(false, Position.invalidPosition(), true, queue.peek());
        AlgoVisHelper.pause(DELAY);

        while(queue.size() != 0){
            Position cur = queue.remove();
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
                    queue.add(new Position(newX, newY));
                    data.visited[newX][newY] = true;
                    data.prev[newX][newY] = cur;
                    this.setData(false, Position.invalidPosition(), true, new Position(newX, newY));
                    AlgoVisHelper.pause(DELAY);
                }
            }
        }

        return isSolved;
    }

    private void findPath(){

        data.clearTag(data.path);
        data.clearTag(data.inQueue);

        Position cur = new Position(data.getExitX(), data.getExitY());
        while(cur != null){
            data.path[cur.x][cur.y] = true;
            this.setData(true, cur, false, Position.invalidPosition());
            AlgoVisHelper.pause(DELAY);

            cur = data.prev[cur.x][cur.y];
        }

        return;
    }

    private void setData(boolean isPath, Position pathPos, boolean inQueue, Position queuePos){
        if(data.inArea(pathPos.x, pathPos.y))
            data.path[pathPos.x][pathPos.y] = isPath;
        if(data.inArea(queuePos.x, queuePos.y))
            data.inQueue[queuePos.x][queuePos.y] = inQueue;
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