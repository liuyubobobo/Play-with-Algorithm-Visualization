import java.io.*;


public class MazeData {

    public static final char ROAD = ' ';
    public static final char WALL = '#';

    private int N, M;
    public char[][] maze;
    public boolean[][] visited;

    public MazeData(int N, int M){

        if( N%2 == 0 || M%2 == 0)
            throw new IllegalArgumentException("Our Maze Generalization Algorihtm requires the width and height of the maze are odd numbers");

        this.N = N;
        this.M = M;

        maze = new char[N][M];
        visited = new boolean[N][M];
        for(int i = 0 ; i < N ; i ++)
            for(int j = 0 ; j < M ; j ++){
                if(i%2 == 1 && j%2 == 1)
                    maze[i][j] = ROAD;
                else
                    maze[i][j] = WALL;

                visited[i][j] = false;
            }
    }

    public int N(){
        return N;
    }

    public int M(){
        return M;
    }

    public boolean inArea(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public boolean writeFile(String filename){

        boolean isFinished = false;
        PrintWriter out = null;
        try {
            OutputStream os = new FileOutputStream(filename);
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            out = new PrintWriter(osw, true);

            out.println("" + N + " " + M);
            for(int i = 0 ; i < N ; i ++){
                for (int j = 0 ; j < M ; j ++)
                    out.print(maze[i][j]);
                out.println();
            }

            isFinished = true;
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally {
            if(out != null)
                out.close();
            return isFinished;
        }

    }
}