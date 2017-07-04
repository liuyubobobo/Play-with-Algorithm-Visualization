import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class AlgoVisualizer{

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
            AlgoVisHelper.pause(20);

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
            //System.out.println("Key released:" + event);
            if(event.getKeyChar() == ' ')
                isAnimated = !isAnimated;
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
            new Thread(() -> {
                vis.run();
            }).start();
        });
    }
}
