package inventorysytem;

import GUI.LoginFrame;
import Orders.Product;
import Users.Admin;
import Users.Casher;
import Users.CustomerServies;
import Users.Deliveryman;
import Users.Reports;
import Users.Workers;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;


public class InventorySytem {
    
    public static JTextArea OneMessage = new JTextArea("Write message here");
    public static JLabel allMessage = new JLabel("Massages Here.. ");
    public static JButton sendMsg = new JButton("Send");
    
    public static Reports report = new Reports();
    public static ArrayList <Workers> workers = new ArrayList<>();
    public static ArrayList <Casher> cashiers = new ArrayList<>();
    public static ArrayList <CustomerServies> cs = new ArrayList<>();
    public static ArrayList <Deliveryman> dm = new ArrayList<>();
    public static ArrayList <Admin> Admins = new ArrayList<>();
    public static ArrayList <Product> products = new ArrayList<>();
    
    public static void main(String[] args) {
        LoginFrame loginDlg = new LoginFrame();
        loginDlg.setVisible(true);
        loginDlg.setTitle("Authenticate Access");
    }
}
