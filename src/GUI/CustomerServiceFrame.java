
package GUI;

import NetworkChat.Client;
import Users.Customer;
import com.toedter.calendar.JDateChooser;
import static inventorysytem.InventorySytem.report;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


public class CustomerServiceFrame extends JFrame {
   
    JPanel Cards = new JPanel(new CardLayout());
    public static ArrayList <Customer> customer = new ArrayList<>();
    JButton ok = new JButton("Save"); JButton no = new JButton("Clear"); JButton addItem = new JButton("Add Item");
    private final String[] types = {"Type 1", "Type 2", "Type 3"};
    private final String[] paymenttypes = {"Cash", "Card", "Cheack"};
    private final String[] offerstype = {"Long Term", "Short Term"};
    Object columnNames[] = { "Column no","Column One", "Column Two", "Column Three","Column Four","Column Five" };
    Object rowData[][] = {{ "NO# ","ID", "Prize", "Brand","Type","Product Name" }};
    JComboBox offerType = new JComboBox(offerstype);
    JComboBox type = new JComboBox(types);
    JComboBox paymenttype = new JComboBox(paymenttypes);
    JPanel offersPanel, addCustomer, deleviery, listCustomer,controlCustomer,issues;
    JButton addoffer, removeOffer, modifyOffer;
    
    public CustomerServiceFrame() {
        initUI();
        add(Cards);
    }
    
