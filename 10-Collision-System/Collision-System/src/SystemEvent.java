public class SystemEvent implements Comparable<SystemEvent>{

    public enum Type{
        PARTICLE_HORIZONTAL_WALL_COLLISION,
        PARTICLE_VERTICLE_WALL_COLLISION,
        TWO_PARTICLES_COLLISION,
        REDRAW
    }

    private Type type;
    private Particle a, b;
    private double time;

    public SystemEvent(Type type, Particle a, Particle b, double time){
        this.type = type;
        this.a = a;
        this.b = b;
        this.time = time;
    }

    public Type getType(){return type;}
    public Particle getParticleA(){return a;}
    public Particle getParticleB(){return b;}
    public double getTime(){return time;}

    @Override
    public int compareTo(SystemEvent another){
        if(this.time < another.time)
            return -1;
        else if(this.time > another.time)
            return 1;
        return 0;
    }
}
