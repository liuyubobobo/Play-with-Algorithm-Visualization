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

        this.setData(false, -1, -1);
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

            if(SwingUtilities.isLeftMouseButton(event))
                setData(true, x, y);
            else if(SwingUtilities.isRightMouseButton(event))
                setData(false, x, y);

        }
    }

    private void setData(boolean isLeftClicked, int x, int y){
        if(isLeftClicked){
            if(data.inArea(x, y))
                data.open[x][y] = true;
        }
        else{
            if(data.inArea(x, y))
                data.flags[x][y] = true;
        }
        frame.setData(data);
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
            vis.addAlgoMouseListener();
            new Thread(() -> {
                vis.run();
            }).start();
        });
    }
}
