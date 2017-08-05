import java.awt.*;
import java.util.Random;
import java.util.Arrays;

public class AlgoVisualizer {

    private static int DELAY = 40;

    private ThreeWaysQuickSortData data;
    private AlgoFrame frame;

    public AlgoVisualizer(AlgoFrame frame, ThreeWaysQuickSortData data){

        this.frame = frame;
        this.data = data;

        this.setData(-1, -1, -1, -1, -1, -1);
    }

    public void run(){

        quickSort3Ways(0, data.N()-1);

        this.setData(0, data.N()-1, -1, -1, -1, -1);
        AlgoVisHelper.pause(DELAY);
    }

    private void quickSort3Ways(int l, int r){

        if( l > r )
            return;

        if( l == r ) {
            setData(l, r, l, -1, -1, -1);
            AlgoVisHelper.pause(DELAY);
            return;
        }

        setData(l, r, -1, -1, -1, -1);
        AlgoVisHelper.pause(DELAY);

        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        int p = (int)(Math.random()*(r-l+1)) + l;
        data.swap(l, p);

        int v = data.get(l);

        int lt = l;     // arr[l+1...lt] < v
        int gt = r + 1; // arr[gt...r] > v
        int i = l+1;    // arr[lt+1...i) == v
        setData(l, r, -1, l, lt, gt);
        AlgoVisHelper.pause(DELAY);

        while( i < gt ){
            if( data.get(i) < v ){
                data.swap( i, lt+1);
                i ++;
                lt ++;
            }
            else if( data.get(i) > v ){
                data.swap( i, gt-1);
                gt --;
            }
            else // arr[i] == v
                i ++;

            setData(l, r, -1, l, lt, gt);
            AlgoVisHelper.pause(DELAY);
        }

        data.swap( l, lt );
        setData(l, r, lt, -1, -1, -1);
        AlgoVisHelper.pause(DELAY);

        quickSort3Ways(l, lt-1 );
        quickSort3Ways(gt, r);
    }

    private void setData(int l, int r, int fixedPivot, int curPivot, int curL, int curR){
        data.l = l;
        data.r = r;
        if(fixedPivot != -1){
            data.fixedPivots[fixedPivot] = true;
            int i = fixedPivot;
            while(i < data.N() && data.get(i) == data.get(fixedPivot)){
                data.fixedPivots[i] = true;
                i ++;
            }
        }
        data.curPivot = curPivot;
        data.curL = curL;
        data.curR = curR;
        frame.setData(data);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Three Ways Quick Sort Visualization", sceneWidth,sceneHeight);

            int N = 200;
            // int N = 100;

            // ThreeWaysQuickSortData data = new ThreeWaysQuickSortData(N, sceneHeight, false);
            // ThreeWaysQuickSortData data = new ThreeWaysQuickSortData(N, sceneHeight, true);
            ThreeWaysQuickSortData data = new ThreeWaysQuickSortData(N, sceneHeight/2 - 5, sceneHeight/2 + 5);
            AlgoVisualizer vis = new AlgoVisualizer(frame, data);
            new Thread(() -> {
                vis.run();
            }).start();
        });
    }
}