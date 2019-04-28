
package GUI;

import Users.Admin;
import static inventorysytem.InventorySytem.cashiers;
import Users.Casher;
import Users.CustomerServies;
import Users.Deliveryman;
import Users.Owner;
import static inventorysytem.InventorySytem.Admins;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class OwnerFrame extends JFrame implements Serializable{
    
    JPanel Cards = new JPanel(new CardLayout());
     private String FirstName,LastName,PhoneNumber,Email,CityName,StateName,StreetName,BlockNumber,AddressOne,AddressTwo,Searched,WorkerType;
     JPanel AddAdmins,ListAdmins,ControlAdmins,AccountsPanel;
     Admin admin = new Admin();
     Owner owner = new Owner();
     private final String[] accFor = {"Admin", "Owner"};
     JComboBox UsersAccounts = new JComboBox(accFor);
    
    public OwnerFrame() {
        initUI();
        add(Cards);
    }
    
    private void initUI() {
        
        this.addWindowListener(new WindowAdapter() {
            
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    admin.saveData();
                    owner.saveData();
                } catch (IOException ex) {
                    Logger.getLogger(OwnerFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            @Override
            public void windowOpened(WindowEvent e) {
                try {
                    admin.getData();
                    owner.getData();
                } catch (IOException ex) {
                    Logger.getLogger(OwnerFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(OwnerFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        initAddAdmins();
        initListAdmins();
        initControlAdmins();
        initAccounts();
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,600);
        setTitle("Owner");
        setResizable(false);
        createMenuBar();
  }
     
    private void createMenuBar() {

        JMenuBar menubar = new JMenuBar();

        JMenu adminMenu = new JMenu("Adminstrators");
        JMenu accountsMenu = new JMenu("Adminstrators Accounts");
        JMenu accessMenu = new JMenu("Access To");
        JMenu settingMenu = new JMenu("Settings");
        
        JMenuItem logMi = new JMenuItem("LogOut");
        JMenuItem accessCashierMi = new JMenuItem("Cashier");
        JMenuItem accessCSMi = new JMenuItem("Customer Services");
        JMenuItem accessAdminMi = new JMenuItem("Administrators");
        
        JMenuItem addMi = new JMenuItem("Add Data.. ");
        JMenuItem controlMi = new JMenuItem("Control Data.. ");
        JMenuItem listMi = new JMenuItem("List Data.. ");
    
        JMenuItem accountMi = new JMenuItem("Accounts.. ");
        
        settingMenu.add(logMi);
        
        accessMenu.add(accessAdminMi);
        accessMenu.addSeparator();
        accessMenu.add(accessCSMi);
        accessMenu.addSeparator();
        accessMenu.add(accessCashierMi);
        
        adminMenu.add(addMi);
        adminMenu.addSeparator();
        adminMenu.add(controlMi);
        adminMenu.addSeparator();
        adminMenu.add(listMi);
        
        accountsMenu.add(accountMi);
        
        menubar.add(adminMenu);
        menubar.add(accountsMenu);
        menubar.add(accessMenu);
        menubar.add(settingMenu);
        
        logMi.addActionListener((ActionEvent e) -> {
        LoginFrame loginDlg = new LoginFrame();
        loginDlg.setVisible(true);
        loginDlg.setTitle("Authenticate Access");
        dispose();
        });
        
        accessCashierMi.addActionListener((ActionEvent e) -> {
        ChashierFrame cashier = new ChashierFrame();
        cashier.setVisible(true);
        cashier.setTitle("Acessed To Cashier");
        });
        
        accessCSMi.addActionListener((ActionEvent e) -> {
        CustomerServiceFrame customerService = new CustomerServiceFrame();
        customerService.setVisible(true);
        customerService.setTitle("Acessed To Customer Service");
        });
        
        accessAdminMi.addActionListener((ActionEvent e) -> {
        AdminFrame admin = new AdminFrame();
        admin.setVisible(true);
        admin.setTitle("Acessed To Adminstrators");
        });
        
        addMi.addActionListener((ActionEvent e) -> {
        AddAdmins.setVisible(true); ListAdmins.setVisible(false); 
        ControlAdmins.setVisible(false); AccountsPanel.setVisible(false);
        });
        
        listMi.addActionListener((ActionEvent e) -> {
        ListAdmins.setVisible(true); AddAdmins.setVisible(false); 
        ControlAdmins.setVisible(false); AccountsPanel.setVisible(false);
        });
        
        controlMi.addActionListener((ActionEvent e) -> {
        ControlAdmins.setVisible(true); AddAdmins.setVisible(false); 
        ListAdmins.setVisible(false); AccountsPanel.setVisible(false);
        });
        
        accountMi.addActionListener((ActionEvent e) -> {
        AccountsPanel.setVisible(true); AddAdmins.setVisible(false); 
        ListAdmins.setVisible(false); ControlAdmins.setVisible(false); 
        });
        
        setJMenuBar(menubar);
    }
     
    private void initAddAdmins(){
        JTextField firstname = new JTextField(20);JTextField lastname = new JTextField(20);
        JTextField phonenumber = new JTextField(20);JTextField theemail = new JTextField(20);
        JTextField cityname = new JTextField(20);JTextField statename = new JTextField(20);
        JTextField address1 = new JTextField(20);JTextField address2 = new JTextField(20);
        JTextField streetnumber = new JTextField(20);JTextField blocknumber = new JTextField(20);
       
        JButton ok = new JButton("Save");
        JButton no = new JButton("Clear");
        
        ok.addActionListener((ActionEvent ae) -> {
           Admin adminn = new Admin();
           adminn.add(firstname.getText(),lastname.getText(),phonenumber.getText(),theemail.getText(),
           cityname.getText(),statename.getText(),address1.getText(),address2.getText(),
           streetnumber.getText(),blocknumber.getText());
           
        });
        no.addActionListener((ActionEvent ae) -> {
            firstname.setText("");lastname.setText("");theemail.setText("");phonenumber.setText("");
            cityname.setText("");statename.setText("");address1.setText("");address2.setText("");
            blocknumber.setText("");streetnumber.setText("");
        });
        
        AddAdmins = new JPanel(null);
        AddAdmins.setBackground(Color.LIGHT_GRAY);
        
        JLabel lb1 = new JLabel("Personal Information: ");
        lb1.setBounds(40, 35, 200, 20);
        AddAdmins.add(lb1);
        
        JLabel first = new JLabel("First Name: ");
        first.setBounds(50,65,100,20);
        AddAdmins.add(first);
        firstname.setBounds(first.getBounds().width+50,65,100,20);
        AddAdmins.add(firstname);
        
        JLabel second = new JLabel("Second Name: ");
        second.setBounds(300,65,100,20);
        AddAdmins.add(second);
        lastname.setBounds(second.getBounds().width+300,65,100,20);
        AddAdmins.add(lastname);
        
        JLabel lb2 = new JLabel("Conatact Information: ");
        lb2.setBounds(40,95,200,20);
        AddAdmins.add(lb2);
        
        JLabel email = new JLabel("Email: ");
        email.setBounds(50,125,100,20);
        AddAdmins.add(email);
        theemail.setBounds(email.getBounds().width+50,125,100,20);
        AddAdmins.add(theemail);
        
        JLabel phone = new JLabel("Phone Number: ");
        phone.setBounds(300,125,100,20);
        AddAdmins.add(phone);
        AddAdmins.setBounds(phone.getBounds().width+300,125,100,20);
        AddAdmins.add(phonenumber);
        
        JLabel lb3 = new JLabel("Address Information: ");
        lb3.setBounds(40,155,200,20);
        AddAdmins.add(lb3);
        
        JLabel city = new JLabel("City Name: ");
        city.setBounds(50,185,100,20);
        AddAdmins.add(city);
        cityname.setBounds(city.getBounds().width+50,185,100,20);
        AddAdmins.add(cityname);
        
        JLabel state = new JLabel("State Name: ");
        state.setBounds(300,185,100,20);
        AddAdmins.add(state);
        statename.setBounds(state.getBounds().width+300,185,100,20);
        AddAdmins.add(statename);
        
        JLabel street = new JLabel("Street Name: ");
        street.setBounds(50,215,100,20);
        AddAdmins.add(street);
        streetnumber.setBounds(street.getBounds().width+50,215,100,20);
        AddAdmins.add(streetnumber);
        
        JLabel blockno = new JLabel("Block Number: ");
        blockno.setBounds(300,215,100,20);
        AddAdmins.add(blockno);
        blocknumber.setBounds(blockno.getBounds().width+300,215,100,20);
        AddAdmins.add(blocknumber);

        JLabel addressone = new JLabel("Address One: ");
        addressone.setBounds(50,245,100,20);
        AddAdmins.add(addressone);
        address1.setBounds(addressone.getBounds().width+50,245,100,20);
        AddAdmins.add(address1);
        
        JLabel addresstwo = new JLabel("Address Two: ");
        addresstwo.setBounds(300,245,100,20);
        AddAdmins.add(addresstwo);
        address2.setBounds(addresstwo.getBounds().width+300,245,100,20);
        AddAdmins.add(address2);
        
        ok.setBounds(350,300,100,35);
        AddAdmins.add(ok);
        
        no.setBounds(460,300,100,35);
        AddAdmins.add(no);
        
        Cards.add(AddAdmins, "card1");
    } 
    
    private void initListAdmins() {
        ListAdmins = new JPanel (null);
        ListAdmins.setBackground(Color.LIGHT_GRAY);
        
        String[] columns = {"ID", "FName", "LName", "Email",
                            "Phone", "City", "State","Street", "Block No.", "Address1", "Address2"};
        DefaultTableModel tablemodel = new DefaultTableModel(columns, 0);
        
        for (Admin c : Admins) {
            String ID = c.GetID();
            String FName = c.con.getFirstname();
            String LName = c.con.getLastname();
            String email = c.con.getEmail();
            String Phone = c.con.getPhonenumber();
            String City = c.address.getCityname();
            String State = c.address.getStatename();
            String Street = c.address.getStreetnumber();
            String Block = c.address.getBlockNumber();
            String Add1 = c.address.getAddressone();
            String Add2 = c.address.getAddresstwo();
            
            Object[] obj = {ID,FName,LName,email,Phone,City,State,Street,Block,Add1,Add2};
            
            tablemodel.addRow(obj);
        }
 
        JTable table = new JTable(tablemodel);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        ListAdmins.setLayout(new BorderLayout());
        ListAdmins.add(scrollPane,BorderLayout.CENTER);

        Cards.add(ListAdmins,"card2");
    } 
    
    private void initControlAdmins() {
 JTextField firstname = new JTextField(20);JTextField lastname = new JTextField(20);
       JTextField theemail = new JTextField(20);JTextField phonenumber = new JTextField(20);
       JTextField cityname = new JTextField(20);JTextField statename = new JTextField(20);
       JTextField address1 = new JTextField(20);JTextField address2 = new JTextField(20);
       JTextField blocknumber = new JTextField(20);JTextField searchHere = new JTextField(20);
       JTextField streetname = new JTextField(20);
       
       JButton search = new JButton("Search");JButton delete = new JButton("Delete");
       JButton modify = new JButton("Modify");JButton listedData = new JButton("Show Listed Data");
       
       listedData.addActionListener((ActionEvent ae) -> {
            ControlAdmins.setVisible(false);
            initListAdmins();
            ListAdmins.setVisible(true);
            AddAdmins.setVisible(false);AccountsPanel.setVisible(false);
       });
       
       search.addActionListener((ActionEvent ae) -> {
                   admin = admin.search(searchHere.getText());
                   firstname.setText(admin.con.getFirstname());
                   lastname.setText(admin.con.getLastname());
                   theemail.setText(admin.con.getEmail());
                   phonenumber.setText(admin.con.getPhonenumber());
                   cityname.setText(admin.address.getCityname());
                   statename.setText(admin.address.getStatename());
                   address1.setText(admin.address.getAddressone());
                   address2.setText(admin.address.getAddresstwo());
                   blocknumber.setText(admin.address.getBlockNumber());
                   streetname.setText(admin.address.getStreetnumber());
       });
       delete.addActionListener((ActionEvent ae) -> {
           if (admin.del(searchHere.getText()))
           {
               firstname.setText("");lastname.setText("");
               theemail.setText("");phonenumber.setText("");
               cityname.setText("");statename.setText("");
               address1.setText("");address2.setText("");
               blocknumber.setText("");streetname.setText("");
           }else {
               JOptionPane.showMessageDialog(null, "Cant Delete ID number : "+ searchHere.getText(), "Alert", JOptionPane.WARNING_MESSAGE);
           }
       });
       
       modify.addActionListener((ActionEvent ae) -> {
            if (!firstname.getText().equals("")) {
                admin.modify(searchHere.getText(), firstname.getText(), "FirstName");
            }
            if (!lastname.getText().equals("")) {
                admin.modify(searchHere.getText(), lastname.getText(), "LastName");
            }
            if (!theemail.getText().equals("")) {
                admin.modify(searchHere.getText(), theemail.getText(), "Email");
            }
            if (!phonenumber.getText().equals("")) {
                admin.modify(searchHere.getText(), phonenumber.getText(), "PhoneNumber");
            }
            if (!cityname.getText().equals("")) {
                admin.modify(searchHere.getText(), cityname.getText(), "City");
            }
            if (!statename.getText().equals("")) {
                admin.modify(searchHere.getText(), statename.getText(), "State");
            }
            if (!address1.getText().equals("")) {
                admin.modify(searchHere.getText(), address1.getText(), "AddressOne");
            }
            if (!address2.getText().equals("")) {
                admin.modify(searchHere.getText(), address2.getText(), "AddressTwo");
            }
            if (!blocknumber.getText().equals("")) {
                admin.modify(searchHere.getText(), blocknumber.getText(), "BlockNumber");
            }
            if (!streetname.getText().equals("")) {
                admin.modify(searchHere.getText(), streetname.getText(), "Street");
            }
       });
       
      ControlAdmins = new JPanel(null);
      ControlAdmins.setBackground(Color.LIGHT_GRAY);
      
      searchHere.setBounds(150, 20, 200, 20);
      ControlAdmins.add(searchHere);
      search.setBounds(searchHere.getBounds().width+150,20,100,20);
      ControlAdmins.add(search);
        
      JLabel lb1 = new JLabel("Personal Information: ");
      lb1.setBounds(40, 50, 200, 20);
      ControlAdmins.add(lb1);
        
      JLabel first = new JLabel("First Name: ");
      first.setBounds(50,80,100,20);
      ControlAdmins.add(first);
      firstname.setBounds(first.getBounds().width+50,80,100,20);
      ControlAdmins.add(firstname);
      
      JLabel second = new JLabel("Second Name: ");
      second.setBounds(300,80,100,20);
      ControlAdmins.add(second);
      lastname.setBounds(second.getBounds().width+300,80,100,20);
      ControlAdmins.add(lastname);
        
      JLabel lb2 = new JLabel("Conatact Information: ");
      lb2.setBounds(40,110,200,20);
      ControlAdmins.add(lb2);
        
      JLabel email = new JLabel("Email: ");
      email.setBounds(50,140,100,20);
      ControlAdmins.add(email);
      theemail.setBounds(email.getBounds().width+50,140,100,20);
      ControlAdmins.add(theemail);
        
      JLabel phone = new JLabel("Phone Number: ");
      phone.setBounds(300,140,100,20);
      ControlAdmins.add(phone);
      phonenumber.setBounds(phone.getBounds().width+300,140,100,20);
      ControlAdmins.add(phonenumber);
        
      JLabel lb3 = new JLabel("Address Information: ");
      lb3.setBounds(40,170,200,20);
      ControlAdmins.add(lb3);
        
      JLabel city = new JLabel("City Name: ");
      city.setBounds(50,200,100,20);
      ControlAdmins.add(city);
      cityname.setBounds(city.getBounds().width+50,200,100,20);
      ControlAdmins.add(cityname);
        
      JLabel state = new JLabel("State Name: ");
      state.setBounds(300,200,100,20);
      ControlAdmins.add(state);
      statename.setBounds(state.getBounds().width+300,200,100,20);
      ControlAdmins.add(statename);
      
      JLabel street = new JLabel("Street Name: ");
      street.setBounds(50,230,100,20);
      ControlAdmins.add(street);
      streetname.setBounds(street.getBounds().width+50,230,100,20);
      ControlAdmins.add(streetname);
      
      JLabel blockno = new JLabel("Block Number: ");
      blockno.setBounds(300,230,100,20);
      ControlAdmins.add(blockno);
      blocknumber.setBounds(blockno.getBounds().width+300,230,100,20);
      ControlAdmins.add(blocknumber);
      
      JLabel addressone = new JLabel("Address One: ");
      addressone.setBounds(50,260,100,20);
      ControlAdmins.add(addressone);
      address1.setBounds(addressone.getBounds().width+50,260,100,20);
      ControlAdmins.add(address1);
        
      JLabel addresstwo = new JLabel("Address Two: ");
      addresstwo.setBounds(300,260,100,20);
      ControlAdmins.add(addresstwo);
      address2.setBounds(addresstwo.getBounds().width+300,260,100,20);
      ControlAdmins.add(address2);
        
        
      delete.setBounds(170,330,100,35);
      ControlAdmins.add(delete);
        
      modify.setBounds(280,330,100,35);
      ControlAdmins.add(modify);
        
      listedData.setBounds(430,45,135,30);
      ControlAdmins.add(listedData);
        
      Cards.add(ControlAdmins, "card3");
    } 
    
    private void initAccounts() {
        JTextField searchHere = new JTextField(20);JTextField accountuser = new JTextField(20);
        JTextField accountpass = new JTextField(20);
        
        JButton search = new JButton("Search"); JButton addAccount = new JButton("Add");
        JButton remove = new JButton("Remove"); JButton modify = new JButton("Edit");
        
        AccountsPanel = new JPanel(null);
        AccountsPanel.setBackground(Color.LIGHT_GRAY);
        TitledBorder titled = new TitledBorder("Control Accounts");
        AccountsPanel.setBorder(titled);
        
        searchHere.setBounds(150, 50, 200, 30);
        AccountsPanel.add(searchHere);
        search.setBounds(searchHere.getBounds().width+150,50,100,30);
        AccountsPanel.add(search);
        
        JLabel lb1 = new JLabel("Personal Information: ");
        lb1.setBounds(40, 100, 200, 20);
        AccountsPanel.add(lb1);
        
        JLabel user = new JLabel("Account Username: ");
        user.setBounds(50,130,200,20);
        AccountsPanel.add(user);
        accountuser.setBounds(165,130,100,20);
        AccountsPanel.add(accountuser);
        
        JLabel pass = new JLabel("Account Password: ");
        pass.setBounds(300,130,200,20);
        AccountsPanel.add(pass);
        accountpass.setBounds(420,130,100,20);
        AccountsPanel.add(accountpass);
        
        addAccount.setBounds(250, 200, 70, 30);
        AccountsPanel.add(addAccount);
        remove.setBounds(350, 200, 100, 30);
        AccountsPanel.add(remove);
        modify.setBounds(480, 200, 70, 30);
        AccountsPanel.add(modify);
        
       UsersAccounts.setBounds(30, 20, 100, 30);
        AccountsPanel.add(UsersAccounts);
        
        String workertype = (String) UsersAccounts.getSelectedItem();
        
        addAccount.addActionListener((ActionEvent ae) -> {
            switch (workertype) {
                case "Admin":
                    admin.username = accountuser.getText();
                    admin.password = accountpass.getText();        
                    admin.inputAccounts();
                    break;
                case "Owner":
                    owner.username = accountuser.getText();
                    owner.password = accountpass.getText();  
                    owner.inputAccounts();
                    break;
                default:
                    break;
            }
        });
        remove.addActionListener((ActionEvent ae) -> {
            switch (workertype) {
                case "Admin":
                    admin.username = accountuser.getText();
                    admin.password = accountpass.getText();        
                    admin.removeAccounts();
                    break;
                case "Owner":
                   owner.username = accountuser.getText();
                   owner.password = accountpass.getText();        
                   owner.removeAccounts();
                    break;
                default:
                    break;
            }
        });
        modify.addActionListener((ActionEvent ae) -> {
            switch (workertype) {
                case "Admin":
                    admin.username = accountuser.getText();
                    admin.password = accountpass.getText();        
                    admin.inputAccounts();
                    break;
                case "Owner":
                    owner.username = accountuser.getText();
                    owner.password = accountpass.getText();        
                    owner.inputAccounts();
                    break;
                default:
                    break;
            }
        });
        
        
        Cards.add(AccountsPanel,"card4");
    } 
}
