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

        this.setData(-1, -1, -1, -1, -1, -1);
        AlgoVisHelper.pause(DELAY);

        quickSort2Ways(0, data.N()-1);

        this.setData(0, data.N()-1, -1, -1, -1, -1);
        AlgoVisHelper.pause(DELAY);
    }

    private void quickSort2Ways(int l, int r){

        if( l > r )
            return;

        if( l == r ) {
            setData(l, r, l, -1, -1, -1);
            AlgoVisHelper.pause(DELAY);
            return;
        }

        setData(l, r, -1, -1, -1, -1);
        AlgoVisHelper.pause(DELAY);

        int p = partition(l, r);
        quickSort2Ways(l, p-1 );
        quickSort2Ways(p+1, r);
    }

    private int partition(int l, int r){

        int p = (int)(Math.random()*(r-l+1)) + l;
        data.swap(l, p);
        setData(l, r, -1, l, -1, -1);
        AlgoVisHelper.pause(DELAY);

        int v = data.get(l);

        // arr[l+1...i) <= v; arr(j...r] >= v
        int i = l+1, j = r;
        setData(l, r, -1, l, i, j);
        AlgoVisHelper.pause(DELAY);
        while( true ){
            while( i <= r && data.get(i) < v ){
                i ++;
                setData(l, r, -1, l, i, j);
                AlgoVisHelper.pause(DELAY);
            }

            while( j >= l+1 && data.get(j) > v ){
                j --;
                setData(l, r, -1, l, i, j);
                AlgoVisHelper.pause(DELAY);
            }

            if( i > j )
                break;

            data.swap( i, j );
            i ++;
            j --;
            setData(l, r, -1, l, i, j);
            AlgoVisHelper.pause(DELAY);
        }

        data.swap(l, j);
        setData(l, r, j, -1, -1, -1);
        AlgoVisHelper.pause(DELAY);

        return j;
    }

    private void setData(int l, int r, int fixedPivot, int curPivot, int curL, int curR){
        data.l = l;
        data.r = r;
        if(fixedPivot != -1)
            data.fixedPivots[fixedPivot] = true;
        data.curPivot = curPivot;
        data.curL = curL;
        data.curR = curR;
        frame.setData(data);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Two Ways Quick Sort Visualization", sceneWidth,sceneHeight);

            int N = 200;
            // int N = 100;

            // TwoWaysQuickSortData data = new TwoWaysQuickSortData(N, sceneHeight, true);
            // TwoWaysQuickSortData data = new TwoWaysQuickSortData(N, sceneHeight, false);
            TwoWaysQuickSortData data = new TwoWaysQuickSortData(N, sceneHeight/2 - 5, sceneHeight/2 + 5);
            AlgoVisualizer vis = new AlgoVisualizer(N, frame, data);
            new Thread(() -> {
                vis.run();
            }).start();
        });
    }
}