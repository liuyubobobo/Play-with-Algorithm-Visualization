import java.awt.*;
import java.util.Random;
import java.util.Arrays;

public class AlgoVisualizer {

    private static int DELAY = 40;

    private QuickSortData data;
    private AlgoFrame frame;

    public AlgoVisualizer(AlgoFrame frame, QuickSortData data){

        this.frame = frame;
        this.data = data;

        this.setData(-1, -1, -1, -1, -1);
    }

    public void run(){

        quickSort(0, data.N()-1);

        this.setData(0, data.N()-1, -1, -1, -1);
        AlgoVisHelper.pause(DELAY);
    }

    private void quickSort(int l, int r){

//        if( l >= r )
//            return;

        if( l > r )
            return;

        if( l == r ){
            setData(l, r, l, -1, -1);
            AlgoVisHelper.pause(DELAY);
            return;
        }

        setData(l, r, -1, -1, -1);
        AlgoVisHelper.pause(DELAY);

        int p = partition(l, r);
        quickSort(l, p-1 );
        quickSort(p+1, r);
    }

    private int partition(int l, int r){

        int p = (int)(Math.random()*(r-l+1)) + l;
        data.swap(l, p);
        setData(l, r, -1, l, p);
        AlgoVisHelper.pause(DELAY);

        int v = data.get(l);
        setData(l, r, -1, l, -1);
        AlgoVisHelper.pause(DELAY);

        int j = l; // arr[l+1...j] < v ; arr[j+1...i) > v
        for( int i = l + 1 ; i <= r ; i ++ ) {
            setData(l, r, -1, l, i);
            AlgoVisHelper.pause(DELAY);
            if (data.get(i) < v) {
                j++;
                data.swap(j, i);
                setData(l, r, -1, l, i);
                AlgoVisHelper.pause(DELAY);
            }
        }

        data.swap(l, j);
        setData(l, r, j, -1, -1);
        AlgoVisHelper.pause(DELAY);

        return j;
    }

    private void setData(int l, int r, int fixedPivot, int curPivot, int curElement){
        data.l = l;
        data.r = r;
        if(fixedPivot != -1)
            data.fixedPivots[fixedPivot] = true;
        data.curPivot = curPivot;
        data.curElement = curElement;
        frame.setData(data);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Quick Sort Visualization", sceneWidth,sceneHeight);

            int N = 200;
            // int N = 100;

            QuickSortData data = new QuickSortData(N, sceneHeight, true);
            AlgoVisualizer vis = new AlgoVisualizer(frame, data);
            new Thread(() -> {
                vis.run();
            }).start();
        });
    }
}