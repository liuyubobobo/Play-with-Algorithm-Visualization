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

        super();

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        canvas = new AlgoCanvas();
        canvas.setPreferredSize(new Dimension(canvasWidth,canvasHeight));
        getContentPane().add(canvas);
        pack();

        //canvas.setDoubleBuffered(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle(title);

        setVisible(true);
    }

    public AlgoFrame(String title){

        this(title, 1024, 768);
    }

    public int getCanvasWidth(){return canvasWidth;}
    public int getCanvasHeight(){return canvasHeight;}

    // data
    private Circle[] circles;
    public void setCircles(Circle[] circles){
        this.circles = circles;
        repaint();
    }

    private class AlgoCanvas extends JPanel{

        public AlgoCanvas(){
            super(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            //System.out.println("paint");
            Graphics2D g2d = (Graphics2D)g;

            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHints(hints);

            // draw
            AlgoVisHelper.setStrokeWidth(g2d,1);
            AlgoVisHelper.setColor(g2d, Color.RED);
            for(int i = 0 ; i < circles.length; i ++)
                AlgoVisHelper.strokeCircle(g2d, circles[i].x, circles[i].y, circles[i].r);
        }

    }
}


