import java.awt.*;
import java.util.Random;
import java.util.Arrays;

public class AlgoVisualizer {

    private static int DELAY = 40;

    private HeapSortData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int N, AlgoFrame frame, HeapSortData data){

        this.frame = frame;
        this.data = data;
    }

    public void run(){

        this.setData(data.N());
        AlgoVisHelper.pause(DELAY);

        heapSort();

        this.setData(0);
        AlgoVisHelper.pause(DELAY);
    }

    private void heapSort(){

        for( int i = (data.N()-1-1)/2 ; i >= 0 ; i -- )
            shiftDown(data.N(), i);

        for( int i = data.N()-1; i > 0 ; i-- ){
            data.swap(0, i);
            shiftDown(i, 0);
            setData(i);
            AlgoVisHelper.pause(DELAY);
        }

        setData(0);
        AlgoVisHelper.pause(DELAY);
    }

    private void shiftDown(int n, int k){

        while( 2*k+1 < n ){
            int j = 2*k+1;
            if( j+1 < n && data.get(j+1) > data.get(j) )
                j += 1;

            if( data.get(k) >= data.get(j) )
                break;

            data.swap(k, j);
            setData(-1);
            AlgoVisHelper.pause(DELAY);
            k = j;
        }
    }

    private void setData(int heapIndex){
        if(heapIndex != -1)
            data.heapIndex = heapIndex;
        frame.setData(data);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Three Ways Quick Sort Visualization", sceneWidth,sceneHeight);

            int N = 200;
            // int N = 100;

            HeapSortData data = new HeapSortData(N, sceneHeight, false);
            // HeapSortData data = new HeapSortData(N, sceneHeight, true);
            // HeapSortData data = new HeapSortData(N, sceneHeight/2 - 5, sceneHeight/2 + 5);
            AlgoVisualizer vis = new AlgoVisualizer(N, frame, data);
            new Thread(() -> {
                vis.run();
            }).start();
        });
    }
}