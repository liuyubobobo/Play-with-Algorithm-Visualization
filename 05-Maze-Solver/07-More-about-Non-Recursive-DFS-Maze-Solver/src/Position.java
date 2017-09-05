public class Position {

    public int x, y;
    public Position prev;

    public Position(int x, int y, Position prev){
        this.x = x;
        this.y = y;
        this.prev = prev;
    }

    public Position(int x, int y){
        this(x, y, null);
    }
}
