import java.awt.*;


public class AlgoVisualizer {

    private static int DELAY = 20;

    private ThreeWaysQuickSortData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N, ThreeWaysQuickSortData.Type dataType){

        // 初始化数据
        data = new ThreeWaysQuickSortData(N, sceneHeight, dataType);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Three Ways Quick Sort Visualization", sceneWidth, sceneHeight);

            new Thread(() -> {
                run();
            }).start();
        });
    }

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){
        this(sceneWidth, sceneHeight, N, ThreeWaysQuickSortData.Type.Default);
    }

    public void run(){

        setData(-1, -1, -1, -1, -1, -1);

        quickSort3Ways(0, data.N()-1);

        setData(-1, -1, -1, -1, -1, -1);
    }

    private void quickSort3Ways(int l, int r){

        if( l > r )
            return;

        if( l == r ) {
            setData(l, r, l, -1, -1, -1);
            return;
        }

        setData(l, r, -1, -1, -1, -1);

        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        int p = (int)(Math.random()*(r-l+1)) + l;
        setData(l, r, -1, p, -1, -1);

        data.swap(l, p);
        int v = data.get(l);
        setData(l, r, -1, l, -1, -1);

        int lt = l;     // arr[l+1...lt] < v
        int gt = r + 1; // arr[gt...r] > v
        int i = l+1;    // arr[lt+1...i) == v
        setData(l, r, -1, l, lt, gt);

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

            setData(l, r, -1, l, i, gt);
        }

        data.swap( l, lt );
        setData(l, r, lt, -1, -1, -1);

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

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;

        AlgoVisualizer vis = new AlgoVisualizer(sceneWidth, sceneHeight, N, ThreeWaysQuickSortData.Type.Default);
        // AlgoVisualizer vis = new AlgoVisualizer(sceneWidth, sceneHeight, N, ThreeWaysQuickSortData.Type.NearlyOrdered);
        // AlgoVisualizer vis = new AlgoVisualizer(sceneWidth, sceneHeight, N, ThreeWaysQuickSortData.Type.Identical);

    }
}