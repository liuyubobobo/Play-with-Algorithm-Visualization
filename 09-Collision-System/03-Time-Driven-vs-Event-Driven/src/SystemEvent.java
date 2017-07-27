public class SystemEvent {

    public enum Type{
        PARTICLE_HORIZONTAL_WALL_COLLISION,
        PARTICLE_VERTICLE_WALL_COLLISION,
        TWO_PARTICLES_COLLISION,
        REDRAW
    }

    Type type;
    Particle a, b;
    double time;

    public SystemEvent(Type type, Particle a, Particle b, double time){
        this.type = type;
        this.a = a;
        this.b = b;
        this.time = time;
    }
}
