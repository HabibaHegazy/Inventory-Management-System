package GUI;

import Users.*;
import NetworkChat.Server;
import Orders.Product;
import Orders.Purchase;
import Orders.Sale;
import Orders.Warranty;
import static inventorysytem.InventorySytem.report;
import static inventorysytem.InventorySytem.cashiers;
import static inventorysytem.InventorySytem.cs;
import static inventorysytem.InventorySytem.dm;
import static inventorysytem.InventorySytem.workers;
import static inventorysytem.InventorySytem.OneMessage;
import static inventorysytem.InventorySytem.allMessage;
import static inventorysytem.InventorySytem.sendMsg;
import static inventorysytem.InventorySytem.Admins;
import static inventorysytem.InventorySytem.products;
import com.toedter.calendar.JDateChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

public class AdminFrame extends JFrame implements Serializable{
    
    JPanel Cards = new JPanel(new CardLayout());
    Server s;
    
    public Casher x = new Casher();
    public Deliveryman d = new Deliveryman();
    public CustomerServies customer = new CustomerServies();
    public Product Products = new Product();
    private Purchase purchase = new Purchase();
    private Sale sale = new Sale();
    private Warranty warr = new Warranty();
    
    JDateChooser reportdate = new JDateChooser();
    JPanel AddWorkers,ListWorkers = new JPanel(),ControlWorkers = new JPanel()
            ,StockItems,AddSupplier,ListSuppliers,ControlSuppliers,orderSup,returnSup,
            reportAnnoucment,AccountsPanel,enterProducts,theMarket,listProducts;
    
    private final String[] type = {"Type 1", "Type 2", "Type 3"};
    private final String[] categories = {"Category 1", "Category 2", "Category 3"};
    private final String[] Types = {"Type 1", "Type 2", "Type 3"};
    private final String[] warrentyOpt = {"YES", "NO"};
    private final String[] accFor = {"Cashier", "Customer Service"};
    private String WorkerType,Annoucment,OtherType;
    
    JComboBox types = new JComboBox(type);
    JComboBox category = new JComboBox(categories); JComboBox theType = new JComboBox(Types);
    JComboBox producttype = new JComboBox(type); JComboBox warrentyOption = new JComboBox(warrentyOpt);
    JComboBox UsersAccounts = new JComboBox(accFor); JComboBox producttypes = new JComboBox(type);
    
