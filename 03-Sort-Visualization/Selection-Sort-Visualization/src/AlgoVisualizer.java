import java.awt.*;
import java.util.Random;

public class AlgoVisualizer {

    private static int DELAY = 40;

    private SelectionSortData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int N, AlgoFrame frame, SelectionSortData data){

        this.frame = frame;
        this.data = data;
    }

    public void run(){

        this.setData(0,-1,-1);
        AlgoVisHelper.pause(DELAY);

        for( int i = 0 ; i < data.N() ; i ++ ){
            // 寻找[i, n)区间里的最小值的索引
            int minIndex = i;
            this.setData(i, -1, minIndex);
            for( int j = i + 1 ; j < data.N() ; j ++ ){
                this.setData(i, j, minIndex);
                AlgoVisHelper.pause(DELAY/4);
                if( data.get(j) < data.get(minIndex) ){
                    minIndex = j;
                    this.setData(i, j, minIndex);
                }
            }

            data.swap(i , minIndex);
            this.setData(i+1, -1, -1);
            AlgoVisHelper.pause(DELAY);
        }
    }

    private void setData(int orderedIndex, int currentCompareIndex, int currentMinIndex){
        data.orderedIndex = orderedIndex;
        data.currentCompareIndex = currentCompareIndex;
        data.currentMinIndex = currentMinIndex;
        frame.setData(data);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Selection Sort Visualization", sceneWidth,sceneHeight);

            int N = 200;
            // int N = 100;

            SelectionSortData data = new SelectionSortData(N, sceneHeight);
            AlgoVisualizer vis = new AlgoVisualizer(N, frame, data);
            new Thread(() -> {
                vis.run();
            }).start();
        });
    }
}