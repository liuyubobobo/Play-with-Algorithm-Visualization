import java.util.Map;

public class Particle {

    private static int MIN_R = 10;
    private static int MAX_R = 30;
    private static int MAX_SPEED = 10;
    private static double MASS_C = 10.0;

    public int x, y;
    public int r;
    public double vx, vy;
    public double mass;

    public Particle(int x, int y, int r, double vx, double vy, double mass){
        this.x = x;
        this.y = y;
        this.r = r;
        this.vx = vx;
        this.vy = vy;
        this.mass = mass;
    }

    public static Particle randomParticle(int sceneWidth, int sceneHeight){

//        int x = (int)(Math.random() * sceneWidth);
//        int y = (int)(Math.random() * sceneHeight);
//        int r = (int)(Math.random() * (MAX_R - MIN_R + 1)) + MIN_R;

        int r = (int)(Math.random() * (MAX_R - MIN_R + 1)) + MIN_R;
        int x = (int)(Math.random() * (sceneWidth - 2*r) ) + r;
        int y = (int)(Math.random() * (sceneHeight - 2*r) ) + r;

        double vx = Math.random() * MAX_SPEED - MAX_SPEED/2;
        double vy = Math.random() * MAX_SPEED - MAX_SPEED/2;
        double mass = r*r / MASS_C;

        return new Particle(x, y, r, vx, vy, mass);
    }

    public boolean isCollision(Particle p){
        return (this.x - p.x) * (this.x - p.x) + (this.y - p.y) * (this.y - p.y)
                < (this.r + p.r) * (this.r + p.r);
    }

    public SystemEvent nextHorizontalWallCollisionEvent(){


    }
}
