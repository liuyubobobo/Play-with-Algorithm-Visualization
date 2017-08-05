import javafx.scene.input.MouseButton;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.PriorityQueue;

public class AlgoVisualizer {

    private static int DELAY = 40;

    private SystemData data;
    private AlgoFrame frame;
    private PriorityQueue<SystemEvent> pq;
    double time = 0.0;

    public AlgoVisualizer(AlgoFrame frame, SystemData data){

        this.frame = frame;
        this.data = data;
        pq = new PriorityQueue<SystemEvent>();
        pq.add(new SystemEvent(SystemEvent.Type.REDRAW, null, null, 0));

        //this.setData(data);
    }

    public void run(){

        while(!pq.isEmpty()){

            SystemEvent curEvent = pq.remove();
            for(int i = 0 ; i < data.N() ; i ++)
                data.particles[i].move(curEvent.getTime()-this.time);
            this.time = curEvent.getTime();

            if(curEvent.getType() == SystemEvent.Type.REDRAW){
                this.setData(data);
                AlgoVisHelper.pause(DELAY);
                pq.add(new SystemEvent(SystemEvent.Type.REDRAW, null, null, curEvent.getTime()+DELAY));
                // System.out.println("REDRAW event handled.");
            }
            else if(curEvent.getType() == SystemEvent.Type.PARTICLE_HORIZONTAL_WALL_COLLISION){
                ;
            }
            else if(curEvent.getType() == SystemEvent.Type.PARTICLE_VERTICLE_WALL_COLLISION){
                ;
            }
            else{ // curEvent.getType() == SystemEvent.Type.TWO_PARTICLES_COLLISION
                ;
            }
        }
    }

    private void setData(SystemData data){
        frame.setData(data);
    }

    public static void main(String[] args) {

        int sceneWidth = 1200;
        int sceneHeight = 800;
        int N = 50;

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Fractal Visualizer", sceneWidth,sceneHeight);

            SystemData data = new SystemData(N, sceneWidth, sceneHeight);

            AlgoVisualizer vis = new AlgoVisualizer(frame, data);
            new Thread(() -> {
                vis.run();
            }).start();
        });
    }
}