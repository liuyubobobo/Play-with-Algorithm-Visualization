import java.awt.*;

public class AlgoVisualizer {

    private GameData data;
    private AlgoFrame frame;

    public AlgoVisualizer(String filename){

        data = new GameData(filename);

    }

    public static void main(String[] args) {

        String filename = "level/boston_09.txt";

        AlgoVisualizer vis = new AlgoVisualizer(filename);
    }
}
