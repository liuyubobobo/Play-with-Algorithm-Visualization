import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class GameData {

    private int maxTurn;
    private int N, M;
    private Board starterBoard;
    private Board showBoard;

    public GameData(String filename){

        if(filename == null)
            throw new IllegalArgumentException("Filename cannot be null!");

        Scanner scanner = null;
        try{
            File file = new File(filename);
            if(!file.exists())
                throw new IllegalArgumentException("File " + filename + " doesn't exist!");

            FileInputStream fis = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");

            String turnline = scanner.nextLine();

            this.maxTurn = Integer.parseInt(turnline);

            ArrayList<String> lines = new ArrayList<String>();
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                lines.add(line);
            }
            starterBoard = new Board(lines.toArray(new String[lines.size()]));

            this.N = starterBoard.N();
            this.M = starterBoard.M();

            starterBoard.print();

            showBoard = new Board(starterBoard);
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
    public Board getShowBoard(){ return showBoard;}

    public boolean inArea(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public boolean solve(){

        if(maxTurn <= 0)
            return false;

        return solve(new Board(starterBoard), maxTurn);
    }

    private static int d[][] = {{-1, 0}, {0, 1}, {0,-1}};
    private boolean solve(Board board, int turn){

        if(board == null)
            throw new IllegalArgumentException("board can not be null in solve function!");

        if(turn == 0)
            return board.isWin();

        if(board.isWin())
            return true;

        for(int x = 0 ; x < N ; x ++)
            for(int y = 0 ; y < M ; y ++)
                if(board.getData(x, y) != Board.EMPTY){
                    for(int i = 0 ; i < 3 ; i ++){
                        int newX = x + d[i][0];
                        int newY = y + d[i][1];
                        if(inArea(newX, newY)){
                            Board nextBoard = new Board(board);
                            nextBoard.swap(x, y, newX, newY);
                            nextBoard.run();
                            if(solve(nextBoard, turn-1))
                                return true;
                        }
                    }
                }

        return false;
    }
}
