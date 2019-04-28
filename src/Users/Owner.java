
package Users;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Owner implements ILogin{
    void controlAdmin(Admin admin)
    {}
    void getrReports(Reports reports)
    {}

   public String username,password;
    HashMap<String,String> accounts = new HashMap<>();
    @Override
    public void saveData() throws FileNotFoundException, IOException{
        try {
             FileOutputStream fileOutput = new FileOutputStream("OwnerAccounts.ser");
             ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
             objectOutput.writeObject(accounts);
             objectOutput.close();
             fileOutput.close();
             System.out.printf("Serialized HashMap data is saved in hashmap.ser");
        }catch(IOException ioe) {
            ioe.printStackTrace();
     }
 }
    
    @Override
    public void getData() throws FileNotFoundException, IOException, ClassNotFoundException {
        accounts = null;
        try {
         FileInputStream fileInput = new FileInputStream("OwnerAccounts.ser");
         ObjectInputStream objectInput = new ObjectInputStream(fileInput);
         accounts = (HashMap) objectInput.readObject();
         objectInput.close();
         fileInput.close();
      }catch(IOException ioe) {
         ioe.printStackTrace();
         return;
      }catch(ClassNotFoundException c) {
         System.out.println("Class not found");
         c.printStackTrace();
         return;
      }
      System.out.println("Deserialized HashMap..");
    }
    
    @Override
    public void inputAccounts(){
        accounts.put(username,password);
    }
    
    @Override
    public boolean LoginLn(String user, String pass) {
      Set set = accounts.entrySet();
      Iterator iterator = set.iterator();
      while(iterator.hasNext()) {
          Map.Entry loop = (Map.Entry)iterator.next();
          String username = (String) loop.getKey();
          String password = (String) loop.getValue();
          if (user.equals(username) && pass.equals(password)) {
              //System.out.println("found");
              return true;
          } //else 
              //System.out.println("not found");
      }        
      return false;
    }

    @Override
    public void removeAccounts() {
        accounts.remove(username);
    }

    @Override
    public String searchAccount() {
      return accounts.get(username); // error when user is not found
    }
    
    @Override
    public boolean forgetPassword(String user,String pass,String newPass){
        if(accounts.containsKey(user) && accounts.containsValue(pass)) {
            username = user;
            password = newPass;
            inputAccounts();
            return true;
        }
        return false;
    }
}
