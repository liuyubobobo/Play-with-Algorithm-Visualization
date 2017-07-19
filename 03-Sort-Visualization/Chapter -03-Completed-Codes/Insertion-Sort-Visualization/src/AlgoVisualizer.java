import java.awt.*;
import java.util.Random;

public class AlgoVisualizer {

    private static int DELAY = 40;

    private InsertionSortData data;
    private AlgoFrame frame;

    public AlgoVisualizer(AlgoFrame frame, InsertionSortData data){

        this.frame = frame;
        this.data = data;
    }

    public void run(){

        this.setData(0,-1);
        AlgoVisHelper.pause(DELAY);
        for( int i = 0 ; i < data.N() ; i ++ ){

            this.setData(i, i);
            AlgoVisHelper.pause(DELAY);
            for(int j = i ; j > 0 && data.get(j) < data.get(j-1) ; j --){
                data.swap(j,j-1);
                this.setData(i, j-1);
                AlgoVisHelper.pause(DELAY);
            }
        }
        this.setData(data.N(), -1);
        AlgoVisHelper.pause(DELAY);
    }

    private void setData(int orderedIndex, int currentIndex){
        data.orderedIndex = orderedIndex;
        data.currentIndex = currentIndex;
        frame.setData(data);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Insertion Sort Visualization", sceneWidth,sceneHeight);

            int N = 200;
            // int N = 100;

            InsertionSortData data = new InsertionSortData(N, sceneHeight, false);
            //InsertionSortData data = new InsertionSortData(N, sceneHeight, true);
            AlgoVisualizer vis = new AlgoVisualizer(frame, data);
            new Thread(() -> {
                vis.run();
            }).start();
        });
    }
}