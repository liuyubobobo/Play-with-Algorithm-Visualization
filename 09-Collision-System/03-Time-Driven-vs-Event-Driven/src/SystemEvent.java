public class SystemEvent {

    public enum Type{
        PARTICLE_HORENTAL_WALL_COLLISION,
        PARTICLE_VERTICLE_WALL_COLLISION,
        TWO_PARTICLES_COLLISION,
        REDRAW
    }

    Type type;
    Particle a, b;
    long time;

    public SystemEvent(Type type, Particle a, Particle b, long time){
        this.type = type;
        this.a = a;
        this.b = b;
        this.time = time;
    }
}