    private void initUI() {
        OfferPnael();
        ListCustomers();
        ControlCustomers();
        SendDeleviry();
        addCustomers();
        customersIssues();
        
        setTitle("Customer Service");
        setSize(650, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        createMenuBar();
    }

    private void createMenuBar() {
        JMenu customers = new JMenu("Customers");
        
        customers.setIcon(new ImageIcon("images\\workers.png"));
        JMenuItem addCustomers = new JMenuItem("Add Customer Information");
        addCustomers.setIcon(new ImageIcon("images\\new.png"));
        JMenuItem controlCustomers = new JMenuItem("Control Customer's Data");
        controlCustomers.setIcon(new ImageIcon("images\\control.png"));
        JMenuItem listCustomers = new JMenuItem("List");
        listCustomers.setIcon(new ImageIcon("images\\list.png"));
                
        customers.add(addCustomers);
        customers.addSeparator();
        customers.add(controlCustomers);
        customers.addSeparator();
        customers.add(listCustomers);
            
        JMenu deliveryOrders = new JMenu("Delivery Orders");
        deliveryOrders.setIcon(new ImageIcon("images\\deleviery.png"));
        JMenuItem sendOrder = new JMenuItem("Orders..");
        deliveryOrders.add(sendOrder);
            
        JMenu offers = new JMenu("Offers");
        offers.setIcon(new ImageIcon("images\\items.png"));
        JMenuItem theOffers = new JMenuItem("Offers..");
        offers.add(theOffers);
        
        JMenu reports = new JMenu("Reports");
        reports.setIcon(new ImageIcon("images\\reports.png"));
        JMenuItem Issue = new JMenuItem("Custoemr's Issues");
        reports.add(Issue);
            
        JMenu setting = new JMenu("Setting");
        setting.setIcon(new ImageIcon("images\\control.png"));
        JMenuItem logout = new JMenuItem("Logout");
        JMenuItem notification = new JMenuItem("Notification");
        JMenuItem chat = new JMenuItem("Quick MSG");
        setting.add(logout);
        setting.addSeparator();
        setting.add(notification);
        setting.add(chat);
                    
        JMenuBar Menubar = new JMenuBar();
        
        Menubar.add(customers);
        Menubar.add(deliveryOrders);
        Menubar.add(offers);
        Menubar.add(reports);
        Menubar.add(setting);
        
        addCustomers.addActionListener((ActionEvent ae) -> {
            addCustomer.setVisible(true);issues.setVisible(false);
            offersPanel.setVisible(false);deleviery.setVisible(false); 
            listCustomer.setVisible(false);controlCustomer.setVisible(false);
        });
        sendOrder.addActionListener((ActionEvent ae) -> {
            deleviery.setVisible(true); issues.setVisible(false);
            offersPanel.setVisible(false);addCustomer.setVisible(false);
            listCustomer.setVisible(false);controlCustomer.setVisible(false);
        });
        listCustomers.addActionListener((ActionEvent ae) -> {
            listCustomer.setVisible(true); issues.setVisible(false);
            offersPanel.setVisible(false);addCustomer.setVisible(false);
            controlCustomer.setVisible(false);deleviery.setVisible(false);
        });
        controlCustomers.addActionListener((ActionEvent ae) -> {
            controlCustomer.setVisible(true); issues.setVisible(false);
            offersPanel.setVisible(false);addCustomer.setVisible(false);
            listCustomer.setVisible(false);deleviery.setVisible(false);
        });
        theOffers.addActionListener((ActionEvent ae) -> {
            offersPanel.setVisible(true); issues.setVisible(false);
            controlCustomer.setVisible(false);addCustomer.setVisible(false);
            listCustomer.setVisible(false);deleviery.setVisible(false);
        });
        Issue.addActionListener((ActionEvent ae) -> {
            issues.setVisible(true); offersPanel.setVisible(false);
            controlCustomer.setVisible(false);addCustomer.setVisible(false);
            listCustomer.setVisible(false);deleviery.setVisible(false);
        });
        chat.addActionListener((ActionEvent ae) -> {
            initClient();
        });
        
        logout.addActionListener((ActionEvent e) -> {
            LoginFrame loginDlg = new LoginFrame();
        loginDlg.setVisible(true);
        loginDlg.setTitle("Authenticate Access");
        dispose();
        });
        notification.addActionListener((ActionEvent e) -> {
            report.getVoiceOut();
        });
        
            
        JPanel customerServicePanel = new JPanel(new BorderLayout());
        customerServicePanel.add(Menubar);
            
        add(customerServicePanel,BorderLayout.NORTH);    
        
    }

// =====================Customers========================
    private void addCustomers() {
        JTextField firstname = new JTextField(20);JTextField lastname = new JTextField(20);
        JTextField theemail = new JTextField(20);JTextField phonenumber = new JTextField(20);
        JTextField cityname = new JTextField(20);JTextField statename = new JTextField(20);
        JTextField address1 = new JTextField(20);JTextField address2 = new JTextField(20);
        JTextField blocknumber = new JTextField(20);JTextField streetname = new JTextField(20);
        
        addCustomer = new JPanel(null);
        JLabel lb1 = new JLabel("Personal Information: ");
        lb1.setBounds(40, 35, 200, 20);
        addCustomer.add(lb1);
        
        JLabel first = new JLabel("First Name: ");
        first.setBounds(50,65,100,20);
        addCustomer.add(first);
        firstname.setBounds(first.getBounds().width+40,65,100,20);
        addCustomer.add(firstname);
        
        JLabel second = new JLabel("Second Name: ");
        second.setBounds(300,65,100,20);
        addCustomer.add(second);
        lastname.setBounds(second.getBounds().width+290,65,100,20);
        addCustomer.add(lastname);
        
        JLabel lb2 = new JLabel("Conatact Information: ");
        lb2.setBounds(40,95,200,20);
        addCustomer.add(lb2);
        
        JLabel email = new JLabel("Email: ");
        email.setBounds(50,125,100,20);
        addCustomer.add(email);
        theemail.setBounds(email.getBounds().width+40,125,100,20);
        addCustomer.add(theemail);
        
        JLabel phone = new JLabel("Phone Number: ");
        phone.setBounds(300,125,100,20);
        addCustomer.add(phone);
        phonenumber.setBounds(phone.getBounds().width+290,125,100,20);
        addCustomer.add(phonenumber);
        
        JLabel lb3 = new JLabel("Address Information: ");
        lb3.setBounds(40,155,200,20);
        addCustomer.add(lb3);
        
        JLabel city = new JLabel("City Name: ");
        city.setBounds(50,185,100,20);
        addCustomer.add(city);
        cityname.setBounds(city.getBounds().width+40,185,100,20);
        addCustomer.add(cityname);
        
        JLabel state = new JLabel("State Name: ");
        state.setBounds(300,185,100,20);
        addCustomer.add(state);
        statename.setBounds(state.getBounds().width+290,185,100,20);
        addCustomer.add(statename);
        
        JLabel street = new JLabel("Street Name: ");
        street.setBounds(50,215,100,20);
        addCustomer.add(street);
        streetname.setBounds(street.getBounds().width+40,215,100,20);
        addCustomer.add(streetname);
        
        JLabel blockno = new JLabel("Block Number: ");
        blockno.setBounds(300,215,100,20);
        addCustomer.add(blockno);
        blocknumber.setBounds(blockno.getBounds().width+290,215,100,20);
        addCustomer.add(blocknumber);

        JLabel addressone = new JLabel("Address One: ");
        addressone.setBounds(50,245,100,20);
        addCustomer.add(addressone);
        address1.setBounds(addressone.getBounds().width+40,245,100,20);
        addCustomer.add(address1);
        
        JLabel addresstwo = new JLabel("Address Two: ");
        addresstwo.setBounds(300,245,100,20);
        addCustomer.add(addresstwo);
        address2.setBounds(addresstwo.getBounds().width+290,245,100,20);
        addCustomer.add(address2);
        
        ok.setBounds(350,300,100,35);
        //ok.addActionListener(new actions());
        addCustomer.add(ok);
        
        no.setBounds(460,300,100,35);
        addCustomer.add(no);
        
        Cards.add(addCustomer,"card1");
    }
    
     private void ListCustomers() {
        JButton display = new JButton("Display All");
        
        listCustomer = new JPanel (null);
        listCustomer.setBackground(Color.LIGHT_GRAY);
        
        String[] columns = {"ID", "FName", "LName", "Email",
                            "Phone", "City", "State","Street", "Block No.", "Address1", "Address2"};
        DefaultTableModel tablemodel = new DefaultTableModel(columns, 0);
        
        for (Customer c : customer) {
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
        
        listCustomer.setLayout(new BorderLayout());
 
        listCustomer.add(display,BorderLayout.PAGE_START);
        listCustomer.add(scrollPane,BorderLayout.CENTER);
        
        Cards.add(listCustomer,"card2");
    } 
    
    private void ControlCustomers() {
       JTextField firstname = new JTextField(20);JTextField lastname = new JTextField(20);
       JTextField theemail = new JTextField(20);JTextField phonenumber = new JTextField(20);
       JTextField cityname = new JTextField(20);JTextField statename = new JTextField(20);
       JTextField address1 = new JTextField(20);JTextField address2 = new JTextField(20);
       JTextField blocknumber = new JTextField(20);JTextField searchHere = new JTextField(20);
       JTextField streetname = new JTextField(20);
       
       JButton search = new JButton("Search");JButton delete = new JButton("Delete");
       JButton modify = new JButton("Modify");JButton listedData = new JButton("Show Listed Data");
        
      controlCustomer = new JPanel(null);
      controlCustomer.setBackground(Color.LIGHT_GRAY);
      
      searchHere.setBounds(150, 20, 200, 20);
      controlCustomer.add(searchHere);
      search.setBounds(searchHere.getBounds().width+150,20,100,20);
      controlCustomer.add(search);
        
      JLabel lb1 = new JLabel("Personal Information: ");
      lb1.setBounds(40, 50, 200, 20);
      controlCustomer.add(lb1);
        
      JLabel first = new JLabel("First Name: ");
      first.setBounds(50,80,100,20);
      controlCustomer.add(first);
      firstname.setBounds(first.getBounds().width+50,80,100,20);
      controlCustomer.add(firstname);
      
      JLabel second = new JLabel("Second Name: ");
      second.setBounds(300,80,100,20);
      controlCustomer.add(second);
      lastname.setBounds(second.getBounds().width+300,80,100,20);
      controlCustomer.add(lastname);
        
      JLabel lb2 = new JLabel("Conatact Information: ");
      lb2.setBounds(40,110,200,20);
      controlCustomer.add(lb2);
        
      JLabel email = new JLabel("Email: ");
      email.setBounds(50,140,100,20);
      controlCustomer.add(email);
      theemail.setBounds(email.getBounds().width+50,140,100,20);
      controlCustomer.add(theemail);
        
      JLabel phone = new JLabel("Phone Number: ");
      phone.setBounds(300,140,100,20);
      controlCustomer.add(phone);
      phonenumber.setBounds(phone.getBounds().width+300,140,100,20);
      controlCustomer.add(phonenumber);
        
      JLabel lb3 = new JLabel("Address Information: ");
      lb3.setBounds(40,170,200,20);
      controlCustomer.add(lb3);
        
      JLabel city = new JLabel("City Name: ");
      city.setBounds(50,200,100,20);
      controlCustomer.add(city);
      cityname.setBounds(city.getBounds().width+50,200,100,20);
      controlCustomer.add(cityname);
        
      JLabel state = new JLabel("State Name: ");
      state.setBounds(300,200,100,20);
      controlCustomer.add(state);
      statename.setBounds(state.getBounds().width+300,200,100,20);
      controlCustomer.add(statename);
      
      JLabel street = new JLabel("Street Name: ");
      street.setBounds(50,230,100,20);
      controlCustomer.add(street);
      streetname.setBounds(street.getBounds().width+50,230,100,20);
      controlCustomer.add(streetname);
      
      JLabel blockno = new JLabel("Block Number: ");
      blockno.setBounds(300,230,100,20);
      controlCustomer.add(blockno);
      blocknumber.setBounds(blockno.getBounds().width+300,230,100,20);
      controlCustomer.add(blocknumber);
      
      JLabel addressone = new JLabel("Address One: ");
      addressone.setBounds(50,260,100,20);
      controlCustomer.add(addressone);
      address1.setBounds(addressone.getBounds().width+50,260,100,20);
      controlCustomer.add(address1);
        
      JLabel addresstwo = new JLabel("Address Two: ");
      addresstwo.setBounds(300,260,100,20);
      controlCustomer.add(addresstwo);
      address2.setBounds(addresstwo.getBounds().width+300,260,100,20);
      controlCustomer.add(address2);
        
      delete.setBounds(170,330,100,35);
      controlCustomer.add(delete);
        
      modify.setBounds(280,330,100,35);
      controlCustomer.add(modify);
        
      listedData.setBounds(430,45,135,30);
      controlCustomer.add(listedData);
        
      Cards.add(controlCustomer, "card3");
    }
// =====================OrderDeleviry===================     
    private void SendDeleviry() {
    JTextField searchHere = new JTextField(20); JTextField productname = new JTextField(20); 
    JTextField brand = new JTextField(20); JTextField productprize = new JTextField(20); 
    JTextField discount = new JTextField(20); JTextField totalprize = new JTextField(20);
    JTextField ordertype = new JTextField(20); JTextField ordernumber = new JTextField(20);
    
      deleviery = new JPanel(null);
      add(deleviery);
      deleviery.setBackground(Color.LIGHT_GRAY);
      TitledBorder titled = new TitledBorder("Send Deleviery");
      titled.setTitleJustification(TitledBorder.RIGHT);
      deleviery.setBorder(titled);
        
      searchHere.setBounds(150, 30, 200, 25);
      deleviery.add(searchHere);
      addItem.setBounds(searchHere.getBounds().width+150,30,100,25);
      deleviery.add(addItem);
        
      JLabel ProductName = new JLabel("Product Name: ");
      ProductName.setBounds(100,80,150,25);
      deleviery.add(ProductName);
      productname.setBounds(ProductName.getBounds().width+50,80,150,25);
      productname.setEditable(false);
      deleviery.add(productname);
      
      JLabel OrderType = new JLabel("Order Type: ");
      OrderType.setBounds(370,80,150,25);
      deleviery.add(OrderType);
      ordertype.setBounds(OrderType.getBounds().width+300,80,100,25);
      ordertype.setText("Deleviry");
      ordertype.setEditable(false);
      deleviery.add(ordertype);
      
      JLabel Brand = new JLabel("   Brand: ");
      Brand.setBounds(50,130,100,25);
      deleviery.add(Brand);
      brand.setBounds(Brand.getBounds().width+15,130,100,25);
      brand.setEditable(false);
      deleviery.add(brand);
      
      JLabel Type = new JLabel("   Type: ");
      Type.setBounds(230,130,100,25);
      deleviery.add(Type);
      type.setBounds(Type.getBounds().width+180,130,70,25);
      type.setEditable(false);
      type.setEnabled(false);
      deleviery.add(type);
      
      JLabel Quantity = new JLabel("   QTY: ");
      Quantity.setBounds(370,130,100,25);
      deleviery.add(Quantity);
      JSpinner quantity = new JSpinner(new SpinnerNumberModel(1, 1, 5000, 1));  
      quantity.setBounds(Quantity.getBounds().width+320,130,50,30);
      deleviery.add(quantity);
      
      JLabel ProductPrize = new JLabel("Product Prize: ");
      ProductPrize.setBounds(30,180,100,25);
      deleviery.add(ProductPrize);
      productprize.setBounds(ProductPrize.getBounds().width+15,180,100,25);
      productprize.setEditable(false);
      deleviery.add(productprize);
      
      JLabel PaymentType = new JLabel("Payment Type: ");
      PaymentType.setBounds(230,180,100,25);
      deleviery.add(PaymentType);
      paymenttype.setBounds(PaymentType.getBounds().width+220,180,70,25);
      deleviery.add(paymenttype);
      
      JLabel Discount = new JLabel("Discount: ");
      Discount.setBounds(410,180,100,25);
      deleviery.add(Discount);
      discount.setBounds(Discount.getBounds().width+370,180,70,25);
      deleviery.add(discount);
      
      JLabel TotalPrize = new JLabel("   Total Prize: ");
      TotalPrize.setBounds(70,240,150,25);
      deleviery.add(TotalPrize);
      totalprize.setBounds(TotalPrize.getBounds().width+05,240,150,25);
      totalprize.setEditable(false);
      deleviery.add(totalprize);
      
      JLabel orderNumber = new JLabel("Order NO: ");
      orderNumber.setBounds(350,240,150,25);
      deleviery.add(orderNumber);
      ordernumber.setBounds(orderNumber.getBounds().width+270,240,100,25);
      deleviery.add(ordernumber);
      
      Object columnNames[] = { "Column no","Column One", "Column Two","Column Three", "Column Four"};
      Object rowData[][] = { { "NO.","Product Name", "Quantity", "Order Number", "Order Type"}};
      JTable table = new JTable(rowData, columnNames);
      JScrollPane scrollPane = new JScrollPane(table);
      table.setFillsViewportHeight(false);
      deleviery.add(scrollPane);
      deleviery.add(table);
      table.setBounds(50,280,530,200);
      
      Cards.add(deleviery, "card4");
    }
// =====================Offers==========================
    private void OfferPnael(){
      offersPanel = new JPanel(null);
      offersPanel.setBackground(Color.LIGHT_GRAY);
      
     JLabel offersType = new JLabel("Offer Type: ");
     offersType.setBounds(20,10,100,25);
     offersPanel.add(offersType);
     offerType.setBounds(20,30,100,25);
     offersPanel.add(offerType);
      
     JLabel offerNum = new JLabel("Offer Number: ");
     offerNum.setBounds(470,10,100,20);
     offersPanel.add(offerNum);
     JTextField offernumber = new JTextField();
     offernumber.setBounds(470,30,80,20);
     offersPanel.add(offernumber);

     JLabel ItemBarcode = new JLabel("Product ID: ");
     ItemBarcode.setBounds(150,50,100,20);
     offersPanel.add(ItemBarcode);
     JTextField itemBarcode = new JTextField();
     itemBarcode.setBounds(ItemBarcode.getX()+100,ItemBarcode.getY(),200,20);
     offersPanel.add(itemBarcode);

     JLabel qty = new JLabel("     QTY: ");
     qty.setBounds(60,95,100,30);
     offersPanel.add(qty);
     JTextField Qit = new JTextField();
     Qit.setBounds(qty.getX()+50,100,100,20);
     offersPanel.add(Qit);
     
     JLabel discount = new JLabel("Discount: ");
     discount.setBounds(250,95,100,30);
     offersPanel.add(discount);
     JTextField Discount = new JTextField();
     Discount.setBounds(discount.getX()+70,100,100,20);
     offersPanel.add(Discount);
     
     JLabel fromdate = new JLabel("From Date: ");
     fromdate.setBounds(60,140,100,20);
     offersPanel.add(fromdate);
     JDateChooser fromDate = new JDateChooser(); 
     fromDate.setBounds(fromdate.getBounds().width+30,140,120,20);
     offersPanel.add(fromDate);
     
     JLabel todate = new JLabel("To Date: ");
     todate.setBounds(280,140,100,20);
     offersPanel.add(todate);
     JDateChooser toDate = new JDateChooser(); 
     toDate.setBounds(todate.getBounds().width+235,140,120,20);
     offersPanel.add(toDate);
      
     addoffer = new JButton("Add Offer",null);
     addoffer.setBounds(60,190,100,30);
     offersPanel.add(addoffer);
      
     modifyOffer = new JButton("Modify Offer",null);
     modifyOffer.setBounds(165,190,105,30);
     offersPanel.add(modifyOffer);
      
     removeOffer = new JButton("Remove Offer",null);
     removeOffer.setBounds(275,190,115,30);
     offersPanel.add(removeOffer);
     
     JTextField searchBar = new JTextField("Search Filed");
     searchBar.setBounds(395,190,150,30);
     offersPanel.add(searchBar);
     
     JTable table = new JTable(rowData, columnNames);
     JScrollPane scrollPane = new JScrollPane(table);
     table.setBounds(25,230,590,250);
      
     offersPanel.add(scrollPane);
     offersPanel.add(table);
     
     Cards.add(offersPanel, "card5");
    }
// =====================Reports========================= 
    private void customersIssues() {
      issues = new JPanel(null);
      issues.setBackground(Color.LIGHT_GRAY);
      
     JLabel customerName = new JLabel("Customer Name: ");
     customerName.setBounds(80,80,100,25);
     issues.add(customerName);
      JTextField name = new JTextField(20);
     name.setBounds(180,80,100,25);
     issues.add(name);
      
     JLabel DateOfIssue = new JLabel("Date: ");
     DateOfIssue.setBounds(340,80,100,20);
     issues.add(DateOfIssue);
     JDateChooser date = new JDateChooser(); 
     date.setBounds(380,80,120,20);
     issues.add(date);
     
     JLabel IssueNo = new JLabel("Issue NO.: ");
     IssueNo.setBounds(20,20,100,25);
     issues.add(IssueNo);
     JTextField issueNumber = new JTextField(20);
     issueNumber.setBounds(90,20,40,25);
     issues.add(issueNumber);
     
    JTextArea theIssue = new JTextArea("Enter Text Here..");
    Border border = BorderFactory.createLineBorder(Color.BLACK);
    theIssue.setBounds(50,140,550,300);
    theIssue.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    issues.add(theIssue);
    
    JButton sendIssue = new JButton("Send Customer Issue");
    sendIssue.setBounds(230, 460, 200, 30);
    issues.add(sendIssue);
    
    Cards.add(issues, "card6");
     
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
