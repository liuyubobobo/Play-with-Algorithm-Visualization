import java.util.Random;


public class InsertionSortData {

    private int N;

    private int[] numbers;
    public int orderedIndex;           // [0...orderedIndex) 是有序的
    public int currentIndex;

    public InsertionSortData(int N, int randomBound){
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
            throw new IllegalArgumentException("Invalid index to access Sort Data.");

        return numbers[index];
    }

    public void swap(int i, int j) {
        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }
}
