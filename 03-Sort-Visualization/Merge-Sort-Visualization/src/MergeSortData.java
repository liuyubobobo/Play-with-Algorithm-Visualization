import java.util.Arrays;
import java.util.Random;


public class MergeSortData {

    private int N;

    public int[] numbers;
    public int l, r;
    public int mergeIndex;

    public MergeSortData(int N, int randomBound){
        this.N = N;

        numbers = new int[N];

        for( int i = 0 ; i < N ; i ++)
            numbers[i] = (int)(Math.random()*randomBound) + 1;
    }

    public int N(){
        return N;
    }

    public int get(int index){
        if( index < 0 || index >= numbers.length)
            throw new IllegalArgumentException("Invalid index to access Selection Sort Data.");

        return numbers[index];
    }

    public void swap(int i, int j) {
        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }
}
