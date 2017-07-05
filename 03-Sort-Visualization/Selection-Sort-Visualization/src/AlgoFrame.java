import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.RenderingHints;

import javax.swing.*;

public class AlgoFrame extends JFrame{

    private int canvasWidth;
    private int canvasHeight;
    private JPanel canvas;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight){

        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        setContentPane(canvas);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setVisible(true);
    }

    public AlgoFrame(String title){

        this(title, 1024, 768);
    }

    public int getCanvasWidth(){return canvasWidth;}
    public int getCanvasHeight(){return canvasHeight;}

    // data
    private int[] numbers;
    private int orderedIndex;   // [0...orderedIndex) 是有序的
    private int currentCompareIndex; // 当前正在比较的元素索引
    public void setNumbers(int[] numbers, int orderedIndex, int currentCompareIndex){
        this.numbers = numbers;
        this.orderedIndex = orderedIndex;
        this.currentCompareIndex = currentCompareIndex;
        repaint();
    }

    private class AlgoCanvas extends JPanel{

        public AlgoCanvas(){
            // 双缓存
            super(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D)g;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.addRenderingHints(hints);

            // 具体绘制
            int w = canvasWidth/numbers.length;
            //AlgoVisHelper.setColor(g2d, AlgoVisHelper.Grey);
            for(int i = 0 ; i < numbers.length ; i ++ ) {
                if (i < orderedIndex)
                    AlgoVisHelper.setColor(g2d, AlgoVisHelper.Red);
                else
                    AlgoVisHelper.setColor(g2d, AlgoVisHelper.Grey);

                if( currentCompareIndex != -1 && i == currentCompareIndex )
                    AlgoVisHelper.setColor(g2d, AlgoVisHelper.LightBlue);

                AlgoVisHelper.fillRectangle(g2d, i * w, canvasHeight - numbers[i], w - 1, numbers[i]);
            }
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}