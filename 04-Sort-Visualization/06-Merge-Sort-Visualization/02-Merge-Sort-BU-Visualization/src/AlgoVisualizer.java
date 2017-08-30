import java.awt.*;
import java.util.Arrays;

public class AlgoVisualizer {

    private static int DELAY = 40;

    private MergeSortData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){

        // 初始化数据
        data = new MergeSortData(N, sceneHeight);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Merge Sort Visualization", sceneWidth, sceneHeight);

            new Thread(() -> {
                run();
            }).start();
        });
    }

    public void run(){

        setData(-1, -1, -1);

        for (int sz = 1; sz < data.N(); sz *= 2)
            for (int i = 0; i < data.N() - sz; i += sz+sz)
                // 对 arr[i...i+sz-1] 和 arr[i+sz...i+2*sz-1] 进行归并
                merge(i, i+sz-1, Math.min(i+sz+sz-1,data.N()-1));

        this.setData(0, data.N()-1, data.N()-1);
    }

    private void merge(int l, int mid, int r){

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
        }
    }

    private void setData(int l, int r, int mergeIndex){
        data.l = l;
        data.r = r;
        data.mergeIndex = mergeIndex;

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