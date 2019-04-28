package Users;

import ExceptionClasses.NotNumber;
import ExceptionClasses.NotString;
import Orders.OrderCustomer;
import static inventorysytem.InventorySytem.cashiers;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Casher extends UserInformation implements ILogin,IUser<Casher>{

    public String username,password;
    HashMap<String,String> accounts = new HashMap<>();
    @Override
    public void saveData() throws FileNotFoundException, IOException{
        try {
             FileOutputStream fileOutput = new FileOutputStream("CashierAccounts.ser");
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
         FileInputStream fileInput = new FileInputStream("CashierAccounts.ser");
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
    
    @Override
    public void add(String first, String last, String Phone, String Email, String AddCity,
            String State, String addressone, String addresstwo,String street, String blockNumber) {
        
       Casher cahsier = new Casher();
       cahsier.generateID("CASHER", first);
       try{
           cahsier.con.setFirstname(first);
           cahsier.con.setLastname(last);
           cahsier.con.setPhonenumber(Phone);
           cahsier.con.setEmail(Email);
           cahsier.address.setCityname(AddCity);
           cahsier.address.setStatename(State);
           cahsier.address.setAddressone(addressone);
           cahsier.address.setAddresstwo(addresstwo);
           cahsier.address.setStreetnumber(street);
           cahsier.address.setBlockNumber(blockNumber);
       }catch (NotString | NotNumber e){
           return;
       }
       cashiers.add(cahsier);
    }

    @Override
    public boolean del(String id) {
       Casher worker = search(id);
       return cashiers.remove(worker);    
    }

    @Override
    public void modify(String id, String New, String Type) {
        for (int i = 0; i < cashiers.size(); i++) {
            if (cashiers.get(i).GetID().equals(id)) {
                try{
                    switch (Type)
                {
                    case "FirstName":
                        cashiers.get(i).con.setFirstname(New);
                        break;
                    case "LastName":
                        cashiers.get(i).con.setLastname(New);
                        break;
                    case "Email":
                        cashiers.get(i).con.setEmail(New);
                        break;
                    case "PhoneNumber":
                        cashiers.get(i).con.setPhonenumber(New);
                        break;
                    case "City":
                        cashiers.get(i).address.setCityname(New);
                        break;
                    case "State":
                        cashiers.get(i).address.setStatename(New);
                        break;
                    case "AddressOne":
                        cashiers.get(i).address.setAddressone(New);
                        break;
                    case "AddressTwo":
                        cashiers.get(i).address.setAddresstwo(New);
                        break;
                    case "Street":
                        cashiers.get(i).address.setStreetnumber(New);
                        break;
                    case "BlockNumber":
                        cashiers.get(i).address.setBlockNumber(New);
                        break;
                    default:
                        break;
                }
                }catch (NotString | NotNumber e){
                    return;
                }
            }
        }
    }
    
    @Override
    public Casher search(String id) {
        Casher c = new Casher();
        for (Casher Ca : cashiers)
        {
            if (Ca.GetID().equals(id))
            {
                c = Ca;
            }
        }
        return c;
    }
    
    //not used
    @Override
    public ArrayList list() {
        return cashiers;
    }
        
    public void makesOrder(OrderCustomer orderCustomer) {
    }
}
