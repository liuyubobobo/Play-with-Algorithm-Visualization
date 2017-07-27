/**
 * Created by yuanzhang on 7/27/17.
 */
public class SystemData {

    private int sceneWidth, sceneHeight;

    private int N;
    public Particle particles[];

    public SystemData(int N, int sceneWidth, int sceneHeight){

        this.N = N;
        particles = new Particle[N];
        for(int i = 0 ; i < N ; i ++) {
            particles[i] = Particle.randomParticle(sceneWidth, sceneHeight);
        }
    }

    public int N(){ return N; }
}
