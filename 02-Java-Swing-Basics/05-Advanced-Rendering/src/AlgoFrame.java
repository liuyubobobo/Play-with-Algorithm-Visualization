import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AlgoFrame extends JFrame{

    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight){

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        canvas.setPreferredSize(new Dimension(canvasWidth,canvasHeight));
        setContentPane(canvas);
        pack();

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


//    private class AlgoCanvas extends JPanel{
//
//        @Override
//        public void paint(Graphics g) {
//            super.paint(g);
//
//            Graphics2D g2d = (Graphics2D)g;
//
//            RenderingHints hints = new RenderingHints(
//                                            RenderingHints.KEY_ANTIALIASING,
//                                            RenderingHints.VALUE_ANTIALIAS_ON);
//            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//            g2d.addRenderingHints(hints);
//
//            g2d.setPaint(Color.RED);
//            int strokeWidth = 10;
//            g2d.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
//            strokeCircle(g2d, canvasWidth/2,canvasHeight/2,300);
//
//            g2d.setPaint(Color.BLUE);
//            fillCircle(g2d, canvasWidth/2,canvasHeight/2,300);
//        }
//
//        private void strokeCircle(Graphics2D g, int x, int y, int r){
//
//            Ellipse2D circle = new Ellipse2D.Double(x-r, y-r, 2*r, 2*r);
//            g.draw(circle);
//        }
//
//        private void fillCircle(Graphics2D g, int x, int y, int r){
//
//            Ellipse2D circle = new Ellipse2D.Double(x-r, y-r, 2*r, 2*r);
//            g.fill(circle);
//        }
//    }

    private class AlgoCanvas extends JPanel{

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            Graphics2D g2d = (Graphics2D)g;

            RenderingHints hints = new RenderingHints(
                                            RenderingHints.KEY_ANTIALIASING,
                                            RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.addRenderingHints(hints);

            AlgoVisHelper.setStrokeWidth(g2d,10);
            AlgoVisHelper.setColor(g2d, Color.RED);
            AlgoVisHelper.strokeCircle(g2d, canvasWidth/2,canvasHeight/2,300);

            AlgoVisHelper.setColor(g2d, Color.BLUE);
            AlgoVisHelper.fillCircle(g2d, canvasWidth/2,canvasHeight/2,300);
        }


    }
}


