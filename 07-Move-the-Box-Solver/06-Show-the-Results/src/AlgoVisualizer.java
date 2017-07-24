import javafx.scene.input.MouseButton;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AlgoVisualizer {

    private static int DELAY = 5;

    private GameData data;
    private AlgoFrame frame;
    private final int d[][] = {{-1,0},{0,1},{1,0},{0,-1}};

    public AlgoVisualizer(AlgoFrame frame, GameData data){

        this.frame = frame;
        this.data = data;
    }

    public void run(){

        if(data.solve())
            System.out.println("The game has a solution!");
        else
            System.out.println("The game does NOT have a solution.");

        this.setData();
        AlgoVisHelper.pause(DELAY);
    }

    public void addAlgoMouseListener(){
        frame.addMouseListener(new AlgoMouseListener());
    }

    private class AlgoMouseListener extends MouseAdapter{

        @Override
        public void mouseReleased(MouseEvent event){
            event.translatePoint(
                    -(int)(frame.getBounds().width - frame.getCanvasWidth()),
                    -(int)(frame.getBounds().height - frame.getCanvasHeight())
            );

            Point pos = event.getPoint();

            int w = frame.getCanvasWidth() / data.M();
            int h = frame.getCanvasHeight() / data.N();

            int x = pos.x / h;
            int y = pos.y / w;
            // System.out.println(x + " , " + y);

        }
    }

    private void setData(){
        frame.setData(data);
    }

    public static void main(String[] args) {

        String filename = "level/boston_09.txt";
        GameData data = new GameData(filename);

        int blockSide = 80;
        int sceneWidth = data.M() * blockSide;
        int sceneHeight = data.N() * blockSide;

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Move the Box Solver", sceneWidth,sceneHeight);

            AlgoVisualizer vis = new AlgoVisualizer(frame, data);
            vis.addAlgoMouseListener();
            new Thread(() -> {
                vis.run();
            }).start();
        });
    }
}
