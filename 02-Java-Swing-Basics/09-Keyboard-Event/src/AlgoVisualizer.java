import java.awt.*;
import java.awt.event.*;

public class AlgoVisualizer{

    private Circle[] circles;
    private AlgoFrame frame;
    private boolean isAnimated = true;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){

        // 初始化数据
        circles = new Circle[N];
        int R = 50;
        for(int i = 0 ; i < N ; i ++ ) {
            int x = (int)(Math.random()*(sceneWidth-2*R)) + R;
            int y = (int)(Math.random()*(sceneHeight-2*R)) + R;
            int vx = (int)(Math.random()*11) - 5;
            int vy = (int)(Math.random()*11) - 5;
            circles[i] = new Circle(x, y, R, vx, vy);
        }

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    public void run(){

        while(true){
            // 绘制数据
            frame.render(circles);
            AlgoVisHelper.pause(20);

            // 更新数据
            if( isAnimated)
                for(Circle circle: circles)
                    circle.move(0, 0, frame.getCanvasWidth(), frame.getCanvasHeight());
        }
    }

    private class AlgoKeyListener extends KeyAdapter{

        @Override
        public void keyReleased(KeyEvent event){
            if(event.getKeyChar() == ' ')
                isAnimated = !isAnimated;
        }
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 10;

        AlgoVisualizer vis = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}
