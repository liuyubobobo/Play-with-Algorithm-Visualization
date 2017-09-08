import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.RenderingHints;
import javax.swing.*;

public class AlgoFrame extends JFrame{

    private int canvasWidth;
    private int canvasHeight;

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
    private FractalData data;
    public void render(FractalData data){
        this.data = data;
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
            drawFractal(g2d, 0, canvasHeight-3, canvasWidth, 0, 0);
        }

        private void drawFractal(Graphics2D g, double x1, double y1, double side, double angle, int depth){

            if(side <= 0)
                return;

            if( depth == data.depth ){
                double x2 = x1 + side * Math.cos(angle*Math.PI/180.0);
                double y2 = y1 - side * Math.sin(angle*Math.PI/180.0);
                AlgoVisHelper.setColor(g, AlgoVisHelper.Indigo);
                AlgoVisHelper.drawLine(g, x1, y1, x2, y2);
                return;
            }

            double side_3 = side / 3;

            double x2 = x1 + side_3 * Math.cos(angle*Math.PI/180.0);
            double y2 = y1 - side_3 * Math.sin(angle*Math.PI/180.0);
            drawFractal(g, x1, y1, side_3, angle, depth+1);

            double x3 = x2 + side_3 * Math.cos((angle+60.0)*Math.PI/180.0);
            double y3 = y2 - side_3 * Math.sin((angle+60.0)*Math.PI/180.0);
            drawFractal(g, x2, y2, side_3, angle+60.0, depth+1);

            double x4 = x3 + side_3 * Math.cos((angle-60.0)*Math.PI/180.0);
            double y4 = y3 - side_3 * Math.sin((angle-60.0)*Math.PI/180.0);
            drawFractal(g, x3, y3, side_3, angle-60.0, depth+1);

            double x5 = x4 + side_3 * Math.cos(angle*Math.PI/180.0);
            double y5 = y4 - side_3 * Math.sin(angle*Math.PI/180.0);
            drawFractal(g, x4, y4, side_3, angle, depth+1);

            return;
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
