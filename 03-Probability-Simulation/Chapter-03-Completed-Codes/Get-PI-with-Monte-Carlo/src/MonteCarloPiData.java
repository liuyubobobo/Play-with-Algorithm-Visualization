import java.util.LinkedList;
import java.awt.*;

public class MonteCarloPiData {

    private Circle circle;
    private LinkedList<Point> points;
    private int insideCircle = 0;

    public MonteCarloPiData(Circle circle){
        this.circle = circle;
        points = new LinkedList<Point>();
    }

    public Circle getCircle(){
        return circle;
    }

    public int getPointsNumber(){
        return points.size();
    }

    public Point getPoint(int i){
        if(i < 0 || i >= points.size())
            throw new IllegalArgumentException("out of bound in getPoint!");

        return points.get(i);
    }

    public void addPoint(Point p){
        points.add(p);
        if(circle.contain(p))
            insideCircle ++;
    }

    public double estimatePi(){

        if(points.size() == 0)
            return 0.0;

        int circleArea = insideCircle;
        int squareArea = points.size();
        return (double)circleArea * 4 / squareArea;
    }
}
