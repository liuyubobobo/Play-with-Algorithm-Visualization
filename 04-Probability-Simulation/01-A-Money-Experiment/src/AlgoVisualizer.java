import javafx.scene.input.MouseButton;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.PriorityQueue;
import java.util.Arrays;

public class AlgoVisualizer {

    private static int DELAY = 40;

    private SocietyData data;
    private AlgoFrame frame;
    boolean running = true;

    public AlgoVisualizer(AlgoFrame frame, SocietyData data){

        this.frame = frame;
        this.data = data;

        this.setData();
    }

    public void run(){

        while(running){

            // 每一帧执行的tick数
            for(int k = 0 ; k < DELAY*100 ; k ++){
                int i = (int)(Math.random() * data.N());
                int j = (int)(Math.random() * data.N());
                int money = 1;
                data.money[i] -= money;
                data.money[j] += money;
            }

            // 是否排序
            Arrays.sort(data.money);
            this.setData();
            AlgoVisHelper.pause(DELAY);
        }
    }

    private void setData(){
        frame.setData(data);
    }

    public static void main(String[] args) {

        int sceneWidth = 1200;
        int sceneHeight = 840;
        int N = 120;

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Money Problem", sceneWidth,sceneHeight);

            SocietyData data = new SocietyData(N, sceneHeight);

            AlgoVisualizer vis = new AlgoVisualizer(frame, data);
            new Thread(() -> {
                vis.run();
            }).start();
        });
    }
}