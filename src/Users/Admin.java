package Users;

import ExceptionClasses.NotNumber;
import ExceptionClasses.NotString;
import Orders.Stock;
import Orders.Product;
import Orders.Category;
import Orders.ReturnToSupplier;
import static inventorysytem.InventorySytem.Admins;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Admin extends UserInformation implements ILogin,IUser<Admin>,Serializable{
    Employee e;
    public String username,password;
    HashMap<String,String> accounts = new HashMap<>();
    
    public void makeAdmin(UserInformation userInfo) 
    {}
    public void orders(Stock stock) 
    {}
    public void product(Product p)
    {}
    public void categories(Category category)
    {}
    public void controlCahsier() 
    {}
    public void controlDM() 
    {}
    public void controlSuppliers()
    {}
    public void controlWorkers()
    {}
    public void returnItem(ReturnToSupplier rts)
    {}
    
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
    public void inputAccounts(){
        accounts.put(username,password);
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
    public void saveData() throws FileNotFoundException, IOException{
        try {
             FileOutputStream fileOutput = new FileOutputStream("AdminAccounts.ser");
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
         FileInputStream fileInput = new FileInputStream("AdminAccounts.ser");
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
    public void add(String first, String last, String Phone, String Email, String AddCity, String State, String addressone, String addresstwo, String street, String blockNumber) {
        Admin admin = new Admin();
       admin.generateID("ADMIN", first);
       try{
           admin.con.setFirstname(first);
           admin.con.setLastname(last);
           admin.con.setPhonenumber(Phone);
           admin.con.setEmail(Email);
           admin.address.setCityname(AddCity);
           admin.address.setStatename(State);
           admin.address.setAddressone(addressone);
           admin.address.setAddresstwo(addresstwo);
           admin.address.setStreetnumber(street);
           admin.address.setBlockNumber(blockNumber);
       }catch (NotString | NotNumber e){
           return;
       }
       Admins.add(admin);
    }

    @Override
    public boolean del(String id) {
        Admin worker = search(id);
       return Admins.remove(worker);    
    }

    @Override
    public void modify(String id, String New, String Type) {
        for (int i = 0; i < Admins.size(); i++) {
            if (Admins.get(i).GetID().equals(id)) {
                try{
                    switch (Type)
                {
                    case "FirstName":
                        Admins.get(i).con.setFirstname(New);
                        break;
                    case "LastName":
                        Admins.get(i).con.setLastname(New);
                        break;
                    case "Email":
                        Admins.get(i).con.setEmail(New);
                        break;
                    case "PhoneNumber":
                        Admins.get(i).con.setPhonenumber(New);
                        break;
                    case "City":
                        Admins.get(i).address.setCityname(New);
                        break;
                    case "State":
                        Admins.get(i).address.setStatename(New);
                        break;
                    case "AddressOne":
                        Admins.get(i).address.setAddressone(New);
                        break;
                    case "AddressTwo":
                        Admins.get(i).address.setAddresstwo(New);
                        break;
                    case "Street":
                        Admins.get(i).address.setStreetnumber(New);
                        break;
                    case "BlockNumber":
                        Admins.get(i).address.setBlockNumber(New);
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
    public Admin search(String id) {
        Admin c = new Admin();
        for (Admin Ca : Admins)
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
        return Admins;
    }
}
