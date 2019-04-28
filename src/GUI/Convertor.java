
package GUI;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import Orders.CurrencyConverter;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Convertor extends JFrame{
    CurrencyConverter convert = new CurrencyConverter();
    double Amount;
    public Convertor() throws IOException {
        convert.getCurrencyDatabase();
        JComboBox curr = new JComboBox(convert.currencyName);
        JPanel panel = new JPanel(null);
        JLabel background = new JLabel(new ImageIcon("images\\backgroud.jpg"));
        background.setLayout( new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setContentPane(background);
        setResizable(false);
        setSize(300,250);
        setTitle("Convertor");
        
        JTextField amount = new JTextField();
        amount.setBounds(50, 30, 200, 20);
        panel.add(amount);
        
        curr.setBounds(50, 80, 200, 20);
        panel.add(curr);
        
        JTextField total = new JTextField();
        total.setBounds(50, 130, 200, 20);
        total.setEditable(false);
        panel.add(total);
        
        JButton btn = new JButton("Convert");
        btn.setBounds(100, 180, 100, 25);
        panel.add(btn);

        btn.addActionListener((ActionEvent ae) -> {
            String Ammount = amount.getText();
            Amount = Double.parseDouble(Ammount);
            String the3omla = (String) curr.getSelectedItem();
            System.out.println(the3omla + " " + Amount);
            total.setText(String.valueOf(convert.calculateCurrency(Amount, the3omla)));
        });
        add(panel,BorderLayout.CENTER);
    }
}