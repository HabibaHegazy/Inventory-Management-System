package NetworkChat;

import static inventorysytem.InventorySytem.OneMessage;
import static inventorysytem.InventorySytem.allMessage;
import static inventorysytem.InventorySytem.sendMsg;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;


public class Server implements Runnable{
    
    private ServerSocket Mysocket;
    private Socket clientsocket;
    private ArrayList<Socket> Allconnections=new ArrayList<Socket>();
    
    public Server() throws IOException
    {
        Mysocket=new ServerSocket(6000);
        allMessage.setBounds(20, 20, 300, 100);
        OneMessage.setBounds(allMessage.getBounds().x,allMessage.getBounds().y+allMessage.getHeight()+10,150,50);
        sendMsg.setBounds(OneMessage.getBounds().x,OneMessage.getBounds().y+OneMessage.getHeight()+10,150,50);
        sendMsg.addActionListener(new act());
        
    }
    
    @Override
    public void run() 
    {
        try
        {
        while (true)
        {
        clientsocket=Mysocket.accept();
        Allconnections.add(clientsocket);
        ConnectionThread ct=new ConnectionThread(clientsocket,allMessage);
        Thread t1=new Thread(ct);
        t1.start();
        Thread.sleep(1000);
        }
        }
        catch (InterruptedException e)
        {}
        catch (IOException e)
        {}
    }
    
    private class act implements  ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                
                if (e.getSource().equals(sendMsg))
                {
                start(OneMessage.getText());
                allMessage.setText(allMessage.getText()+"<br>"+OneMessage.getText());
                OneMessage.setText(null);
                }
                
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        
    }
    
    public void start(String msg) throws IOException
    {
        for (Socket s: Allconnections)
        {
        OutputStream os=s.getOutputStream();
        PrintWriter pw=new PrintWriter(os,true);
        pw.println(msg);
        }
                
    }
    
    private class ConnectionThread implements Runnable{
    
    private Socket s;
    private InputStream is;
    private OutputStream os;
    private BufferedReader fromclient;
    private  JLabel Message;
    
    public ConnectionThread(Socket s,JLabel Msg) throws IOException
    {
        this.s=s;
        this.Message=Msg;
        is=s.getInputStream();
        os=s.getOutputStream();
    }
    
    public void run() {
           fromclient=new BufferedReader(new InputStreamReader(is));
        while (true)
        {
               try {
                   String NewMessage=fromclient.readLine();
                   Message.setText(Message.getText() +"<br>"+NewMessage);
                   Thread.sleep(100);
               } catch (IOException ex) {
                   Logger.getLogger(ConnectionThread.class.getName()).log(Level.SEVERE, null, ex);
               } catch (InterruptedException ex) {
                   Logger.getLogger(ConnectionThread.class.getName()).log(Level.SEVERE, null, ex);
               }
        }
    }
    }
}