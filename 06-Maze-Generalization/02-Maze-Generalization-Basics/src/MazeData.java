public class MazeData {

    public static final char ROAD = ' ';
    public static final char WALL = '#';

    private int N, M;
    public char[][] maze;

    public MazeData(int N, int M){

        if( N%2 == 0 || M%2 == 0)
            throw new IllegalArgumentException("Our Maze Generalization Algorihtm requires the width and height of the maze are odd numbers");

        this.N = N;
        this.M = M;

        maze = new char[N][M];
        for(int i = 0 ; i < N ; i ++)
            for(int j = 0 ; j < M ; j ++)
                if(i%2 == 1 && j%2 == 1)
                    maze[i][j] = ' ';
                else
                    maze[i][j] = '#';
    }

    public int N(){
        return N;
    }

    public int M(){
        return M;
    }
}
