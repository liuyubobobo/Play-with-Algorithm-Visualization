import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class AlgoVisualizer{

    private static int DELAY = 20;

    private int N;
    private Circle[] circles;
    private static int R = 50;
    private AlgoFrame frame;
    private boolean isAnimated = true;

    public AlgoVisualizer(int N, AlgoFrame frame){
        this.N = N;
        this.frame = frame;

        circles = new Circle[N];

        Random rand = new Random();
        for(int i = 0 ; i < N ; i ++ ) {
            int x = rand.nextInt(frame.getCanvasWidth()-2*R) + R;
            int y = rand.nextInt(frame.getCanvasHeight()-2*R) + R;
            int vx = rand.nextInt(11) - 5;
            int vy = rand.nextInt(11) - 5;
            circles[i] = new Circle(x, y, R, vx, vy);
        }
    }

    public void run(){

        while(true){
            // 绘制数据
            frame.setCircles(circles);
            AlgoVisHelper.pause(DELAY);

            // 更新数据
            if( isAnimated)
                for(Circle circle: circles)
                    circle.move(0, 0, frame.getCanvasWidth(), frame.getCanvasHeight());
        }
    }

    public void addAlgoKeyListener(){
        frame.addKeyListener(new AlgoKeyListener());
    }

    private class AlgoKeyListener extends KeyAdapter{

        @Override
        public void keyReleased(KeyEvent event){
            if(event.getKeyChar() == ' ')
                isAnimated = !isAnimated;
        }
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
            //System.out.println(event.getPoint());

            for(Circle circle : circles)
                if(circle.contain(event.getPoint()))
                    circle.isFilled = !circle.isFilled;
        }
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Welcome", sceneWidth,sceneHeight);

            int N = 10;
            AlgoVisualizer vis = new AlgoVisualizer(N, frame);
            vis.addAlgoKeyListener();
            vis.addAlgoMouseListener();
            new Thread(() -> {
                vis.run();
            }).start();
        });
    }
}
