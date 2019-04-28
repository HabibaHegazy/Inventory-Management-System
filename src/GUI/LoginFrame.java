
package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import Users.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginFrame extends JDialog{
    final JFrame frame = new JFrame("Login");
    private JTextField tfUsername = new JTextField(20);
    private final JPasswordField pfPassword = new JPasswordField(20);
    Casher cashier = new Casher(); 
    CustomerServies cs = new CustomerServies();
    Admin admin = new Admin();
    Owner owner = new Owner();
    String text;
    
    public LoginFrame() {
        Frame();
    }
 
    private void Frame() {
        this.addWindowListener(new WindowAdapter() {
            
            @Override
            public void windowClosing(WindowEvent e) {
            }
            
            @Override
            public void windowOpened(WindowEvent e) {
                try {
                    cashier.getData();
                    cs.getData();
                    admin.getData();
                    owner.getData();
                } catch (IOException ex) {
                    Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        JLabel lbUsername = new JLabel("Username: ");
        JLabel lbPassword = new JLabel("Password: ");
        JButton btnLogin = new JButton("Login");
        JButton btnCancel = new JButton("Cancel");
        JButton btnForget = new JButton("Forget Password");
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        
        // username label
        panel.add(lbUsername, constraints);
 
        // username textFiled
        panel.add(tfUsername, constraints);
 
        // password label
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(lbPassword, constraints);
 
        // password textFiled
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(pfPassword, constraints);
        panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
 
        // Login btn
            btnLogin.addActionListener((ActionEvent e) -> {
                 if (cashier.LoginLn(getUsername(), getPassword())) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Hi " + getUsername() + "! You have successfully logged in.", 
                            "Login",JOptionPane.INFORMATION_MESSAGE);
                    EventQueue.invokeLater(() -> {
                        ChashierFrame cashier = new ChashierFrame();
                        cashier.setVisible(true);
                        });
                    dispose();
                    } else if (admin.LoginLn(getUsername(), getPassword())) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Hi " + getUsername() + "! You have successfully logged in.", 
                            "Login", JOptionPane.INFORMATION_MESSAGE);
                    EventQueue.invokeLater(() -> {
                        AdminFrame admin = new AdminFrame();
                        admin.setVisible(true);
                        });
                    dispose();
                    } else if (owner.LoginLn(getUsername(), getPassword())) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Hi " + getUsername() + "! You have successfully logged in.", 
                            "Login", JOptionPane.INFORMATION_MESSAGE);
                    EventQueue.invokeLater(() -> {
                        OwnerFrame owner = new OwnerFrame();
                        owner.setVisible(true);
                        });
                    dispose();
                    } else if (cs.LoginLn(getUsername(), getPassword())) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Hi " + getUsername() + "! You have successfully logged in.", 
                            "Login",JOptionPane.INFORMATION_MESSAGE);
                    EventQueue.invokeLater(() -> {
                        CustomerServiceFrame custmerSerivce = new CustomerServiceFrame();
                        custmerSerivce.setVisible(true);
                        });
                    dispose();
                    } else {
                            JOptionPane.showMessageDialog(LoginFrame.this, "Invalid username or password", 
                                    "Login", JOptionPane.ERROR_MESSAGE);
                            tfUsername.setText("");
                            pfPassword.setText("");
                        }
                });
            
        // cancle btn
        btnCancel.addActionListener((ActionEvent e) -> {
            dispose();
        });
        
        // forget btn
        btnForget.addActionListener((ActionEvent e) -> {
            if (tfUsername.getText().equals("")) {
                JOptionPane.showMessageDialog(LoginFrame.this, "Enter your Username in the Username Filed", 
                        "Forget Password", JOptionPane.ERROR_MESSAGE);
            } else {
                text = JOptionPane.showInputDialog(frame, "Create Password");
                text = text.trim();
                if(text.equals("") || text.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {                
                    if(cashier.forgetPassword(getUsername(), getPassword(), text))
                        JOptionPane.showMessageDialog(LoginFrame.this, "Password was Changed Successfully", "Succeeded", JOptionPane.ERROR_MESSAGE);
                     else if (cs.forgetPassword(getUsername(), getPassword(), text))
                        JOptionPane.showMessageDialog(LoginFrame.this, "Password was Changed Successfully", "Succeeded", JOptionPane.ERROR_MESSAGE);
                     else 
                        JOptionPane.showMessageDialog(LoginFrame.this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(btnLogin);
        buttonsPanel.add(btnCancel);
        buttonsPanel.add(btnForget);
 
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonsPanel, BorderLayout.PAGE_END);
 
        pack();
        setResizable(false);
        setLocationRelativeTo(frame);
        getRootPane().setDefaultButton(btnLogin);
    }
    
    private String getUsername() {
        return tfUsername.getText().trim();
    }
 
    private String getPassword() {
        return new String(pfPassword.getPassword());
    }
}
