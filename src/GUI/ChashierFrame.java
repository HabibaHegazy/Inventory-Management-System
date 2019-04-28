package GUI;

import static inventorysytem.InventorySytem.report;
import NetworkChat.Client;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ChashierFrame extends JFrame{
    
    JComboBox pay = new JComboBox(new String []{"Cash","Visa","Check"});
    Object columnNames[] = { "Column no","Column One", "Column Two", "Column Three","Column Four","Column Five" };
    Object rowData[][] = { { "NO # ","ID", "Prize", "Brand","Type","Product Name" }};
    JPanel Cashier;
    JButton addItem, removeItem, clear, receipt; 
    
    public ChashierFrame() {
        initUI();
        initClient();
    }
    
    private void initUI() {
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(750,500);
        setTitle("Cashier");
        createMenuBar();
        mainPage();
     }
     
    private void createMenuBar() {

        JMenuBar menubar = new JMenuBar();

        JMenuItem mainMenu = new JMenuItem("MainPage");
        JMenuItem calMenu = new JMenuItem("Calculator");
        JMenuItem consMenu = new JMenuItem("Convertor");
        JMenuItem chatMenu = new JMenuItem("Quick MSG");
        JMenuItem logMenu = new JMenuItem("Logout");
        JMenuItem NotMenu = new JMenuItem("Notification");

        menubar.add(mainMenu);
        menubar.add(calMenu);
        menubar.add(consMenu);
        menubar.add(logMenu);
        menubar.add(NotMenu);
        menubar.add(chatMenu);
        
        logMenu.addActionListener((ActionEvent e) -> {
            LoginFrame loginDlg = new LoginFrame();
        loginDlg.setVisible(true);
        loginDlg.setTitle("Authenticate Access");
        dispose();
        });
        
        chatMenu.addActionListener((ActionEvent e) -> {
            initClient();
        });
        
        calMenu.addActionListener((ActionEvent e) -> {
        Calc calculator = new Calc();
        calculator.setVisible(true);
        });
        
        consMenu.addActionListener((ActionEvent e) -> {
            try {
                Convertor covertor = new Convertor();
                covertor.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(ChashierFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        NotMenu.addActionListener((ActionEvent e) -> {
          report.getVoiceOut();
        });
        
        setJMenuBar(menubar);
    }
     
    private void mainPage() {
     Cashier = new JPanel(null);
     Cashier.setBackground(Color.LIGHT_GRAY);
     
     JLabel ItemBarcode = new JLabel("Item Barcode: ");
     ItemBarcode.setBounds(200,30,100,20);
     Cashier.add(ItemBarcode);
     JTextField itemBarcode = new JTextField();
     itemBarcode.setBounds(ItemBarcode.getX()+100,ItemBarcode.getY(),200,20);
     Cashier.add(itemBarcode);
     
     JLabel ProdId = new JLabel("Product ID: ");
     ProdId.setBounds(100,80,100,20);
     Cashier. add(ProdId);
     JTextField PID = new JTextField();
     PID.setBounds(ProdId.getX()+80,ProdId.getY(),100,20);
     Cashier.add(PID);
     
     JLabel qty = new JLabel("     QTY: ");
     qty.setBounds(100,110,100,30);
     Cashier.add(qty);
     JTextField Qit = new JTextField();
     Qit.setBounds(qty.getX()+80,qty.getY(),100,20);
     Cashier.add(Qit);
     
     JLabel discount = new JLabel("Discount: ");
     discount.setBounds(100,140,100,30);
     Cashier.add(discount);
     JTextField Discount = new JTextField();
     Discount.setBounds(discount.getX()+80,discount.getY(),100,20);
     Cashier.add(Discount);
     
     
     JLabel total = new JLabel("Total: ");
     total.setBounds(350,85,100,30);
     Cashier.add(total);
     JTextField totalField = new JTextField("0.0");
     totalField.setBounds(400,ProdId.getY(),150,40);
     Cashier.add(totalField);
          
     pay.setBounds(430,130,80,30);
     Cashier.add(pay);
     
     addItem = new JButton("Add Item",null);
     addItem.setBounds(20,230,110,30);
     Cashier.add(addItem);
      
     removeItem = new JButton("Remove Item",null);
     removeItem.setBounds(addItem.getX(),addItem.getY()+30,110,30);
     Cashier.add(removeItem);
      
     clear = new JButton("Clear Table",null);
     clear.setBounds(addItem.getX(),removeItem.getY()+30,110,30);
     Cashier.add(clear);
           
     receipt = new JButton("Print Receipt",null);
     receipt.setBounds(addItem.getX(),clear.getY()+30,110,50);
     Cashier.add(receipt);
     
     JTable table = new JTable(rowData, columnNames);
     JScrollPane scrollPane = new JScrollPane(table);
     table.setBounds(150,180,570,240);
      
     Cashier.add(scrollPane);
     Cashier.add(table);
     
     add(Cashier);
     }  
    
    private void initClient() {
        try {
            Client cl = new Client();
            cl.setVisible(true);
            cl.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        } catch (IOException ex) {
            Logger.getLogger(ChashierFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
}

     
 
