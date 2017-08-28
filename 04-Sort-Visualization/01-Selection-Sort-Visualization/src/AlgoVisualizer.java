import java.awt.*;

public class AlgoVisualizer {

    private static int DELAY = 10;

    private SelectionSortData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){

        data = new SelectionSortData(N, sceneHeight);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Selection Sort Visualization", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run(){

        frame.render(data);
        AlgoVisHelper.pause(DELAY);

        for( int i = 0 ; i < data.N() ; i ++ ){
            // 寻找[i, n)区间里的最小值的索引
            int minIndex = i;
            for( int j = i + 1 ; j < data.N() ; j ++ ){

                frame.render(data);
                AlgoVisHelper.pause(DELAY);
                if( data.get(j) < data.get(minIndex) ){
                    minIndex = j;
                    frame.render(data);
                    AlgoVisHelper.pause(DELAY);
                }
            }

            data.swap(i , minIndex);
            frame.render(data);
            AlgoVisHelper.pause(DELAY);
        }

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;

        AlgoVisualizer vis = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}