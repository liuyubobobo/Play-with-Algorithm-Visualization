import javafx.scene.input.MouseButton;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AlgoVisualizer {

    private static int DELAY = 5;

    private MineSweeperData data;
    private AlgoFrame frame;
    private final int d[][] = {{-1,0},{0,1},{1,0},{0,-1}};

    public AlgoVisualizer(AlgoFrame frame, MineSweeperData data){

        this.frame = frame;
        this.data = data;
    }

    public void run(){

        this.setData();
        AlgoVisHelper.pause(DELAY);
    }

    private void setData(){
        frame.setData(data);
    }

    private class AlgoMouseListener extends MouseAdapter{

        @Override
        public void mouseReleased(MouseEvent event){
            event.translatePoint(
                    -(int)(frame.getBounds().width - frame.getCanvasWidth()),
                    -(int)(frame.getBounds().height - frame.getCanvasHeight())
            );

            Point pos = event.getPoint();

            if(SwingUtilities.isLeftMouseButton(event)){

            }
            else if(SwingUtilities.isRightMouseButton(event)){

            }
        }
    }

    public static void main(String[] args) {

        int N = 20;
        int M = 20;

        int blockSide = 32;
        int sceneWidth = M * blockSide;
        int sceneHeight = N * blockSide;

        int mineNumber = 20;
        MineSweeperData data = new MineSweeperData(N, M, mineNumber);

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Mine Sweeper", sceneWidth,sceneHeight);

            AlgoVisualizer vis = new AlgoVisualizer(frame, data);
            new Thread(() -> {
                vis.run();
            }).start();
        });
    }
}
