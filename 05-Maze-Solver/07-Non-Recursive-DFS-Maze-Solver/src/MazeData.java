import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;


public class MazeData {

    public static final char ROAD = ' ';
    public static final char WALL = '#';

    private int entranceX, entranceY;
    private int exitX, exitY;

    private int N, M;
    public char[][] maze;
    public boolean[][] visited;
    public boolean[][] path;
    public boolean[][] inStack;
    public Position[][] prev;

    public MazeData(String filename){

        if(filename == null)
            throw new IllegalArgumentException("Filename can not be null!");

        Scanner scanner = null;
        try{
            File file = new File(filename);
            if(!file.exists())
                throw new IllegalArgumentException("File " + filename + " doesn't exist");

            FileInputStream fis = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");

            String nmline = scanner.nextLine();

            Scanner nmScanner = null;
            nmScanner = new Scanner(nmline);
            N = nmScanner.nextInt();
            M = nmScanner.nextInt();
            nmScanner.close();
            if(N < 2 || M < 1)
                throw new IllegalArgumentException("The size of maze is invalid.");

            maze = new char[N][M];
            visited = new boolean[N][M];
            path = new boolean[N][M];
            inStack = new boolean[N][M];
            prev = new Position[N][M];
            for(int i = 0 ; i < N ; i ++){
                String line = scanner.nextLine();
                if(line.length() != M)
                    throw new IllegalArgumentException("Maze file " + filename + " is invalid");
                for(int j = 0 ; j < M ; j ++){
                    maze[i][j] = line.charAt(j);
                    visited[i][j] = false;
                    path[i][j] = false;
                    inStack[i][j] = false;
                    prev[i][j] = null;
                }
            }

            entranceX = 1;
            entranceY = 0;
            exitX = N - 2;
            exitY = M - 1;
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally {
            if(scanner != null)
                scanner.close();
        }
    }

    public int N(){ return N; }
    public int M(){ return M; }
    public int getEntranceX(){ return entranceX; }
    public int getEntranceY(){ return entranceY; }
    public int getExitX(){ return exitX; }
    public int getExitY(){ return exitY; }

    public boolean inArea(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public void print(){
        System.out.println(N + " " + M);
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < M ; j ++)
                System.out.print(maze[i][j]);
            System.out.println();
        }
        return;
    }

    public void clearTag(boolean[][] tag){
        for(int i = 0 ; i < N ; i ++)
            for(int j = 0 ; j < M ; j ++)
                tag[i][j] = false;
        return;
    }
}