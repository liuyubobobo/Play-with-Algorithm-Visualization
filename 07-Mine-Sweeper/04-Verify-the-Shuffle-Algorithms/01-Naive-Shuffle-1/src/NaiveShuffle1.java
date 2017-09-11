
public class NaiveShuffle1 {

    private int N;
    private int n, m;

    public NaiveShuffle1(int N, int n, int m){

        if(N <= 0)
            throw new IllegalArgumentException("N must be larger than 0!");

        if(n < m)
            throw new IllegalArgumentException("n must be larger than or equal to m!");

        this.N = N;
        this.n = n;
        this.m = m;
    }

    public void run(){

        int freq[] = new int[n];

        int arr[] = new int[n];
        for(int i = 0 ; i < N ; i ++){

            reset(arr);
            shuffle(arr);
            for(int j = 0 ; j < n ; j ++)
                freq[j] += arr[j];
        }

        for(int i = 0 ; i < n ; i ++){
            System.out.println(i + " : " + (double)freq[i]/N);
        }
    }

    private void reset(int[] arr){

        for(int i = 0 ; i < m ; i ++)
            arr[i] = 1;

        for(int i = m ; i < n ; i ++)
            arr[i] = 0;

        return;
    }

    private void shuffle(int[] arr){

        for(int i = 0 ; i < n ; i ++){
            int x = (int)(Math.random() * n);
            swap(arr, i, x);
        }
    }

    private void swap(int[] arr, int x, int y){

        int t = arr[x];
        arr[x] = arr[y];
        arr[y] = t;
    }

    public static void main(String[] args) {

        int N = 10000000;
        NaiveShuffle1 exp = new NaiveShuffle1(N, 10, 5);
        exp.run();

    }
}
