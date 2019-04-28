
package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.List;

import javax.swing.JPanel;

public class StatisticsChart extends JPanel
{
   private ArrayList<Integer> data;
   private static final int maxNum = 15;
   private static final int gap = 20;
   private static final int screenSize = 400;
   private static final int lineWidth = 10;

   public StatisticsChart() {
      data = new ArrayList<Integer>();
   }

   public void add(int value) {
      data.add(value);
   }

   @Override
   protected void paintComponent(Graphics graphics) {
      super.paintComponent(graphics); 
      Graphics2D graphics2D = (Graphics2D)graphics;
      graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      
      double xScale = ((double) screenSize * gap) / (data.size() - 1);
      double yScale = ((double) screenSize * gap) / (maxNum - 1);

      List<Point> graphPoints = new ArrayList<>();
      for (int i = 0; i < data.size(); i++) {
         int x1 = (int) (i * xScale + gap);
         int y1 = (int) ((maxNum - data.get(i)) * yScale + gap);
         graphPoints.add(new Point(x1, y1));
      }

      // draw the graph lines ( x and y axes )
      graphics2D.drawLine(gap, screenSize - gap, gap, gap);
      graphics2D.drawLine(gap, screenSize - gap, screenSize - gap, screenSize - gap);

      // add side lines on Y axis.
      for (int i = 0; i < 10; i++) {
         int x0 = gap;
         int x1 = lineWidth + gap;
         int y0 = screenSize - (((i + 1) * (screenSize - gap )) / 10 + gap);
         int y1 = y0;
         graphics2D.drawLine(x0, y0, x1, y1);
      }

      // add side lines on X axis.
      for (int i = 0; i < 10; i++) {
         int x0 = 400 - (((i + 1) * (screenSize - gap )) / 10 + gap);
         int x1 = x0;
         int y0 = 400 - gap;
         int y1 = y0 - lineWidth;
         graphics2D.drawLine(x0, y0, x1, y1);
      }

      // draw the graph itself 
      graphics2D.setColor(Color.BLUE);
      for (int i = 0; i < graphPoints.size() - 1; i++) {
         int x1 = graphPoints.get(i).x;
         int y1 = graphPoints.get(i).y;
         int x2 = graphPoints.get(i + 1).x;
         int y2 = graphPoints.get(i + 1).y;
         graphics2D.drawLine(x1, y1, x2, y2);         
      }
   }
}