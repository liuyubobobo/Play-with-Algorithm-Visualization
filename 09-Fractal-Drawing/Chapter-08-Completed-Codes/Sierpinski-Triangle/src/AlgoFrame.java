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
            drawFractal(g2d, 0, canvasWidth, canvasHeight, 0);
        }

        private void drawFractal(Graphics2D g, int Ax, int Ay, int side, int depth){

            if(side <= 1){
                AlgoVisHelper.setColor(g, AlgoVisHelper.Indigo);
                AlgoVisHelper.fillRectangle(g, Ax, Ay, 1, 1);
                return;
            }

            int Bx = Ax + side;
            int By = Ay;

            int h = (int)(Math.sin(60.0*Math.PI/180.0)*side);
            int Cx = Ax + side/2;
            int Cy = Ay - h;

            if( depth == data.depth ){
                AlgoVisHelper.setColor(g, AlgoVisHelper.Indigo);
                AlgoVisHelper.fillTriangle(g, Ax, Ay, Bx, By, Cx, Cy);
                return;
            }

            int AB_centerx = (Ax+Bx)/2;
            int AB_centery = (Ay+By)/2;

            int AC_centerx = (Ax+Cx)/2;
            int AC_centery = (Ay+Cy)/2;

            int BC_centerx = (Bx+Cx)/2;
            int BC_centery = (By+Cy)/2;

            drawFractal(g, Ax, Ay, side/2, depth+1);
            drawFractal(g, AC_centerx, AC_centery, side/2, depth+1);
            drawFractal(g, AB_centerx, AB_centery, side/2, depth+1);

            return;
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
