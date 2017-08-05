import java.awt.geom.Point2D;
import java.util.LinkedList;

public class MonteCarloData {

    private int x, y, r;

    private LinkedList<Point2D> points;
    private int N;
    private int insideCircleNum;
    private int outsideCircleNum;

    public MonteCarloData(int x, int y, int r){

        this.x = x;
        this.y = y;
        this.r = r;

        points = new LinkedList<Point2D>();
        this.N = 0;
        this.insideCircleNum = 0;
        this.outsideCircleNum = 0;
    }

    public int getX(){ return x; }
    public int getY(){ return y; }
    public int getR(){ return r; }
    public int N(){ return N; }
    public int getInsideCircleNum(){ return insideCircleNum; }
    public int getOutsideCircleNum(){ return outsideCircleNum; }

    public boolean inCircle(Point2D p){
        return Math.pow(p.getX() - x, 2) + Math.pow(p.getY() - y, 2) <= r*r;
    }

    public void addPoint(int x, int y){
        points.add(new Point2D.Double(x, y));
        this.N ++;
        if(inCircle(points.getLast()))
            insideCircleNum ++;
        else
            outsideCircleNum ++;
    }

    public Point2D getPoint(int index){
        if(index < 0 || index >= N)
            throw new IllegalArgumentException("index is out of range in getPoint.");

        return points.get(index);
    }
}
