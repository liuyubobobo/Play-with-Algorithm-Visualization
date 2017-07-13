import java.awt.*;
import java.util.Random;
import java.util.Arrays;

public class AlgoVisualizer {

    private static int DELAY = 40;

    private TwoWaysQuickSortData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int N, AlgoFrame frame, TwoWaysQuickSortData data){

        this.frame = frame;
        this.data = data;
    }

    public void run(){

        this.setData(-1, -1, -1, -1, -1);
        AlgoVisHelper.pause(DELAY);

        quickSort2Ways(0, data.N()-1);

        this.setData(0, data.N()-1, -1, -1, -1);
        AlgoVisHelper.pause(DELAY);
    }

    private void quickSort2Ways(int l, int r){

        if( l > r )
            return;

        setData(l, r, l, -1, -1);
        AlgoVisHelper.pause(DELAY);

        if( l == r )
            return;

        int p = partition(l, r);
        quickSort2Ways(l, p-1 );
        quickSort2Ways(p+1, r);
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
            AlgoFrame frame = new AlgoFrame("Two Ways Quick Sort Visualization", sceneWidth,sceneHeight);

            int N = 200;
            // int N = 100;

            TwoWaysQuickSortData data = new TwoWaysQuickSortData(N, sceneHeight, true);
            AlgoVisualizer vis = new AlgoVisualizer(N, frame, data);
            new Thread(() -> {
                vis.run();
            }).start();
        });
    }
}