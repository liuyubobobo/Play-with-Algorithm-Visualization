
public class Board {

    private int N, M;
    private char[][] data;

    public Board(String[] lines){
        if(lines == null)
            throw new IllegalArgumentException("lines cannot be null in Board constructor.");

        N = lines.length;
        if(N == 0)
            throw new IllegalArgumentException("lines cannot be empty in Board constructor.");

        M = lines[0].length();

        data = new char[N][M];
        for(int i = 0 ; i < N ; i ++){
            if(lines[i].length() != M)
                throw new IllegalArgumentException("All lines' length must be same in Board constructor.");
            for(int j = 0 ; j < M ; j ++)
                data[i][j] = lines[i].charAt(j);
        }
    }

    public int N(){ return N; }
    public int M(){ return M; }

    public boolean inArea(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public void print(){
        for(int i = 0 ; i < N ; i ++)
            System.out.println(String.valueOf(data[i]));
    }
}
