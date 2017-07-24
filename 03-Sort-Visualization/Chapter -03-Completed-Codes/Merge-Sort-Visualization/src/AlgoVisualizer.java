import java.awt.*;
import java.util.Random;
import java.util.Arrays;

public class AlgoVisualizer {

    private static int DELAY = 40;

    private MergeSortData data;
    private AlgoFrame frame;

    public AlgoVisualizer(AlgoFrame frame, MergeSortData data){

        this.frame = frame;
        this.data = data;

        this.setData(-1, -1, -1);
    }

    public void run(){

        mergeSort(0, data.N()-1);

        this.setData(0, data.N(), data.N());
        AlgoVisHelper.pause(DELAY);
    }

    private void mergeSort(int l, int r){

        if( l >= r )
            return;

        setData(l, r, -1);
        AlgoVisHelper.pause(DELAY);

        int mid = (l+r)/2;
        mergeSort(l, mid);
        mergeSort(mid+1, r);
        merge(l, mid, r);
    }

    private void merge(int l, int mid, int r){

        setData(l, r, -1);
        AlgoVisHelper.pause(DELAY);

        int[] aux = Arrays.copyOfRange(data.numbers, l, r+1);

        // 初始化，i指向左半部分的起始索引位置l；j指向右半部分起始索引位置mid+1
        int i = l, j = mid+1;
        for( int k = l ; k <= r; k ++ ){

            if( i > mid ){  // 如果左半部分元素已经全部处理完毕
                data.numbers[k] = aux[j-l]; j ++;
            }
            else if( j > r ){   // 如果右半部分元素已经全部处理完毕
                data.numbers[k] = aux[i-l]; i ++;
            }
            else if( aux[i-l] < aux[j-l] ){  // 左半部分所指元素 < 右半部分所指元素
                data.numbers[k] = aux[i-l]; i ++;
            }
            else{  // 左半部分所指元素 >= 右半部分所指元素
                data.numbers[k] = aux[j-l]; j ++;
            }

            setData(l, r, k);
            AlgoVisHelper.pause(DELAY);
        }

        setData(l, r, -1);
        AlgoVisHelper.pause(DELAY);
    }

    private void setData(int l, int r, int mergeIndex){
        data.l = l;
        data.r = r;
        data.mergeIndex = mergeIndex;
        frame.setData(data);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Merge Sort Visualization", sceneWidth,sceneHeight);

            int N = 200;
            // int N = 100;

            MergeSortData data = new MergeSortData(N, sceneHeight);
            AlgoVisualizer vis = new AlgoVisualizer(frame, data);
            new Thread(() -> {
                vis.run();
            }).start();
        });
    }
}