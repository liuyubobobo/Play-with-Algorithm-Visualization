import java.util.Arrays;


public class QuickSortData {

    public enum Type{
        Default,
        NearlyOrdered
    }

    private int[] numbers;
    public int l, r;
    public boolean[] fixedPivots;
    public int curPivot;
    public int curElement;

    public QuickSortData(int N, int randomBound, Type dataType){

        numbers = new int[N];
        fixedPivots = new boolean[N];

        for( int i = 0 ; i < N ; i ++) {
            numbers[i] = (int)(Math.random()*randomBound) + 1;
            fixedPivots[i] = false;
        }

        if(dataType == Type.NearlyOrdered){
            Arrays.sort(numbers);
            int swapTime = (int)(0.01*N);
            for(int i = 0 ; i < swapTime; i ++){
                int a = (int)(Math.random()*N);
                int b = (int)(Math.random()*N);
                swap(a, b);
            }
        }
    }

    public QuickSortData(int N, int randomBound){
        this(N, randomBound, Type.Default);
    }

    public int N(){
        return numbers.length;
    }

    public int get(int index){
        if( index < 0 || index >= numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data.");

        return numbers[index];
    }

    public void swap(int i, int j) {

        if( i < 0 || i >= numbers.length || j < 0 || j >= numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data.");

        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }
}