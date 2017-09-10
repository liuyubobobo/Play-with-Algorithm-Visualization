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
            drawFractal(g2d, 0, 0, canvasWidth, canvasHeight, 0);
        }

        private void drawFractal(Graphics2D g, int x, int y, int w, int h, int depth){

            int w_3 = w/3;
            int h_3 = h/3;

            if(w_3 <= 0 || h_3 <= 0)
                return;

            if( depth == data.depth ){
                AlgoVisHelper.setColor(g, AlgoVisHelper.Indigo);
                AlgoVisHelper.fillRectangle(g, x+w_3, y+h_3, w_3, h_3);
                return;
            }

            for(int i = 0 ; i < 3 ; i ++)
                for(int j = 0 ; j < 3 ; j ++)
                    if(i == 1 && j == 1){
                        AlgoVisHelper.setColor(g, AlgoVisHelper.Indigo);
                        AlgoVisHelper.fillRectangle(g, x+i*w_3, y+j*h_3, w_3, h_3);
                    }
                    else
                        drawFractal(g, x+i*w_3, y+j*h_3, w_3, h_3, depth+1);

            return;
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
