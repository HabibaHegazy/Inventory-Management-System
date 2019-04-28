package NetworkChat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Client extends JFrame{
    
    private JLabel recivedMsg;
    private JTextField SentMsg;
    InputStream is;
    OutputStream os;
    Socket toserver = null;
    
    public Client() throws IOException
    {
        setSize(350,350);
        setTitle("Client Chat");
        recivedMsg=new JLabel("<html>Recived Message");
        SentMsg=new JTextField("Write message to server here");
        setLayout(null);
        recivedMsg.setBounds(20,20, 200,200);
        add(recivedMsg);
        JButton btnReciveMsg=new JButton("Send");
        btnReciveMsg.setBounds(220,250,100,50);
        SentMsg.setBounds(10,250,200,50);
        add(SentMsg);
        add(btnReciveMsg);
        btnReciveMsg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                PrintWriter pw=new PrintWriter(os,true);
                pw.println("Client : "+SentMsg.getText());
                SentMsg.setText("");
            }
        });
        try {
            toserver = new Socket("127.0.0.1", 6000);
            is=toserver.getInputStream();
            os=toserver.getOutputStream();
            updategui t=new updategui();
            t.start();
        } catch (IOException ex) {
        }    
    }
    
    private class updategui extends Thread
    {
        public void run()
        {
            while (true)
            {
                
                BufferedReader bf=new BufferedReader(new InputStreamReader(is));
                String Message = null;
                try {
                    Message = bf.readLine();
                    if (Message!=null)
                    {
                    recivedMsg.setText(recivedMsg.getText()+"<br>"+Message);                    
                    }
                } catch (IOException ex) {
                    System.out.println(ex);
                }
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                }
            }
        }
    }
}