    public AdminFrame() {
        try {
            s = new Server();
        } catch (IOException ex) {
            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        initUI();
        add(Cards);
    }
    private void initUI(){
         
         this.addWindowListener(new WindowAdapter() {
            
            @Override
            public void windowClosing(WindowEvent e) {
                ObjectOutputStream Files1;
                ObjectOutputStream Files2;
                ObjectOutputStream Files3;
                ObjectOutputStream Files4;
                ObjectOutputStream Files5;
                ObjectOutputStream Files6;
                try {
                    Files1 = new ObjectOutputStream(new FileOutputStream("WorkersData\\Workers.bin"));
                    Files1.writeObject(workers);
                    Files1.close();
                    
                    Files2 = new ObjectOutputStream(new FileOutputStream("WorkersData\\Cashiers.bin"));
                    Files2.writeObject(cashiers);
                    Files2.close();
                    
                    Files3 = new ObjectOutputStream(new FileOutputStream("WorkersData\\DeliveryMen.bin"));
                    Files3.writeObject(dm);
                    Files3.close();
                    
                    Files4 = new ObjectOutputStream(new FileOutputStream("WorkersData\\CustomersService.bin"));
                    Files4.writeObject(cs);
                    Files4.close();
                    
                    Files5 = new ObjectOutputStream(new FileOutputStream("WorkersData\\Admins.bin"));
                    Files5.writeObject(Admins);
                    Files5.close();
                    
                    Files6 = new ObjectOutputStream(new FileOutputStream("WorkersData\\Products.bin"));
                    Files6.writeObject(products);
                    Files6.close();
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                try {
                    x.saveData();
                    customer.saveData();
                } catch (IOException ex) {
                    Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            @Override
            public void windowOpened(WindowEvent e) {
                ObjectInputStream Files1;
                ObjectInputStream Files2;
                ObjectInputStream Files3;
                ObjectInputStream Files4;
                ObjectInputStream Files5;
                ObjectInputStream Files6;
                try {
                    Files1 = new ObjectInputStream (new FileInputStream("WorkersData\\Workers.bin"));
                    workers = (ArrayList<Workers>)Files1.readObject();
                    Files1.close();
                    
                    Files2 = new ObjectInputStream (new FileInputStream("WorkersData\\Cashiers.bin"));
                    cashiers = (ArrayList<Casher>)Files2.readObject();
                    Files2.close();
                    
                    Files3 = new ObjectInputStream (new FileInputStream("WorkersData\\DeliveryMen.bin"));
                    dm = (ArrayList<Deliveryman>)Files3.readObject();
                    Files3.close();
                    
                    Files4 = new ObjectInputStream (new FileInputStream("WorkersData\\CustomersService.bin"));
                    cs = (ArrayList<CustomerServies>)Files4.readObject();
                    Files4.close();
                    
                    Files5 = new ObjectInputStream (new FileInputStream("WorkersData\\Admins.bin"));
                    Admins = (ArrayList<Admin>)Files5.readObject();
                    Files5.close();
                    
                    Files6 = new ObjectInputStream (new FileInputStream("WorkersData\\Products.bin"));
                    products = (ArrayList<Product>)Files6.readObject();
                    Files6.close();
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    x.getData();
                    customer.getData();
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
         
        initAddWorkers();
        initControlWorkers();
        initServer();
        reportMakeAnncument();
        initControlItemsStocks();
        initAddSupplier();
        initListSuppliers();
        initControlSuppliers();
        initAccounts();
        getFromSupplier();
        returnToSupplier();
        IntoSystemProduct();
        initMarket();
        ListProduct();
        
        setTitle("Adminstrator");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        createMenuBar();
        createPopupMenu();
        initAccounts();
    }
    private void createMenuBar() {

        JMenuBar menubar = new JMenuBar();

        ImageIcon iconAdd = new ImageIcon("images//new.png");
        ImageIcon iconControl = new ImageIcon("images//control.png");
        ImageIcon iconList = new ImageIcon("images//list.jpg");
        ImageIcon iconSup = new ImageIcon("images//suppliers.png");
        ImageIcon iconStock = new ImageIcon("images//stock.jpg");
        ImageIcon iconPro = new ImageIcon("images//items.png");
        ImageIcon iconWor = new ImageIcon("images//workers.png");
        ImageIcon iconMarket = new ImageIcon("images//market.png");
        ImageIcon iconAcc = new ImageIcon("images//accounts.png");
        ImageIcon iconRep = new ImageIcon("images//reports.png");
        ImageIcon iconChasher = new ImageIcon("images//cashier.jpg");
        ImageIcon iconCS = new ImageIcon("images//customerSerivce.png");
        ImageIcon iconDM = new ImageIcon("images//deleviery.png");
        ImageIcon iconOthers = new ImageIcon("images//rest.png");
        //ImageIcon iconserver = new ImageIcon("images//database.png");
        
        //JMenu serverMenu = new JMenu("Chat");
        //serverMenu.setIcon(iconserver);
        
        //JMenuItem chat = new JMenuItem("Chat with users");
        //serverMenu.add(chat);
        
        JMenu workersMenu = new JMenu("Workers");
        workersMenu.setIcon(iconWor);
        JMenu suppliersMenu = new JMenu("Supplier");
        suppliersMenu.setIcon(iconSup);
        JMenu stockMenu = new JMenu("Stock");
        stockMenu.setIcon(iconStock);
        JMenu ProductsMenu = new JMenu("Products");
        ProductsMenu.setIcon(iconPro);
        JMenu MarketMenu = new JMenu("Market");
        MarketMenu.setIcon(iconMarket);
        JMenu AccountsMenu = new JMenu("Accounts");
        AccountsMenu.setIcon(iconAcc);
        JMenu ReportsMenu = new JMenu("Reports");
        ReportsMenu.setIcon(iconRep);
        
        JMenu addMi = new JMenu("Add Data.. ");
        addMi.setIcon(iconAdd);
        JMenu controlMi = new JMenu("Control Data..");
        controlMi.setIcon(iconControl);
        JMenu ListMi = new JMenu("List Data..");
        ListMi.setIcon(iconList);
         
        JMenu supplierMi = new JMenu("Suppliers Data.. ");
        
        JMenuItem acasherMi = new JMenuItem("   Cashiers   ", iconChasher);
        JMenuItem acsMi = new JMenuItem("   Customer Services   ", iconCS);
        JMenuItem admMi = new JMenuItem("   Deleivery Men   ", iconDM);
        JMenuItem arestMi = new JMenuItem("   Others   ", iconOthers);
        
        JMenuItem ccasherMi = new JMenuItem("Cashiers...", iconChasher);
        JMenuItem ccsMi = new JMenuItem("Customer Services...", iconCS);
        JMenuItem cdmMi = new JMenuItem("Deleivery Men...", iconDM);
        JMenuItem crestMi = new JMenuItem("Others...", iconOthers);
        
        JMenuItem lcasherMi = new JMenuItem("Cashiers...", iconChasher);
        JMenuItem lcsMi = new JMenuItem("Customer Services...", iconCS);
        JMenuItem ldmMi = new JMenuItem("Deleivery Men...", iconDM);
        JMenuItem lrestMi = new JMenuItem("Others...", iconOthers);

        JMenuItem stockItemsMi = new JMenuItem("Stock Items Data.. ");
        
        JMenuItem addSupplierMi = new JMenuItem("Add Data..");
        JMenuItem controlSupplierMi = new JMenuItem("Control Data..");
        JMenuItem listSupplierMi = new JMenuItem("List Data..");
        JMenuItem getfromSupplierMi = new JMenuItem("Order From Supplier..");
        JMenuItem returntoSupplierMi = new JMenuItem("Return to Supplier..");
        
        JMenuItem MarketMi = new JMenuItem("Access Market..");
        
        JMenuItem MakeAnnoucmentsMi = new JMenuItem("Make Annucement..");
        JMenuItem statisticsMi = new JMenuItem("Statistics..");
        
        JMenuItem AccountsMi = new JMenuItem("Accounts..");
        
        JMenuItem productsMi = new JMenuItem("Products..");
        JMenuItem listproductsMi = new JMenuItem("List Products..");
        
        addMi.add(acasherMi);
        addMi.add(acsMi);
        addMi.add(admMi);
        addMi.add(arestMi);
        controlMi.add(ccasherMi);
        controlMi.add(ccsMi);
        controlMi.add(cdmMi);
        controlMi.add(crestMi);
        ListMi.add(lcasherMi);
        ListMi.add(lcsMi);
        ListMi.add(ldmMi);
        ListMi.add(lrestMi);
        supplierMi.add(addSupplierMi);
        supplierMi.add(controlSupplierMi);
        supplierMi.add(listSupplierMi);
        
        
        acasherMi.addActionListener((ActionEvent ae) -> {
            WorkerType = new String(); WorkerType = "Cashier";
            TitledBorder titled = new TitledBorder("Add "+WorkerType); // set title font // increase border thickness
            titled.setTitleJustification(TitledBorder.RIGHT);
            AddWorkers.setBorder(titled);
            AddWorkers.setVisible(true); listProducts.setVisible(false);
            theMarket.setVisible(false);enterProducts.setVisible(false);
            returnSup.setVisible(false);orderSup.setVisible(false);
            AccountsPanel.setVisible(false);ListSuppliers.setVisible(false);
            ControlSuppliers.setVisible(false);AddSupplier.setVisible(false);
            StockItems.setVisible(false);reportAnnoucment.setVisible(false);
            ControlWorkers.setVisible(false);ListWorkers.setVisible(false);
        });
        
        acsMi.addActionListener((ActionEvent event) -> {
            WorkerType = new String(); WorkerType = "CustomerService";
            TitledBorder titled = new TitledBorder("Add "+WorkerType);
            titled.setTitleJustification(TitledBorder.RIGHT);
            AddWorkers.setBorder(titled);
            AddWorkers.setVisible(true); listProducts.setVisible(false);
            theMarket.setVisible(false);enterProducts.setVisible(false);
            returnSup.setVisible(false);orderSup.setVisible(false);
            AccountsPanel.setVisible(false);ListSuppliers.setVisible(false);
            ControlSuppliers.setVisible(false);AddSupplier.setVisible(false);
            StockItems.setVisible(false);reportAnnoucment.setVisible(false);
            ControlWorkers.setVisible(false);ListWorkers.setVisible(false);
        });
        
        admMi.addActionListener((ActionEvent event) -> {
            WorkerType = new String(); WorkerType = "DeliveryMan";
            TitledBorder titled = new TitledBorder("Add "+WorkerType);
            titled.setTitleJustification(TitledBorder.RIGHT);
            AddWorkers.setBorder(titled);
            AddWorkers.setVisible(true); listProducts.setVisible(false);
            theMarket.setVisible(false);enterProducts.setVisible(false);
            returnSup.setVisible(false);orderSup.setVisible(false);
            AccountsPanel.setVisible(false);ListSuppliers.setVisible(false);
            ControlSuppliers.setVisible(false);AddSupplier.setVisible(false);
            StockItems.setVisible(false);reportAnnoucment.setVisible(false);
            ControlWorkers.setVisible(false);ListWorkers.setVisible(false);
        });
        
        arestMi.addActionListener((ActionEvent event) -> {
            WorkerType = new String(); WorkerType = "Not Supported";
            TitledBorder titled = new TitledBorder("Add Other Empolyee");
            titled.setTitleJustification(TitledBorder.RIGHT);
            AddWorkers.setBorder(titled);
            AddWorkers.setVisible(true); listProducts.setVisible(false);
            theMarket.setVisible(false);enterProducts.setVisible(false);
            returnSup.setVisible(false);orderSup.setVisible(false);
            AccountsPanel.setVisible(false);ListSuppliers.setVisible(false);
            ControlSuppliers.setVisible(false);AddSupplier.setVisible(false);
            StockItems.setVisible(false);reportAnnoucment.setVisible(false);
            ControlWorkers.setVisible(false);ListWorkers.setVisible(false);
        });
        
        lcasherMi.addActionListener((ActionEvent event) -> {
            WorkerType = new String(); WorkerType = "Cashier";
            ListWorkers.setVisible(false);
            initListWorkers();
            ListWorkers.setVisible(true); listProducts.setVisible(false);
            theMarket.setVisible(false);enterProducts.setVisible(false);
            returnSup.setVisible(false);orderSup.setVisible(false);
            AccountsPanel.setVisible(false);ListSuppliers.setVisible(false);
            ControlSuppliers.setVisible(false);AddSupplier.setVisible(false);
            StockItems.setVisible(false);reportAnnoucment.setVisible(false);
            ControlWorkers.setVisible(false);AddWorkers.setVisible(false);
        });
        
        lcsMi.addActionListener((ActionEvent event) -> {
            WorkerType = new String(); WorkerType = "CustomerService";
            ListWorkers.setVisible(false);
            initListWorkers();
            ListWorkers.setVisible(true); listProducts.setVisible(false);
            theMarket.setVisible(false);enterProducts.setVisible(false);
            returnSup.setVisible(false);orderSup.setVisible(false);
            AccountsPanel.setVisible(false);ListSuppliers.setVisible(false);
            ControlSuppliers.setVisible(false);AddSupplier.setVisible(false);
            StockItems.setVisible(false);reportAnnoucment.setVisible(false);
            ControlWorkers.setVisible(false);AddWorkers.setVisible(false);
        });
        
        ldmMi.addActionListener((ActionEvent event) -> {
            WorkerType = new String(); WorkerType = "DeliveryMan";
            ListWorkers.setVisible(false);
            initListWorkers();
            ListWorkers.setVisible(true); listProducts.setVisible(false);
            theMarket.setVisible(false);enterProducts.setVisible(false);
            returnSup.setVisible(false);orderSup.setVisible(false);
            AccountsPanel.setVisible(false);ListSuppliers.setVisible(false);
            ControlSuppliers.setVisible(false);AddSupplier.setVisible(false);
            StockItems.setVisible(false);reportAnnoucment.setVisible(false);
            ControlWorkers.setVisible(false);AddWorkers.setVisible(false);
        });
        
        lrestMi.addActionListener((ActionEvent event) -> {
            WorkerType = new String(); WorkerType = "Not Supported";
            ListWorkers.setVisible(false);
            initListWorkers();
            ListWorkers.setVisible(true); listProducts.setVisible(false);
            theMarket.setVisible(false);enterProducts.setVisible(false);
            returnSup.setVisible(false);orderSup.setVisible(false);
            AccountsPanel.setVisible(false);ListSuppliers.setVisible(false);
            ControlSuppliers.setVisible(false);AddSupplier.setVisible(false);
            StockItems.setVisible(false);reportAnnoucment.setVisible(false);
            ControlWorkers.setVisible(false);AddWorkers.setVisible(false);
        });
        
        ccasherMi.addActionListener((ActionEvent event) -> {
            WorkerType = new String(); WorkerType = "Cashier";
            ControlWorkers.setVisible(false);
            initControlWorkers();
            TitledBorder titled = new TitledBorder("Control Cashier");
            titled.setTitleJustification(TitledBorder.RIGHT);
            ControlWorkers.setBorder(titled); 
            ControlWorkers.setVisible(true); listProducts.setVisible(false);
            theMarket.setVisible(false);enterProducts.setVisible(false);
            returnSup.setVisible(false);orderSup.setVisible(false);
            AccountsPanel.setVisible(false);ListSuppliers.setVisible(false);
            ControlSuppliers.setVisible(false);AddSupplier.setVisible(false);
            StockItems.setVisible(false);reportAnnoucment.setVisible(false);
            ListWorkers.setVisible(false);AddWorkers.setVisible(false);
        });
        
        ccsMi.addActionListener((ActionEvent event) -> {
            WorkerType = new String(); WorkerType = "CustomerService";
            ControlWorkers.setVisible(false);
            initControlWorkers();
            TitledBorder titled = new TitledBorder("Control Customer Service");
            titled.setTitleJustification(TitledBorder.RIGHT);
            ControlWorkers.setBorder(titled);
            ControlWorkers.setVisible(true); listProducts.setVisible(false);
            theMarket.setVisible(false);enterProducts.setVisible(false);
            returnSup.setVisible(false);orderSup.setVisible(false);
            AccountsPanel.setVisible(false);ListSuppliers.setVisible(false);
            ControlSuppliers.setVisible(false);AddSupplier.setVisible(false);
            StockItems.setVisible(false);reportAnnoucment.setVisible(false);
            ListWorkers.setVisible(false);AddWorkers.setVisible(false);
        });
        
        cdmMi.addActionListener((ActionEvent event) -> {
            WorkerType = new String(); WorkerType = "DeliveryMan";
            ControlWorkers.setVisible(false);
            initControlWorkers();
            TitledBorder titled = new TitledBorder("Control Delevery Men");
            titled.setTitleJustification(TitledBorder.RIGHT);
            ControlWorkers.setBorder(titled);
            ControlWorkers.setVisible(true); listProducts.setVisible(false);
            theMarket.setVisible(false);enterProducts.setVisible(false);
            returnSup.setVisible(false);orderSup.setVisible(false);
            AccountsPanel.setVisible(false);ListSuppliers.setVisible(false);
            ControlSuppliers.setVisible(false);AddSupplier.setVisible(false);
            StockItems.setVisible(false);reportAnnoucment.setVisible(false);
            ListWorkers.setVisible(false);AddWorkers.setVisible(false);
        });
        
        crestMi.addActionListener((ActionEvent event) -> {
            WorkerType = new String(); WorkerType = "Not Supported";
            TitledBorder titled = new TitledBorder("Control Other Empolyee");
            titled.setTitleJustification(TitledBorder.RIGHT);
            ControlWorkers.setBorder(titled);
            ControlWorkers.setVisible(true); listProducts.setVisible(false);
            theMarket.setVisible(false);enterProducts.setVisible(false);
            returnSup.setVisible(false);orderSup.setVisible(false);
            AccountsPanel.setVisible(false);ListSuppliers.setVisible(false);
            ControlSuppliers.setVisible(false);AddSupplier.setVisible(false);
            StockItems.setVisible(false);reportAnnoucment.setVisible(false);
            ListWorkers.setVisible(false);AddWorkers.setVisible(false);
        });
        
        MakeAnnoucmentsMi.addActionListener((ActionEvent event) -> {
            reportAnnoucment.setVisible(true); listProducts.setVisible(false);
            theMarket.setVisible(false);enterProducts.setVisible(false);
            returnSup.setVisible(false);orderSup.setVisible(false);
            AccountsPanel.setVisible(false);ListSuppliers.setVisible(false);
            ControlSuppliers.setVisible(false);AddSupplier.setVisible(false);
            StockItems.setVisible(false);ControlWorkers.setVisible(false);
            ListWorkers.setVisible(false);AddWorkers.setVisible(false);
        });
        
        stockItemsMi.addActionListener((ActionEvent event) -> {
            StockItems.setVisible(true); listProducts.setVisible(false);
            theMarket.setVisible(false);enterProducts.setVisible(false);
            returnSup.setVisible(false);orderSup.setVisible(false);
            AccountsPanel.setVisible(false);ListSuppliers.setVisible(false);
            ControlSuppliers.setVisible(false);AddSupplier.setVisible(false);
            reportAnnoucment.setVisible(false);ControlWorkers.setVisible(false);
            ListWorkers.setVisible(false);AddWorkers.setVisible(false);
        });   
        
        addSupplierMi.addActionListener((ActionEvent event) -> {
            AddSupplier.setVisible(true); listProducts.setVisible(false);
            theMarket.setVisible(false);enterProducts.setVisible(false);
            returnSup.setVisible(false);orderSup.setVisible(false);
            AccountsPanel.setVisible(false);ListSuppliers.setVisible(false);
            ControlSuppliers.setVisible(false);StockItems.setVisible(false);
            reportAnnoucment.setVisible(false);ControlWorkers.setVisible(false);
            ListWorkers.setVisible(false);AddWorkers.setVisible(false);
        });
        
        controlSupplierMi.addActionListener((ActionEvent event) -> {
            ControlSuppliers.setVisible(true); listProducts.setVisible(false);
            theMarket.setVisible(false);enterProducts.setVisible(false);
            returnSup.setVisible(false);orderSup.setVisible(false);
            AccountsPanel.setVisible(false);ListSuppliers.setVisible(false);
            AddSupplier.setVisible(false);StockItems.setVisible(false);
            reportAnnoucment.setVisible(false);ControlWorkers.setVisible(false);
            ListWorkers.setVisible(false);AddWorkers.setVisible(false);
        });
        
        listSupplierMi.addActionListener((ActionEvent event) -> {
            ListSuppliers.setVisible(true); listProducts.setVisible(false);
            theMarket.setVisible(false);enterProducts.setVisible(false);
            returnSup.setVisible(false);orderSup.setVisible(false);
            AccountsPanel.setVisible(false);ControlSuppliers.setVisible(false);
            AddSupplier.setVisible(false);StockItems.setVisible(false);
            reportAnnoucment.setVisible(false);ControlWorkers.setVisible(false);
            ListWorkers.setVisible(false);AddWorkers.setVisible(false);
        });
        
        AccountsMi.addActionListener((ActionEvent event) -> {
            AccountsPanel.setVisible(true); listProducts.setVisible(false);
            theMarket.setVisible(false);enterProducts.setVisible(false);
            returnSup.setVisible(false);orderSup.setVisible(false);
            ListSuppliers.setVisible(false);ControlSuppliers.setVisible(false);
            AddSupplier.setVisible(false);StockItems.setVisible(false);
            reportAnnoucment.setVisible(false);ControlWorkers.setVisible(false);
            ListWorkers.setVisible(false);AddWorkers.setVisible(false);
        });
        
        getfromSupplierMi.addActionListener((ActionEvent event) -> {
            orderSup.setVisible(true); listProducts.setVisible(false);
            theMarket.setVisible(false);enterProducts.setVisible(false);
            returnSup.setVisible(false);AccountsPanel.setVisible(false);
            ListSuppliers.setVisible(false);ControlSuppliers.setVisible(false);
            AddSupplier.setVisible(false);StockItems.setVisible(false);
            reportAnnoucment.setVisible(false);ControlWorkers.setVisible(false);
            ListWorkers.setVisible(false);AddWorkers.setVisible(false);
        });
        
        returntoSupplierMi.addActionListener((ActionEvent event) -> {
            returnSup.setVisible(true); listProducts.setVisible(false);
            theMarket.setVisible(false);enterProducts.setVisible(false);
            orderSup.setVisible(false);AccountsPanel.setVisible(false);
            ListSuppliers.setVisible(false);ControlSuppliers.setVisible(false);
            AddSupplier.setVisible(false);StockItems.setVisible(false);
            reportAnnoucment.setVisible(false);ControlWorkers.setVisible(false);
            ListWorkers.setVisible(false);AddWorkers.setVisible(false);
        });
        
        productsMi.addActionListener((ActionEvent event) -> {
            enterProducts.setVisible(true); listProducts.setVisible(false);
            theMarket.setVisible(false);returnSup.setVisible(false);
            orderSup.setVisible(false);AccountsPanel.setVisible(false);
            ListSuppliers.setVisible(false);ControlSuppliers.setVisible(false);
            AddSupplier.setVisible(false);StockItems.setVisible(false);
            reportAnnoucment.setVisible(false);ControlWorkers.setVisible(false);
            ListWorkers.setVisible(false);AddWorkers.setVisible(false);
        });
        
        MarketMi.addActionListener((ActionEvent event) -> {
            theMarket.setVisible(true); listProducts.setVisible(false);
            enterProducts.setVisible(false);returnSup.setVisible(false);
            orderSup.setVisible(false);AccountsPanel.setVisible(false);
            ListSuppliers.setVisible(false);ControlSuppliers.setVisible(false);
            AddSupplier.setVisible(false);StockItems.setVisible(false);
            reportAnnoucment.setVisible(false);ControlWorkers.setVisible(false);
            ListWorkers.setVisible(false);AddWorkers.setVisible(false);
        });
        listproductsMi.addActionListener((ActionEvent event) -> {
            listProducts.setVisible(false);
            ListProduct();
            listProducts.setVisible(true); theMarket.setVisible(false);
            enterProducts.setVisible(false);returnSup.setVisible(false);
            orderSup.setVisible(false);AccountsPanel.setVisible(false);
            ListSuppliers.setVisible(false);ControlSuppliers.setVisible(false);
            AddSupplier.setVisible(false);StockItems.setVisible(false);
            reportAnnoucment.setVisible(false);ControlWorkers.setVisible(false);
            ListWorkers.setVisible(false);AddWorkers.setVisible(false);
        });
        
        statisticsMi.addActionListener((ActionEvent event) -> {
            ChartComponents bar = new ChartComponents();
            bar.graphFrame();
        });
        
        workersMenu.add(addMi);
        workersMenu.addSeparator();
        workersMenu.add(controlMi);
        workersMenu.addSeparator();
        workersMenu.add(ListMi);
        
        suppliersMenu.add(supplierMi);
        suppliersMenu.addSeparator();
        suppliersMenu.add(getfromSupplierMi);
        suppliersMenu.addSeparator();
        suppliersMenu.add(returntoSupplierMi);
        
        stockMenu.add(stockItemsMi);
        
        MarketMenu.add(MarketMi);
        
        ReportsMenu.add(MakeAnnoucmentsMi);
        ReportsMenu.addSeparator();
        ReportsMenu.add(statisticsMi);
        
        AccountsMenu.add(AccountsMi);
        
        ProductsMenu.add(productsMi);
        ProductsMenu.addSeparator();
        ProductsMenu.add(listproductsMi);
        
        menubar.add(workersMenu);
        menubar.add(suppliersMenu);
        menubar.add(stockMenu);
        menubar.add(ProductsMenu);
        menubar.add(MarketMenu);
        menubar.add(AccountsMenu);
        menubar.add(ReportsMenu);

        setJMenuBar(menubar);
    }
    private void createPopupMenu() {
        JPopupMenu pmenu = new JPopupMenu();
        
        JMenuItem outMi = new JMenuItem("LogOut");
        outMi.addActionListener((ActionEvent e) -> {
        LoginFrame loginDlg = new LoginFrame();
        loginDlg.setVisible(true);
        loginDlg.setTitle("Authenticate Access");
        dispose();
        try {
            x.saveData();
            customer.saveData();
        } catch (IOException ex) {
            Logger.getLogger(AdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
        pmenu.add(outMi);

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {

                if (e.getButton() == MouseEvent.BUTTON3) {
                    pmenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }
    // =====================WORKERS=======================
    private void initAddWorkers(){
        JTextField firstname = new JTextField(20);JTextField lastname = new JTextField(20);
        JTextField phonenumber = new JTextField(20);JTextField theemail = new JTextField(20);
        JTextField cityname = new JTextField(20);JTextField statename = new JTextField(20);
        JTextField address1 = new JTextField(20);JTextField address2 = new JTextField(20);
        JTextField streetnumber = new JTextField(20);JTextField blocknumber = new JTextField(20);
        
        OtherType = new String();
        
        String[] arr = {"Nothing","Cashier","Customer Service","Delivery Man"};
        
        JComboBox otherType = new JComboBox(arr);
        otherType.setSelectedIndex(0);
        
        JButton ok = new JButton("Save");
        JButton clr = new JButton("Clear");
        
        ok.addActionListener((ActionEvent ae) -> {
            OtherType = (String)otherType.getSelectedItem();
            switch (WorkerType) {
                case "Cashier":
                    if (OtherType.equals("Cashier")) {
                        JOptionPane.showMessageDialog(null, "You can't add 2 Cashiers with same data.", "Alert", JOptionPane.WARNING_MESSAGE);
                        otherType.setSelectedIndex(0);
                    }
                    else if (OtherType.equals("Customer Service")) {
                        
                        Casher cashier = new Casher();
                        CustomerServies cs1 = new CustomerServies();
                        IUser[] users = new IUser[2];
                        users[0] = cashier; users[1] = cs1;
                        
                        for (int i = 0; i < 2; i++) {
                            users[i].add(firstname.getText(),lastname.getText(),phonenumber.getText(),theemail.getText(),
                                cityname.getText(),statename.getText(),address1.getText(),address2.getText(),
                                streetnumber.getText(),blocknumber.getText());
                        }
                        
                        firstname.setText("");lastname.setText("");theemail.setText("");phonenumber.setText("");
                        cityname.setText("");statename.setText("");address1.setText("");address2.setText("");
                        blocknumber.setText("");streetnumber.setText("");otherType.setSelectedIndex(0);
                    }
                    else if (OtherType.equals("Delivery Man")) {
                        
                        Casher cashier = new Casher();
                        Deliveryman dm1 = new Deliveryman();
                        IUser[] users = new IUser[2];
                        users[0] = cashier; users[1] = dm1;
                        
                        for (int i = 0; i < 2; i++) {
                            users[i].add(firstname.getText(),lastname.getText(),phonenumber.getText(),theemail.getText(),
                                cityname.getText(),statename.getText(),address1.getText(),address2.getText(),
                                streetnumber.getText(),blocknumber.getText());
                        }
                        
                        firstname.setText("");lastname.setText("");theemail.setText("");phonenumber.setText("");
                        cityname.setText("");statename.setText("");address1.setText("");address2.setText("");
                        blocknumber.setText("");streetnumber.setText("");otherType.setSelectedIndex(0);
                    }else{
                        Casher cashier = new Casher();
                        cashier.add(firstname.getText(),lastname.getText(),phonenumber.getText(),theemail.getText(),
                                cityname.getText(),statename.getText(),address1.getText(),address2.getText(),
                                streetnumber.getText(),blocknumber.getText());
                    
                        firstname.setText("");lastname.setText("");theemail.setText("");phonenumber.setText("");
                        cityname.setText("");statename.setText("");address1.setText("");address2.setText("");
                        blocknumber.setText("");streetnumber.setText("");otherType.setSelectedIndex(0);
                    }
                    break;
                case "CustomerService":
                    if (OtherType.equals("Cashier")) {
                        
                        CustomerServies cs1 = new CustomerServies();
                        Casher cashier = new Casher();
                        IUser[] users = new IUser[2];
                        users[0] = cashier; users[1] = cs1;
                        
                        for (int i = 0; i < 2; i++) {
                            users[i].add(firstname.getText(),lastname.getText(),phonenumber.getText(),theemail.getText(),
                                cityname.getText(),statename.getText(),address1.getText(),address2.getText(),
                                streetnumber.getText(),blocknumber.getText());
                        }
                        
                        firstname.setText("");lastname.setText("");theemail.setText("");phonenumber.setText("");
                        cityname.setText("");statename.setText("");address1.setText("");address2.setText("");
                        blocknumber.setText("");streetnumber.setText("");otherType.setSelectedIndex(0);
                    }
                    else if (OtherType.equals("Customer Service")) {
                        
                        JOptionPane.showMessageDialog(null, "You can't add 2 Customer Service with same data.", "Alert", JOptionPane.WARNING_MESSAGE);
                        otherType.setSelectedIndex(0);
                    }
                    else if (OtherType.equals("Delivery Man")) {
                        
                        CustomerServies cs1 = new CustomerServies();
                        Deliveryman dm1 = new Deliveryman();
                        IUser[] users = new IUser[2];
                        users[0] = cs1; users[1] = dm1;
                        
                        for (int i = 0; i < 2; i++) {
                            users[i].add(firstname.getText(),lastname.getText(),phonenumber.getText(),theemail.getText(),
                                cityname.getText(),statename.getText(),address1.getText(),address2.getText(),
                                streetnumber.getText(),blocknumber.getText());
                        }
                        
                        firstname.setText("");lastname.setText("");theemail.setText("");phonenumber.setText("");
                        cityname.setText("");statename.setText("");address1.setText("");address2.setText("");
                        blocknumber.setText("");streetnumber.setText("");otherType.setSelectedIndex(0);
                    }else{
                        CustomerServies cs = new CustomerServies();
                        cs.add(firstname.getText(),lastname.getText(),phonenumber.getText(),theemail.getText(),
                                cityname.getText(),statename.getText(),address1.getText(),address2.getText(),
                                streetnumber.getText(),blocknumber.getText());
                        
                        firstname.setText("");lastname.setText("");theemail.setText("");phonenumber.setText("");
                        cityname.setText("");statename.setText("");address1.setText("");address2.setText("");
                        blocknumber.setText("");streetnumber.setText("");otherType.setSelectedIndex(0);
                    }
                    break;
                case "DeliveryMan":
                    if (OtherType.equals("Cashier")) {
                        
                        Deliveryman dm1 = new Deliveryman();
                        Casher cashier = new Casher();
                        IUser[] users = new IUser[2];
                        users[0] = cashier; users[1] = dm1;
                        
                        for (int i = 0; i < 2; i++) {
                            users[i].add(firstname.getText(),lastname.getText(),phonenumber.getText(),theemail.getText(),
                                cityname.getText(),statename.getText(),address1.getText(),address2.getText(),
                                streetnumber.getText(),blocknumber.getText());
                        }
                        
                        firstname.setText("");lastname.setText("");theemail.setText("");phonenumber.setText("");
                        cityname.setText("");statename.setText("");address1.setText("");address2.setText("");
                        blocknumber.setText("");streetnumber.setText("");otherType.setSelectedIndex(0);
                    }
                    else if (OtherType.equals("Customer Service")) {
                        
                        Deliveryman dm1 = new Deliveryman();
                        CustomerServies cs1 = new CustomerServies();
                        IUser[] users = new IUser[2];
                        users[0] = cs1; users[1] = dm1;
                        
                        for (int i = 0; i < 2; i++) {
                            users[i].add(firstname.getText(),lastname.getText(),phonenumber.getText(),theemail.getText(),
                                cityname.getText(),statename.getText(),address1.getText(),address2.getText(),
                                streetnumber.getText(),blocknumber.getText());
                        }
                        
                        firstname.setText("");lastname.setText("");theemail.setText("");phonenumber.setText("");
                        cityname.setText("");statename.setText("");address1.setText("");address2.setText("");
                        blocknumber.setText("");streetnumber.setText("");otherType.setSelectedIndex(0);
                    }
                    else if (OtherType.equals("Delivery Man")) {
                        JOptionPane.showMessageDialog(null, "You can't add 2 Delivery Man with same data.", "Alert", JOptionPane.WARNING_MESSAGE);
                        otherType.setSelectedIndex(0);
                    }else{
                        Deliveryman dvm = new Deliveryman();
                        dvm.add(firstname.getText(),lastname.getText(),phonenumber.getText(),theemail.getText(),
                                cityname.getText(),statename.getText(),address1.getText(),address2.getText(),
                                streetnumber.getText(),blocknumber.getText());
                    
                        firstname.setText("");lastname.setText("");theemail.setText("");phonenumber.setText("");
                        cityname.setText("");statename.setText("");address1.setText("");address2.setText("");
                        blocknumber.setText("");streetnumber.setText("");otherType.setSelectedIndex(0);
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Not Supported Yet.", "Alert", JOptionPane.WARNING_MESSAGE);
                    
                    firstname.setText("");lastname.setText("");theemail.setText("");phonenumber.setText("");
                    cityname.setText("");statename.setText("");address1.setText("");address2.setText("");
                    blocknumber.setText("");streetnumber.setText("");otherType.setSelectedIndex(0);
                    
                    break;
            }
        });
        
        clr.addActionListener((ActionEvent ae) -> {
            firstname.setText("");lastname.setText("");theemail.setText("");phonenumber.setText("");
            cityname.setText("");statename.setText("");address1.setText("");address2.setText("");
            blocknumber.setText("");streetnumber.setText("");otherType.setSelectedIndex(0);
        });
        
        AddWorkers = new JPanel(null);
        AddWorkers.setBackground(Color.LIGHT_GRAY);
        
        JLabel lb1 = new JLabel("Personal Information: ");
        lb1.setBounds(40, 35, 200, 20);
        AddWorkers.add(lb1);
        
        JLabel first = new JLabel("First Name: ");
        first.setBounds(50,65,100,20);
        AddWorkers.add(first);
        firstname.setBounds(first.getBounds().width+50,65,100,20);
        AddWorkers.add(firstname);
        
        JLabel second = new JLabel("Second Name: ");
        second.setBounds(300,65,100,20);
        AddWorkers.add(second);
        lastname.setBounds(second.getBounds().width+300,65,100,20);
        AddWorkers.add(lastname);
        
        JLabel lb2 = new JLabel("Conatact Information: ");
        lb2.setBounds(40,95,200,20);
        AddWorkers.add(lb2);
        
        JLabel email = new JLabel("Email: ");
        email.setBounds(50,125,100,20);
        AddWorkers.add(email);
        theemail.setBounds(email.getBounds().width+50,125,100,20);
        AddWorkers.add(theemail);
        
        JLabel phone = new JLabel("Phone Number: ");
        phone.setBounds(300,125,100,20);
        AddWorkers.add(phone);
        phonenumber.setBounds(phone.getBounds().width+300,125,100,20);
        AddWorkers.add(phonenumber);
        
        JLabel lb3 = new JLabel("Address Information: ");
        lb3.setBounds(40,155,200,20);
        AddWorkers.add(lb3);
        
        JLabel city = new JLabel("City Name: ");
        city.setBounds(50,185,100,20);
        AddWorkers.add(city);
        cityname.setBounds(city.getBounds().width+50,185,100,20);
        AddWorkers.add(cityname);
        
        JLabel state = new JLabel("State Name: ");
        state.setBounds(300,185,100,20);
        AddWorkers.add(state);
        statename.setBounds(state.getBounds().width+300,185,100,20);
        AddWorkers.add(statename);
        
        JLabel street = new JLabel("Street Number: ");
        street.setBounds(50,215,100,20);
        AddWorkers.add(street);
        streetnumber.setBounds(street.getBounds().width+50,215,100,20);
        AddWorkers.add(streetnumber);
        
        JLabel blockno = new JLabel("Block Number: ");
        blockno.setBounds(300,215,100,20);
        AddWorkers.add(blockno);
        blocknumber.setBounds(blockno.getBounds().width+300,215,100,20);
        AddWorkers.add(blocknumber);

        JLabel addressone = new JLabel("Address One: ");
        addressone.setBounds(50,245,100,20);
        AddWorkers.add(addressone);
        address1.setBounds(addressone.getBounds().width+50,245,100,20);
        AddWorkers.add(address1);
        
        JLabel addresstwo = new JLabel("Address Two: ");
        addresstwo.setBounds(300,245,100,20);
        AddWorkers.add(addresstwo);
        address2.setBounds(addresstwo.getBounds().width+300,245,100,20);
        AddWorkers.add(address2);
        
        otherType.setBounds(50,300,200,30);
        AddWorkers.add(otherType);
        
        ok.setBounds(350,300,100,35);
        AddWorkers.add(ok);
        
        clr.setBounds(460,300,100,35);
        AddWorkers.add(clr);
        
        Cards.add(AddWorkers, "card1");
    } 
    private void initListWorkers() {
        
        ListWorkers = new JPanel (null);
        ListWorkers.setBackground(Color.LIGHT_GRAY);
        
        String[] columns = {"ID", "FName", "LName", "Email",
                            "Phone", "City", "State","Street", "Block No.", "Address1", "Address2"};
        
        DefaultTableModel tablemodel = new DefaultTableModel(columns, 0);
        
        switch (WorkerType) {
            case "Cashier":
                ArrayList<Casher> cas = x.list();
                for (Casher c : cas) {
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
                }       break;
            case "CustomerService":
                ArrayList<CustomerServies> cus = customer.list();
                for (CustomerServies c : cus) {
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
                }       break;
            case "DeliveryMan":
                ArrayList<Deliveryman> delivery = d.list();
                for (Deliveryman c : delivery) {
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
                }       break;
            default:
                JOptionPane.showMessageDialog(null, "Not Supported Yet.", "Alert", JOptionPane.WARNING_MESSAGE);
                break;
        }
        
 
        JTable table = new JTable(tablemodel);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        
        ListWorkers.setLayout(new BorderLayout());

        ListWorkers.add(scrollPane,BorderLayout.CENTER);
        
        Cards.add(ListWorkers,"card2");
    } 
    private void initControlWorkers() {
       JTextField firstname = new JTextField(20);JTextField lastname = new JTextField(20);
       JTextField theemail = new JTextField(20);JTextField phonenumber = new JTextField(20);
       JTextField cityname = new JTextField(20);JTextField statename = new JTextField(20);
       JTextField address1 = new JTextField(20);JTextField address2 = new JTextField(20);
       JTextField blocknumber = new JTextField(20);JTextField searchHere = new JTextField(20);
       JTextField streetname = new JTextField(20);
       
       JButton search = new JButton("Search");JButton delete = new JButton("Delete");
       JButton modify = new JButton("Modify");JButton listedData = new JButton("Show Listed Data");
       
       listedData.addActionListener((ActionEvent ae) -> {
            ListWorkers.setVisible(false);
            initListWorkers();
            ListWorkers.setVisible(true);
            theMarket.setVisible(false);enterProducts.setVisible(false);
            returnSup.setVisible(false);orderSup.setVisible(false);
            AccountsPanel.setVisible(false);ListSuppliers.setVisible(false);
            ControlSuppliers.setVisible(false);AddSupplier.setVisible(false);
            StockItems.setVisible(false);reportAnnoucment.setVisible(false);
            ControlWorkers.setVisible(false);AddWorkers.setVisible(false);
       });
       
       search.addActionListener((ActionEvent ae) -> {
           switch (WorkerType) {
               case "Cashier":
                   x = x.search(searchHere.getText());
                   firstname.setText(x.con.getFirstname());
                   lastname.setText(x.con.getLastname());
                   theemail.setText(x.con.getEmail());
                   phonenumber.setText(x.con.getPhonenumber());
                   cityname.setText(x.address.getCityname());
                   statename.setText(x.address.getStatename());
                   address1.setText(x.address.getAddressone());
                   address2.setText(x.address.getAddresstwo());
                   blocknumber.setText(x.address.getBlockNumber());
                   streetname.setText(x.address.getStreetnumber());
                   break;
               case "CustomerService":
                   customer = customer.search(searchHere.getText());
                   firstname.setText(customer.con.getFirstname());
                   lastname.setText(customer.con.getLastname());
                   theemail.setText(customer.con.getEmail());
                   phonenumber.setText(customer.con.getPhonenumber());
                   cityname.setText(customer.address.getCityname());
                   statename.setText(customer.address.getStatename());
                   address1.setText(customer.address.getAddressone());
                   address2.setText(customer.address.getAddresstwo()); 
                   blocknumber.setText(customer.address.getBlockNumber());
                   streetname.setText(customer.address.getStreetnumber());
                   break;
               case "DeliveryMan":
                   d = d.search(searchHere.getText());
                   firstname.setText(d.con.getFirstname());
                   lastname.setText(d.con.getLastname());
                   theemail.setText(d.con.getEmail());
                   phonenumber.setText(d.con.getPhonenumber());
                   cityname.setText(d.address.getCityname());
                   statename.setText(d.address.getStatename());
                   address1.setText(d.address.getAddressone());
                   address2.setText(d.address.getAddresstwo());
                   blocknumber.setText(d.address.getBlockNumber());
                   streetname.setText(d.address.getStreetnumber());
                   break;
               default:
                   JOptionPane.showMessageDialog(null, "Not Supported Yet.", "Alert", JOptionPane.WARNING_MESSAGE);
                   break;
           }
       });
       delete.addActionListener((ActionEvent ae) -> {
           switch (WorkerType)
           {
               case "Cashier":
                   if (x.del(searchHere.getText()))
                   {
                       firstname.setText("");lastname.setText("");
                       theemail.setText("");phonenumber.setText("");
                       cityname.setText("");statename.setText("");
                       address1.setText("");address2.setText("");
                       blocknumber.setText("");streetname.setText("");
                   }
                   else{
                       JOptionPane.showMessageDialog(null, "Cant Delete ID number : "+ searchHere.getText(), "Alert", JOptionPane.WARNING_MESSAGE);
                   }
                   break;
               case "CustomerService":
                   if (customer.del(searchHere.getText()))
                   {
                       firstname.setText("");lastname.setText("");
                       theemail.setText("");phonenumber.setText("");
                       cityname.setText("");statename.setText("");
                       address1.setText("");address2.setText("");
                       blocknumber.setText("");streetname.setText("");
                   }
                   else{
                       JOptionPane.showMessageDialog(null, "Cant Delete ID number : "+ searchHere.getText(), "Alert", JOptionPane.WARNING_MESSAGE);
                   }
                   break;
               case "DeliveryMan":
                   if (d.del(searchHere.getText()))
                   {
                       firstname.setText("");lastname.setText("");
                       theemail.setText("");phonenumber.setText("");
                       cityname.setText("");statename.setText("");
                       address1.setText("");address2.setText("");
                       blocknumber.setText("");streetname.setText("");
                   }
                   else{
                       JOptionPane.showMessageDialog(null, "Cant Delete ID number : "+ searchHere.getText(), "Alert", JOptionPane.WARNING_MESSAGE);
                   }
                   break;
               default:
                   JOptionPane.showMessageDialog(null, "Not Supported Yet.", "Alert", JOptionPane.WARNING_MESSAGE);
                   break;
           }
       });
       
       modify.addActionListener((ActionEvent ae) -> {
           switch (WorkerType)
           {
               case "Cashier":
                   if (!firstname.getText().equals("")) {
                       x.modify(searchHere.getText(), firstname.getText(), "FirstName");
                   }
                   if (!lastname.getText().equals("")) {
                       x.modify(searchHere.getText(), lastname.getText(), "LastName");
                   }
                   if (!theemail.getText().equals("")) {
                       x.modify(searchHere.getText(), theemail.getText(), "Email");
                   }
                   if (!phonenumber.getText().equals("")) {
                       x.modify(searchHere.getText(), phonenumber.getText(), "PhoneNumber");
                   }
                   if (!cityname.getText().equals("")) {
                       x.modify(searchHere.getText(), cityname.getText(), "City");
                   }
                   if (!statename.getText().equals("")) {
                       x.modify(searchHere.getText(), statename.getText(), "State");
                   }
                   if (!address1.getText().equals("")) {
                       x.modify(searchHere.getText(), address1.getText(), "AddressOne");
                   }
                   if (!address2.getText().equals("")) {
                       x.modify(searchHere.getText(), address2.getText(), "AddressTwo");
                   }
                   if (!blocknumber.getText().equals("")) {
                       x.modify(searchHere.getText(), blocknumber.getText(), "BlockNumber");
                   }
                   if (!streetname.getText().equals("")) {
                       x.modify(searchHere.getText(), streetname.getText(), "Street");
                   }
                   break;
               case "CustomerService":
                   if (!firstname.getText().equals("")) {
                       customer.modify(searchHere.getText(), firstname.getText(), "FirstName");
                   }
                   if (!lastname.getText().equals("")) {
                       customer.modify(searchHere.getText(), lastname.getText(), "LastName");
                   }
                   if (!theemail.getText().equals("")) {
                       customer.modify(searchHere.getText(), theemail.getText(), "Email");
                   }
                   if (!phonenumber.getText().equals("")) {
                       customer.modify(searchHere.getText(), phonenumber.getText(), "PhoneNumber");
                   }
                   if (!cityname.getText().equals("")) {
                       customer.modify(searchHere.getText(), cityname.getText(), "City");
                   }
                   if (!statename.getText().equals("")) {
                       customer.modify(searchHere.getText(), statename.getText(), "State");
                   }
                   if (!address1.getText().equals("")) {
                       customer.modify(searchHere.getText(), address1.getText(), "AddressOne");
                   }
                   if (!address2.getText().equals("")) {
                       customer.modify(searchHere.getText(), address2.getText(), "AddressTwo");
                   }
                   if (!blocknumber.getText().equals("")) {
                       customer.modify(searchHere.getText(), blocknumber.getText(), "BlockNumber");
                   }
                   if (!streetname.getText().equals("")) {
                       customer.modify(searchHere.getText(), streetname.getText(), "Street");
                   }
                   break;
               case "DeliveryMan":
                   if (!firstname.getText().equals("")) {
                       d.modify(searchHere.getText(), firstname.getText(), "FirstName");
                   }
                   if (!lastname.getText().equals("")) {
                       d.modify(searchHere.getText(), lastname.getText(), "LastName");
                   }
                   if (!theemail.getText().equals("")) {
                       d.modify(searchHere.getText(), theemail.getText(), "Email");
                   }
                   if (!phonenumber.getText().equals("")) {
                       d.modify(searchHere.getText(), phonenumber.getText(), "PhoneNumber");
                   }
                   if (!cityname.getText().equals("")) {
                       d.modify(searchHere.getText(), cityname.getText(), "City");
                   }
                   if (!statename.getText().equals("")) {
                       d.modify(searchHere.getText(), statename.getText(), "State");
                   }
                   if (!address1.getText().equals("")) {
                       d.modify(searchHere.getText(), address1.getText(), "AddressOne");
                   }
                   if (!address2.getText().equals("")) {
                       d.modify(searchHere.getText(), address2.getText(), "AddressTwo");
                   }
                   if (!blocknumber.getText().equals("")) {
                       d.modify(searchHere.getText(), blocknumber.getText(), "BlockNumber");
                   }
                   if (!streetname.getText().equals("")) {
                       d.modify(searchHere.getText(), streetname.getText(), "Street");
                   }
                   break;
               default:
                   JOptionPane.showMessageDialog(null, "Not Supported Yet.", "Alert", JOptionPane.WARNING_MESSAGE);
                  break;
           }      
       });
       
        
      ControlWorkers = new JPanel(null);
      ControlWorkers.setBackground(Color.LIGHT_GRAY);
      
      searchHere.setBounds(150, 20, 200, 20);
      ControlWorkers.add(searchHere);
      search.setBounds(searchHere.getBounds().width+150,20,100,20);
      ControlWorkers.add(search);
        
      JLabel lb1 = new JLabel("Personal Information: ");
      lb1.setBounds(40, 50, 200, 20);
      ControlWorkers.add(lb1);
        
      JLabel first = new JLabel("First Name: ");
      first.setBounds(50,80,100,20);
      ControlWorkers.add(first);
      firstname.setBounds(first.getBounds().width+50,80,100,20);
      ControlWorkers.add(firstname);
      
      JLabel second = new JLabel("Second Name: ");
      second.setBounds(300,80,100,20);
      ControlWorkers.add(second);
      lastname.setBounds(second.getBounds().width+300,80,100,20);
      ControlWorkers.add(lastname);
        
      JLabel lb2 = new JLabel("Conatact Information: ");
      lb2.setBounds(40,110,200,20);
      ControlWorkers.add(lb2);
        
      JLabel email = new JLabel("Email: ");
      email.setBounds(50,140,100,20);
      ControlWorkers.add(email);
      theemail.setBounds(email.getBounds().width+50,140,100,20);
      ControlWorkers.add(theemail);
        
      JLabel phone = new JLabel("Phone Number: ");
      phone.setBounds(300,140,100,20);
      ControlWorkers.add(phone);
      phonenumber.setBounds(phone.getBounds().width+300,140,100,20);
      ControlWorkers.add(phonenumber);
        
      JLabel lb3 = new JLabel("Address Information: ");
      lb3.setBounds(40,170,200,20);
      ControlWorkers.add(lb3);
        
      JLabel city = new JLabel("City Name: ");
      city.setBounds(50,200,100,20);
      ControlWorkers.add(city);
      cityname.setBounds(city.getBounds().width+50,200,100,20);
      ControlWorkers.add(cityname);
        
      JLabel state = new JLabel("State Name: ");
      state.setBounds(300,200,100,20);
      ControlWorkers.add(state);
      statename.setBounds(state.getBounds().width+300,200,100,20);
      ControlWorkers.add(statename);
      
      JLabel street = new JLabel("Street Name: ");
      street.setBounds(50,230,100,20);
      ControlWorkers.add(street);
      streetname.setBounds(street.getBounds().width+50,230,100,20);
      ControlWorkers.add(streetname);
      
      JLabel blockno = new JLabel("Block Number: ");
      blockno.setBounds(300,230,100,20);
      ControlWorkers.add(blockno);
      blocknumber.setBounds(blockno.getBounds().width+300,230,100,20);
      ControlWorkers.add(blocknumber);
      
      JLabel addressone = new JLabel("Address One: ");
      addressone.setBounds(50,260,100,20);
      ControlWorkers.add(addressone);
      address1.setBounds(addressone.getBounds().width+50,260,100,20);
      ControlWorkers.add(address1);
        
      JLabel addresstwo = new JLabel("Address Two: ");
      addresstwo.setBounds(300,260,100,20);
      ControlWorkers.add(addresstwo);
      address2.setBounds(addresstwo.getBounds().width+300,260,100,20);
      ControlWorkers.add(address2);
        
        
      delete.setBounds(170,330,100,35);
      ControlWorkers.add(delete);
        
      modify.setBounds(280,330,100,35);
      ControlWorkers.add(modify);
        
      listedData.setBounds(430,45,135,30);
      ControlWorkers.add(listedData);
        
      Cards.add(ControlWorkers, "card3");
    } 
    // =====================REPORTS=======================
    private void reportMakeAnncument() { 
    JTextArea annoucment = new JTextArea("Enter Text Here..");
    reportAnnoucment = new JPanel(null);
    reportAnnoucment.setBackground(Color.LIGHT_GRAY);
        
    TitledBorder titled = new TitledBorder("Report Annoucment");
    titled.setTitleJustification(TitledBorder.RIGHT);
    reportAnnoucment.setBorder(titled);
        
    Border border = BorderFactory.createLineBorder(Color.BLACK);
    annoucment.setBounds(50,45,500,200);
    annoucment.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    reportAnnoucment.add(annoucment);
        
    JLabel expDate = new JLabel("Report Date: ");
    expDate.setBounds(75,270,100,20);
    reportAnnoucment.add(expDate);
    reportdate.setBounds(expDate.getBounds().width+80,270,120,20);
    reportAnnoucment.add(reportdate);
        
    JLabel ReportNumber = new JLabel("Report Number: ");
    ReportNumber.setBounds(75,310,100,20);
    reportAnnoucment.add(ReportNumber);
    JSpinner reportnumber = new JSpinner(new SpinnerNumberModel(1, 1, 2000, 1));  
    reportnumber.setBounds(ReportNumber.getBounds().width+80,310,50,30);
    reportAnnoucment.add(reportnumber);
        
    JButton makeAnn = new JButton("Send Anncoument"); 
    makeAnn.setBounds(370,280,150,35);
    reportAnnoucment.add(makeAnn);
    
    allMessage.setBounds(75,330,150,50);
    reportAnnoucment.add(allMessage);
    
    OneMessage.setBounds(50,400,500,90);
    OneMessage.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    reportAnnoucment.add(OneMessage);
      
    sendMsg.setBounds(450,510,70,25);
    reportAnnoucment.add(sendMsg);
        
    makeAnn.addActionListener((ActionEvent event) -> {
        Annoucment = new String();
        Annoucment = annoucment.getText();
        
        int reportNUM = (Integer)reportnumber.getValue();
        
        Date reportDatee = reportdate.getDate();
        
        report.announcementReport(Annoucment, reportDatee, reportNUM);
    });
        
    Cards.add(reportAnnoucment, "card4");
    }
    // =====================STOCK=========================
    private void initControlItemsStocks(){
        JTextField stockplace = new JTextField(20);JTextField productname = new JTextField(20);
        JTextField searchHere = new JTextField(20);JTextField brandname = new JTextField(20);
        
        JDateChooser date_chooser = new JDateChooser();JDateChooser datechooser = new JDateChooser();
        
        JButton search = new JButton("Search"); JButton delete = new JButton("Delete");
        JButton modify = new JButton("Modify");
        
        StockItems = new JPanel (null);
        StockItems.setBackground(Color.LIGHT_GRAY);

        TitledBorder titled = new TitledBorder("Control Items Stock");
        titled.setTitleJustification(TitledBorder.RIGHT);
        StockItems.setBorder(titled);
        
        searchHere.setBounds(150,50,200,30);
        StockItems.add(searchHere);
        search.setBounds(searchHere.getBounds().width+150,50,100,30);
        StockItems.add(search);
        
        JLabel stockPlaceName = new JLabel("Stock Place: ");
        stockPlaceName.setBounds(70,100,100,20);
        StockItems.add(stockPlaceName);
        stockplace.setBounds(stockPlaceName.getBounds().width+70,100,125,20);
        StockItems.add(stockplace);
        stockplace.setEditable(false);
        
        JLabel lb2 = new JLabel("Products Infromation: ");
        lb2.setBounds(55, 125, 200, 20);
        StockItems.add(lb2);
        
        JLabel pn = new JLabel("Product Name: ");
        pn.setBounds(75,150,100,20);
        StockItems.add(pn);
        productname.setBounds(pn.getBounds().width+75,150,100,20);
        StockItems.add(productname);
        
        JLabel bn = new JLabel("  Brand Name: ");
        bn.setBounds(300,150,100,20);
        StockItems.add(bn);
        brandname.setBounds(bn.getBounds().width+300,150,100,20);
        StockItems.add(brandname);
        
        category.setBounds(150,185,100,30);
        StockItems.add(category);
        
        types.setBounds(330,185,100,30);
        StockItems.add(types);
        
        JLabel expDate = new JLabel("Expiration Date: ");
        expDate.setBounds(75,225,100,20);
        StockItems.add(expDate);
        date_chooser.setBounds(expDate.getBounds().width+75,225,120,20);
        StockItems.add(date_chooser);
        
        JLabel mgDate = new JLabel("  Manufacture Date: ");
        mgDate.setBounds(300,225,115,20);
        StockItems.add(mgDate);
        datechooser.setBounds(mgDate.getBounds().width+300,225,120,20);
        StockItems.add(datechooser);
        
        delete.setBounds(170,500,100,35);
        StockItems.add(delete);
        
        modify.setBounds(280,500,100,35);
        StockItems.add(modify);
        
      Object columnNames[] = { "Column no","Column One", "Column Two","Column Three", "Column Four"};
      Object rowData[][] = { { "NO.","Product Name", "Quantity", "Product Prize", "Full Prize"}};
      JTable table = new JTable(rowData, columnNames);
      JScrollPane scrollPane = new JScrollPane(table);
      table.setFillsViewportHeight(false);
      StockItems.add(scrollPane);
      StockItems.add(table);
      table.setBounds(20,280,550,200);
      
        Cards.add(StockItems, "card5");
    } 
     // =====================SUPPLIERS==================== 
    public void initAddSupplier() {
        JTextField firstname = new JTextField(20);JTextField lastname = new JTextField(20);
        JTextField phonenumber = new JTextField(20);JTextField cityname = new JTextField(20);
        JTextField statename = new JTextField(20);JTextField address1 = new JTextField(20);
        JTextField address2 = new JTextField(20);JTextField blocknumber = new JTextField(20);
        JTextField companyname = new JTextField(20);JTextField companyemail = new JTextField(20);
        JTextField faxnumber = new JTextField(20);
        
        JButton ok = new JButton("Save");JButton no = new JButton("Clear");
        
        AddSupplier = new JPanel(null);
        AddSupplier.setBackground(Color.LIGHT_GRAY);
      
        TitledBorder titled = new TitledBorder("Add Supplier");
        titled.setTitleJustification(TitledBorder.RIGHT);
        AddSupplier.setBorder(titled);
      
        JLabel lb1 = new JLabel("Supplier Information: ");
        lb1.setBounds(40, 35, 200, 20);
        AddSupplier.add(lb1);
        
        JLabel first = new JLabel("First Name: ");
        first.setBounds(50,65,100,20);
        AddSupplier.add(first); 
        firstname.setBounds(first.getBounds().width+50,65,100,20);
        AddSupplier.add(firstname);
        
        JLabel second = new JLabel("Second Name: ");
        second.setBounds(300,65,100,20);
        AddSupplier.add(second);
        lastname.setBounds(second.getBounds().width+300,65,100,20);
        AddSupplier.add(lastname);
        
        JLabel company = new JLabel("Company Name: ");
        company.setBounds(50,95,100,20);
        AddSupplier.add(company); 
        companyname.setBounds(company.getBounds().width+50,95,100,20);
        AddSupplier.add(companyname);
        
        JLabel lb2 = new JLabel("Conatact Information: ");
        lb2.setBounds(40,125,200,20);
        AddSupplier.add(lb2);
        
        JLabel cemail = new JLabel("Company Email: ");
        cemail.setBounds(50,150,100,20);
        AddSupplier.add(cemail);
        companyemail.setBounds(cemail.getBounds().width+50,150,100,20);
        AddSupplier.add(companyemail);
        
        JLabel phone = new JLabel("Phone Number: ");
        phone.setBounds(300,150,100,20);
        AddSupplier.add(phone);
        phonenumber.setBounds(phone.getBounds().width+300,150,100,20);
        AddSupplier.add(phonenumber);
        
        JLabel faxno = new JLabel("Fax Number: ");
        faxno.setBounds(50,180,100,20);
        AddSupplier.add(faxno);
        faxnumber.setBounds(faxno.getBounds().width+50,180,100,20);
        AddSupplier.add(faxnumber);
        
        JLabel lb3 = new JLabel("Company Address Information: ");
        lb3.setBounds(40,210,200,20);
        AddSupplier.add(lb3);
        
        JLabel city = new JLabel("City Name: ");
        city.setBounds(50,240,100,20);
        AddSupplier.add(city);
        cityname.setBounds(city.getBounds().width+50,240,100,20);
        AddSupplier.add(cityname);
        
        JLabel state = new JLabel("State Name: ");
        state.setBounds(300,240,100,20);
        AddSupplier.add(state);
        statename.setBounds(state.getBounds().width+300,240,100,20);
        AddSupplier.add(statename);
        
        JLabel addressone = new JLabel("Address One: ");
        addressone.setBounds(50,270,100,20);
        AddSupplier.add(addressone);
        address1.setBounds(addressone.getBounds().width+50,270,100,20);
        AddSupplier.add(address1);
        
        JLabel addresstwo = new JLabel("Address Two: ");
        addresstwo.setBounds(300,270,100,20);
        AddSupplier.add(addresstwo);
        address2.setBounds(addresstwo.getBounds().width+300,270,100,20);
        AddSupplier.add(address2);
        
        JLabel blockno = new JLabel("Building Number: ");
        blockno.setBounds(50,300,100,20);
        AddSupplier.add(blockno);
        blocknumber.setBounds(blockno.getBounds().width+50,300,100,20);
        AddSupplier.add(blocknumber);
        
        ok.setBounds(350,400,100,35);
        AddSupplier.add(ok);
        
        no.setBounds(460,400,100,35);
        AddSupplier.add(no);
        
        Cards.add(AddSupplier, "card6");
    }
    private void initListSuppliers() {
        
        ListSuppliers = new JPanel(null);
        ListSuppliers.setBackground(Color.LIGHT_GRAY);
        
        String[] columns = {"ID", "FName", "LName", "Email",
                            "Phone", "City", "State","Street", "Block No.", "Address1", "Address2"};
 
        Object[][] data = {
            {"1", "Omar", "Atef", "Omar.Atef@gmail.com", "Phone", "Egypt", "Cairo","Street", "06", "Address1", "Address2"},
            {"2", "Habiba", "Hegazy", "habiba@gmail.com", "Phone", "Egypt", "Shrqya","Street", "10", "Address1", "Address2"},
            {"3", "Sarah", "Fouad", "souFouad@gmail.com", "Phone", "Egypt", "Cairo","Street", "08", "Address1", "Address2"}
        };
 
        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
 
        //JLabel lblHeading = new JLabel("   ");
        //lblHeading.setFont(new Font("Arial",Font.TRUETYPE_FONT,24));
 
        ListSuppliers.setLayout(new BorderLayout());
 
        ListSuppliers.add(scrollPane,BorderLayout.CENTER);
        
        Cards.add(ListSuppliers,"card7");
    }
    private void initControlSuppliers() {
        JTextField firstname = new JTextField(20);JTextField lastname = new JTextField(20);
        JTextField theemail = new JTextField(20);JTextField phonenumber = new JTextField(20);
        JTextField cityname = new JTextField(20);JTextField statename = new JTextField(20);
        JTextField address1 = new JTextField(20);JTextField address2 = new JTextField(20);
        JTextField blocknumber = new JTextField(20);JTextField searchHere = new JTextField(20);
        JTextField streetname = new JTextField(20);JTextField companyname = new JTextField(20);
        JTextField faxnumber = new JTextField(20);
        
        JButton search = new JButton("Search");JButton delete = new JButton("Delete");
        JButton modify = new JButton("Modify");
        
        ControlSuppliers = new JPanel(null);
        ControlSuppliers.setBackground(Color.LIGHT_GRAY);
      
        TitledBorder titled = new TitledBorder("Control Supplier");
        titled.setTitleJustification(TitledBorder.RIGHT);
        ControlSuppliers.setBorder(titled);
      
        searchHere.setBounds(150, 20, 200, 20);
        ControlSuppliers.add(searchHere);
        search.setBounds(searchHere.getBounds().width+150,20,100,20);
        ControlSuppliers.add(search);
        
        JLabel lb1 = new JLabel("Supplier Personal Information: ");
        lb1.setBounds(40, 50, 200, 20);
        ControlSuppliers.add(lb1);
        
        JLabel first = new JLabel("First Name: ");
        first.setBounds(50,80,100,20);
        ControlSuppliers.add(first);
        firstname.setBounds(first.getBounds().width+50,80,100,20);
        ControlSuppliers.add(firstname);
      
        JLabel second = new JLabel("Second Name: ");
        second.setBounds(300,80,100,20);
        ControlSuppliers.add(second);
        lastname.setBounds(second.getBounds().width+300,80,100,20);
        ControlSuppliers.add(lastname);
        
        JLabel lb2 = new JLabel("Company Conatact Information: ");
        lb2.setBounds(40,110,200,20);
        ControlSuppliers.add(lb2);
        
        JLabel cn = new JLabel("Company Name: ");
        cn.setBounds(50,140,100,20);
        ControlSuppliers.add(cn);
        companyname.setBounds(cn.getBounds().width+50,140,100,20);
        ControlSuppliers.add(companyname);
      
         JLabel email = new JLabel("Company Email: ");
        email.setBounds(300,140,100,20);
        ControlSuppliers.add(email);
         theemail.setBounds(email.getBounds().width+300,140,100,20);
        ControlSuppliers.add(theemail);
        
        JLabel phone = new JLabel("Phone Number: ");
        phone.setBounds(50,170,100,20);
        ControlSuppliers.add(phone);
        phonenumber.setBounds(phone.getBounds().width+50,170,100,20);
        ControlSuppliers.add(phonenumber);
      
        
        JLabel fax = new JLabel("Fax Number: ");
        fax.setBounds(300,170,100,20);
        ControlSuppliers.add(fax);
        faxnumber.setBounds(fax.getBounds().width+300,170,100,20);
        ControlSuppliers.add(faxnumber);
      
        JLabel lb3 = new JLabel("Company Address Information: ");
        lb3.setBounds(40,200,200,20);
        ControlSuppliers.add(lb3);
        
        JLabel city = new JLabel("City Name: ");
        city.setBounds(50,230,100,20);
        ControlSuppliers.add(city);
        cityname.setBounds(city.getBounds().width+50,230,100,20);
        ControlSuppliers.add(cityname);
        
        JLabel state = new JLabel("State Name: ");
        state.setBounds(300,230,100,20);
        ControlSuppliers.add(state);
        statename.setBounds(state.getBounds().width+300,230,100,20);
        ControlSuppliers.add(statename);
      
        JLabel street = new JLabel("Street Name: ");
        street.setBounds(50,260,100,20);
        ControlSuppliers.add(street);
        streetname.setBounds(street.getBounds().width+50,260,100,20);
        ControlSuppliers.add(streetname);
      
        JLabel blockno = new JLabel("Block Number: ");
        blockno.setBounds(300,260,100,20);
        ControlSuppliers.add(blockno);
        blocknumber.setBounds(blockno.getBounds().width+300,260,100,20);
        ControlSuppliers.add(blocknumber);
      
        JLabel addressone = new JLabel("Address One: ");
        addressone.setBounds(50,290,100,20);
        ControlSuppliers.add(addressone);
        address1.setBounds(addressone.getBounds().width+50,290,100,20);
        ControlSuppliers.add(address1);
        
        JLabel addresstwo = new JLabel("Address Two: ");
        addresstwo.setBounds(300,290,100,20);
        ControlSuppliers.add(addresstwo);
        address2.setBounds(addresstwo.getBounds().width+300,290,100,20);
        ControlSuppliers.add(address2);
        
        
        delete.setBounds(170,330,100,35);
        ControlSuppliers.add(delete);
        
        modify.setBounds(280,330,100,35);
        ControlSuppliers.add(modify);
        
        Cards.add(ControlSuppliers, "card8");
    }
    private void getFromSupplier() {
     JTextField productname = new JTextField(20); JTextField suppliername = new JTextField(20); 
     JTextField orderNum = new JTextField(20); JTextField shipping = new JTextField(20);
     JButton orderBtn = new JButton("Order"); JButton cartBtn = new JButton("Add Cart");
     
     orderSup = new JPanel(null);
     orderSup.setBackground(Color.LIGHT_GRAY);
     TitledBorder titled = new TitledBorder("Order Product");
     titled.setTitleJustification(TitledBorder.RIGHT);
     orderSup.setBorder(titled);
     
     JLabel OrderNumber = new JLabel("Order Number: ");
     OrderNumber.setBounds(50,30,150,25);
     orderSup.add(OrderNumber);
     orderNum.setBounds(OrderNumber.getBounds().width,30,100,25);
     orderNum.setEditable(false);
     orderSup.add(orderNum);
     
     JLabel shipNumber = new JLabel("Shipment Number: ");
     shipNumber.setBounds(280,30,150,25);
     orderSup.add(shipNumber);
     shipping.setBounds(shipNumber.getBounds().width+250,30,100,25);
     shipping.setEditable(false);
     orderSup.add(shipping);

     JLabel ProductName = new JLabel("Product Name: ");
     ProductName.setBounds(50,80,150,25);
     orderSup.add(ProductName);
     productname.setBounds(ProductName.getBounds().width-6,80,150,25);
     orderSup.add(productname);

     JLabel SupplierName = new JLabel("Supplier Name: ");
     SupplierName.setBounds(310,80,150,25);
     orderSup.add(SupplierName);
     suppliername.setBounds(SupplierName.getBounds().width+260,80,150,25);
     orderSup.add(suppliername);
 
     JLabel Brand = new JLabel("   Brand: ");
     Brand.setBounds(100,130,150,25);
     orderSup.add(Brand);
     theType.setBounds(Brand.getBounds().width+15,130,100,25);
     orderSup.add(theType);
      
     JLabel Quantity = new JLabel("   QTY: ");
     Quantity.setBounds(300,130,100,25);
     orderSup.add(Quantity);
     JSpinner quantity = new JSpinner(new SpinnerNumberModel(1, 1, 5000, 1));  
     quantity.setBounds(Quantity.getBounds().width+250,125,50,30);
     orderSup.add(quantity);
     
     cartBtn.setBounds(200,450,100,35);
     orderSup.add(cartBtn);
     
     orderBtn.setBounds(310,450,100,35);
     orderSup.add(orderBtn);
      
     Object columnNames[] = { "Column no","Column One", "Column Two","Column Three", "Column Four"};
     Object rowData[][] = { { "NO.","Product Name", "Quantity", "Product Prize", "Full Prize"}};
     JTable table = new JTable(rowData, columnNames);
     JScrollPane scrollPane = new JScrollPane(table);
     table.setFillsViewportHeight(false);
     orderSup.add(scrollPane);
     orderSup.add(table);
     table.setBounds(25,180,550,250);
     
     Cards.add(orderSup, "card9");
    } 
    private void returnToSupplier() {
     JTextField searchHere = new JTextField(20); JTextField productname = new JTextField(20); 
     JTextField suppliername = new JTextField(20); JTextField orderNum = new JTextField(20); 
     JTextField shipping = new JTextField(20);
     JButton returnbtn = new JButton("Return");
     
     returnSup = new JPanel(null);
     returnSup.setBackground(Color.LIGHT_GRAY);
     TitledBorder titled = new TitledBorder("Order Product");
     titled.setTitleJustification(TitledBorder.RIGHT);
     returnSup.setBorder(titled);
     
     JLabel searchBar = new JLabel("search here: ");
     searchBar.setBounds(200,30,150,25);
     returnSup.add(searchBar);
     searchHere.setBounds(200, 50, 200, 25); // start search on enter button
     returnSup.add(searchHere);
     
     JLabel ProductName = new JLabel("Product Name: ");
     ProductName.setBounds(30,140,150,25);
     returnSup.add(ProductName);
     productname.setBounds(120,140,150,25);
     productname.setEditable(false);
     returnSup.add(productname);

     JLabel Brand = new JLabel("   Brand: ");
     Brand.setBounds(40,190,150,25);
     returnSup.add(Brand);
     theType.setBounds(Brand.getBounds().width-50,190,70,25);
     theType.setEditable(false);
     returnSup.add(theType);
      
     JLabel Quantity = new JLabel("   QTY: ");
     Quantity.setBounds(180,190,100,25);
     returnSup.add(Quantity);
     JSpinner quantity = new JSpinner(new SpinnerNumberModel(1, 1, 10000, 1));  
     quantity.setBounds(Quantity.getBounds().width+120,190,50,30);
     returnSup.add(quantity);
     
     JLabel SupplierName = new JLabel("Supplier Name: ");
     SupplierName.setBounds(30,240,150,25);
     returnSup.add(SupplierName);
     suppliername.setBounds(120,240,125,25);
     suppliername.setEditable(false);
     returnSup.add(suppliername);
 
     JLabel OrderNumber = new JLabel("Order Number: ");
     OrderNumber.setBounds(30,290,150,25);
     returnSup.add(OrderNumber);
     orderNum.setBounds(120,290,125,25);
     orderNum.setEditable(false);
     returnSup.add(orderNum);

     JLabel ShipNumber = new JLabel("Shipment Number: ");
     ShipNumber.setBounds(30,340,150,25);
     returnSup.add(ShipNumber);
     shipping.setBounds(140,340,125,25);
     shipping.setEditable(false);
     returnSup.add(shipping);

     returnbtn.setBounds(135,400,100,35);
     returnSup.add(returnbtn);
     
    JTextArea returnReason = new JTextArea("Return Reason..");
    Border border = BorderFactory.createLineBorder(Color.BLACK);
    returnReason.setBounds(300,100,270,400);
    returnReason.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    returnSup.add(returnReason); 
     
     Cards.add(returnSup,"card10");
    } 
    // =====================ACCOUNTS======================
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
                case "Cashier":
                    x.username = accountuser.getText();
                    x.password = accountpass.getText();        
                    x.inputAccounts();
                    break;
                case "Customer Service":
                    customer.username = accountuser.getText();
                    customer.password = accountpass.getText();  
                    customer.inputAccounts();
                    break;
                default:
                    break;
            }
        });
        remove.addActionListener((ActionEvent ae) -> {
            switch (workertype) {
                case "Cashier":
                    x.username = accountuser.getText();
                    x.password = accountpass.getText();        
                    x.removeAccounts();
                    break;
                case "Customer Service":
                   customer.username = accountuser.getText();
                   customer.password = accountpass.getText();        
                   customer.removeAccounts();
                    break;
                default:
                    break;
            }
        });
        modify.addActionListener((ActionEvent ae) -> {
            switch (workertype) {
                case "Cashier":
                    x.username = accountuser.getText();
                    x.password = accountpass.getText();        
                    x.inputAccounts();
                    break;
                case "Customer Service":
                    customer.username = accountuser.getText();
                    customer.password = accountpass.getText();        
                    customer.inputAccounts();
                    break;
                default:
                    break;
            }
        });
        
        
        Cards.add(AccountsPanel,"card11");
    } 
    // =======================MARKET======================
    private void initMarket() {
      JTextField productname = new JTextField(20);JTextField brand = new JTextField(20); 
      JButton getFromStockBtn = new JButton("Get Product From STock");
      theMarket = new JPanel(null);
      theMarket.setBackground(Color.LIGHT_GRAY);
      TitledBorder titled = new TitledBorder("The Market");
      theMarket.setBorder(titled);
        
      JLabel ProductName = new JLabel("Product Name: ");
      ProductName.setBounds(100,30,150,25);
      theMarket.add(ProductName);
      productname.setBounds(ProductName.getBounds().width+50,30,150,25);
      theMarket.add(productname);
      
      JLabel Brand = new JLabel("   Brand: ");
      Brand.setBounds(50,80,100,25);
      theMarket.add(Brand);
      brand.setBounds(Brand.getBounds().width+15,80,100,25);
      theMarket.add(brand);
      
      JLabel Type = new JLabel("   Type: ");
      Type.setBounds(230,80,100,25);
      theMarket.add(Type);
      producttype.setBounds(Type.getBounds().width+180,80,70,25);
      theMarket.add(producttype);
      
      JLabel Quantity = new JLabel("   QTY: ");
      Quantity.setBounds(370,80,100,25);
      theMarket.add(Quantity);
      JSpinner quantity = new JSpinner(new SpinnerNumberModel(1, 1, 5000, 1));  
      quantity.setBounds(Quantity.getBounds().width+320,80,50,30);
      theMarket.add(quantity);
      
      getFromStockBtn.setBounds(100,400,200,30);
      theMarket.add(getFromStockBtn);
      
      Object columnNames[] = { "Column no","Column One", "Column Two","Column Three", "Column Four"};
      Object rowData[][] = { { "NO.","Product Name", "Quantity", "Product Prize", "Full Prize"}};
      JTable table = new JTable(rowData, columnNames);
      JScrollPane scrollPane = new JScrollPane(table);
      table.setFillsViewportHeight(false);
      theMarket.add(scrollPane);
      theMarket.add(table);
      table.setBounds(50,130,500,250);
      
      Cards.add(theMarket,"card12");
    }
    // =====================PRODUCTS======================
    private void IntoSystemProduct(){
     enterProducts = new JPanel(null);
     enterProducts.setBackground(Color.LIGHT_GRAY);
     add(enterProducts);
     
     JTextField productname = new JTextField(20);JTextField brand = new JTextField(20); 
     JTextField parchaseprize = new JTextField(20); JTextField saleprize = new JTextField(20); 
     JTextField taxamount = new JTextField(20); JTextField parchasenumber = new JTextField(20);
     JTextField salenumber = new JTextField(20); JTextField barcode = new JTextField(20);
     
     JDateChooser date_chooser = new JDateChooser();JDateChooser datechooser = new JDateChooser();
     
     JButton addBtn = new JButton("ADD"); JButton deleteBtn = new JButton("DELETE"); JButton modifyBtn = new JButton("EDIT");

      TitledBorder titled = new TitledBorder("Products.. ");
      titled.setTitleJustification(TitledBorder.RIGHT);
      enterProducts.setBorder(titled);
       
      JLabel ProductName = new JLabel("Product Name: ");
      ProductName.setBounds(100,30,150,25);
      enterProducts.add(ProductName);
      productname.setBounds(ProductName.getBounds().width+50,30,150,25);
      enterProducts.add(productname);
      
      JLabel Brand = new JLabel("   Brand: ");
      Brand.setBounds(50,80,100,25);
      enterProducts.add(Brand);
      brand.setBounds(Brand.getBounds().width+15,80,100,25);
      enterProducts.add(brand);
      
      JLabel Type = new JLabel("   Type: ");
      Type.setBounds(230,80,100,25);
      enterProducts.add(Type);
      producttypes.setBounds(Type.getBounds().width+180,80,70,25);
      enterProducts.add(producttypes);
      
      JLabel Quantity = new JLabel("   QTY: ");
      Quantity.setBounds(370,80,100,25);
      enterProducts.add(Quantity);
      JSpinner quantity = new JSpinner(new SpinnerNumberModel(1, 1, 5000, 1));  
      quantity.setBounds(Quantity.getBounds().width+320,80,50,30);
      enterProducts.add(quantity);
      
      JLabel expDate = new JLabel("Expiration Date: ");
      expDate.setBounds(75,130,100,20);
      enterProducts.add(expDate);
      date_chooser.setBounds(expDate.getBounds().width+75,130,120,20);
      enterProducts.add(date_chooser);
        
      JLabel mgDate = new JLabel("  Manufacture Date: ");
      mgDate.setBounds(300,130,115,20);
      enterProducts.add(mgDate);
      datechooser.setBounds(mgDate.getBounds().width+300,130,120,20);
      enterProducts.add(datechooser);
      
      JLabel parchasePrize = new JLabel("Parchase Prize: ");
      parchasePrize.setBounds(50,170,120,25);
      enterProducts.add(parchasePrize);
      parchaseprize.setBounds(parchasePrize.getBounds().width+25,170,100,25);
      enterProducts.add(parchaseprize);
      
      JLabel parchaseNumber = new JLabel("Parchase Number: ");
      parchaseNumber.setBounds(300,170,120,25);
      enterProducts.add(parchaseNumber);
      parchasenumber.setBounds(parchaseNumber.getBounds().width+290,170,120,25);
      enterProducts.add(parchasenumber);
      
      JLabel SalePrize = new JLabel("Sale Prize: ");
      SalePrize.setBounds(75,210,100,25);
      enterProducts.add(SalePrize);
      saleprize.setBounds(SalePrize.getBounds().width+50,210,100,25);
      enterProducts.add(saleprize);
      
      JLabel SaleNumber = new JLabel("Sale Number: ");
      SaleNumber.setBounds(290,210,100,25);
      enterProducts.add(SaleNumber);
      salenumber.setBounds(SaleNumber.getBounds().width+280,210,100,25);
      enterProducts.add(salenumber);

      JLabel taxAmount = new JLabel("Tax Amount: ");
      taxAmount.setBounds(50,250,100,25);
      enterProducts.add(taxAmount);
      taxamount.setBounds(taxAmount.getBounds().width+25,250,70,25);
      enterProducts.add(taxamount);
      
      JLabel warrenty = new JLabel("Warrenty: ");
      warrenty.setBounds(230,250,100,25);
      enterProducts.add(warrenty);
      warrentyOption.setBounds(warrenty.getBounds().width+200,250,70,25);
      enterProducts.add(warrentyOption);
      
      JLabel Barcode = new JLabel("Barcode: ");
      Barcode.setBounds(400,250,100,25);
      enterProducts.add(Barcode);
      barcode.setBounds(Barcode.getBounds().width+360,250,90,25);
      enterProducts.add(barcode);
      
     addBtn.addActionListener((ActionEvent ae) -> {
          Products.addProduct(date_chooser.getDate(),datechooser.getDate(), (String)producttypes.getSelectedItem(), brand.getText(), productname.getText(), 
             (int)quantity.getValue(), barcode.getText(), (String)warrentyOption.getSelectedItem(), parchaseprize.getText(), saleprize.getText(), 
             parchasenumber.getText(), salenumber.getText(),purchase, sale, warr);
      });
     deleteBtn.addActionListener((ActionEvent ae) -> {
          if (Products.deleteProduct(productname.getText()))
                   {
                       date_chooser.setDate(null);datechooser.setDate(null);
                       brand.setText("");productname.setText("");
                       quantity.setValue(1);barcode.setText("");
                       parchaseprize.setText("");saleprize.setText("");
                       parchasenumber.setText("");salenumber.setText("");
                   }
                   else{
                       JOptionPane.showMessageDialog(null, "Cant Delete product name : "+ productname.getText(), "Alert", JOptionPane.WARNING_MESSAGE);
                   }
      });
      
      addBtn.setBounds(150,340,100,25);
      enterProducts.add(addBtn);
      deleteBtn.setBounds(250,340,100,25);
      enterProducts.add(deleteBtn);
//      modifyBtn.setBounds(350,340,100,25);
//      enterProducts.add(modifyBtn);
      
      Cards.add(enterProducts,"card12");
    }
    private void ListProduct(){
      listProducts = new JPanel(null);
      listProducts.setBackground(Color.LIGHT_GRAY);
      TitledBorder titled = new TitledBorder("The Market");
      listProducts.setBorder(titled);
      
      String[] columns = {"Product Name", "brand", "QTY", "parchuse $","Sale $", "Barcode"};
        
        DefaultTableModel tablemodel = new DefaultTableModel(columns, 0);
         ArrayList<Product> pro = Products.list();
                for (Product c : pro) {
                    String prodName = c.GetProductname();
                    String brand = c.getBrand();
                    String type = c.getTtpe();
                    int qty = c.getqty();
                    String parchuse = c.GetparchusePrize();
                    String sale = c.GetsalePrize();
                    String barcode = c.GetBracode();
                    Object[] obj = {prodName,brand,type,qty,parchuse,sale,barcode};
                    
                    tablemodel.addRow(obj);
                }
                
        JTable table = new JTable(tablemodel);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        
        listProducts.setLayout(new BorderLayout());

        listProducts.add(scrollPane,BorderLayout.CENTER);
        
        Cards.add(listProducts,"card2");          
    }
    //======================NetWorkChat===================
    private void initServer(){
        Thread thread = new Thread(s);
        thread.start();
    }
}