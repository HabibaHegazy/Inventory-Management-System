
package GUI;

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ChartComponents extends JComponent
{
   public int v1=1,v2=1,v3=1,v4=1,v5=1;

   public void paintComponent(Graphics g) {
      Graphics2D g2 = (Graphics2D) g;
      StatisticsChart chart = new StatisticsChart();
      chart.add(1);
      chart.add(v1);
      chart.add(v2);
      chart.add(v3);
      chart.add(v4);
      chart.add(v5);
      chart.paintComponent(g2);
   }
   
   public void graphFrame(){
      JFrame frame = new JFrame();
      ChartComponents component = new ChartComponents();
      JSpinner spin1 = new JSpinner(new SpinnerNumberModel(1, 1, 15, 1));  
      spin1.setBounds(50,30,50,30);
      frame.add(spin1);
      JSpinner spin2 = new JSpinner(new SpinnerNumberModel(1, 1, 15, 1));  
      spin2.setBounds(100,30,50,30);
      frame.add(spin2);
      JSpinner spin3 = new JSpinner(new SpinnerNumberModel(1, 1, 15, 1));  
      spin3.setBounds(150,30,50,30);
      frame.add(spin3);
      JSpinner spin4 = new JSpinner(new SpinnerNumberModel(1, 1, 15, 1));  
      spin4.setBounds(200,30,50,30);
      frame.add(spin4);
      JSpinner spin5 = new JSpinner(new SpinnerNumberModel(1, 1, 15, 1));  
      spin5.setBounds(250,30,50,30);
      frame.add(spin5);
      JButton draw = new JButton("Draw");
      draw.setBounds(300,30,70,30);
      frame.add(draw);
      
      frame.setSize(400, 450);
      frame.setTitle("Statistics Graph");
      frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

      frame.add(component);
      frame.setVisible(true);
      frame.setResizable(false);
      
      draw.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent ae) {
              component.v1 = (int) spin1.getValue();
              component.v2 = (int) spin2.getValue();
              component.v3 = (int) spin3.getValue();
              component.v4 = (int) spin4.getValue();
              component.v5 = (int) spin5.getValue();
              frame.setVisible(false);
              frame.setVisible(true);
          }
      });
   }
}