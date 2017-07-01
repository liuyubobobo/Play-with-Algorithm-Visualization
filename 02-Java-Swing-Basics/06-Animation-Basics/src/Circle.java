
public class Circle {

    public int x;
    public int y;
    public int r;
    public int vx;
    public int vy;

    public Circle(int x, int y, int r, int vx, int vy){
        this.x = x;
        this.y = y;
        this.r = r;
        this.vx = vx;
        this.vy = vy;
    }

    public void go(){
        x += vx;
        y += vy;
    }

}
