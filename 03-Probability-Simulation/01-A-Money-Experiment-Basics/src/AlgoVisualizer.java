import java.awt.*;

public class AlgoVisualizer {

    private static int DELAY = 10;
    private int[] money;
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){

        // 初始化数据
        money = new int[N];
        for(int i = 0 ; i < N ; i ++) {
            money[i] = sceneHeight/3;
        }

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Money Problem", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    public void run(){

        while(true){

            int i = (int)(Math.random() * money.length);
            int j = (int)(Math.random() * money.length);

            if(money[i] > 0){
                money[i] -= 1;
                money[j] += 1;
            }

            frame.render(money);
            AlgoVisHelper.pause(DELAY);
        }
    }

    public static void main(String[] args) {

        int sceneWidth = 1200;
        int sceneHeight = 840;
        int N = 120;

        AlgoVisualizer vis = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}