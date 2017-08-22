public class SocietyData {

    private int N;
    public int money[];

    public SocietyData(int N, int sceneHeight){

        this.N = N;
        money = new int[N];
        for(int i = 0 ; i < N ; i ++) {
            money[i] = sceneHeight/3;
        }
    }

    public int N(){ return N; }
}
