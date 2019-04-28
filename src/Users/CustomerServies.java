package Users;

import ExceptionClasses.NotNumber;
import ExceptionClasses.NotString;
import static inventorysytem.InventorySytem.cs;
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

public class CustomerServies extends UserInformation implements IUser<Object>,ILogin,Serializable{
    
    HashMap<String,String> accounts = new HashMap<>();
    public String username,password; 
   
     @Override
    public void saveData() throws FileNotFoundException, IOException{
        try {
             FileOutputStream fileOutput = new FileOutputStream("CSAccounts.ser");
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
         FileInputStream fileInput = new FileInputStream("CSAccounts.ser");
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
    public String searchAccount() { // return pass only 
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

    public void Offers(Offers offers)
    {}
    public void makeReport(Reports reports)
    {}
    public void LoginIn()
    {}
    public void LogOut()
    {}
    @Override
   public void add(String first, String last, String Phone, String Email, String AddCity, String State,
           String addressone, String addresstwo, String street, String blockNumber) 
    {
       CustomerServies customer = new CustomerServies();
       customer.generateID("CUSTOMER", first);
       try{
           customer.con.setFirstname(first);
           customer.con.setLastname(last);
           customer.con.setPhonenumber(Phone);
           customer.con.setEmail(Email);
           customer.address.setCityname(AddCity);
           customer.address.setStatename(State);
           customer.address.setAddressone(addressone);
           customer.address.setAddresstwo(addresstwo);
           customer.address.setStreetnumber(street);
           customer.address.setBlockNumber(blockNumber);
       }catch (NotString | NotNumber e){
           return;
       }
       cs.add(customer);
    }

    @Override
    public boolean del(String id) 
    {
       CustomerServies customer = search (id);
       return cs.remove(customer);
    }

    @Override
    public void modify(String id, String New, String Type)
    {
        for (int i = 0; i < cs.size(); i++) {
            if (cs.get(i).GetID().equals(id)) {
                try{
                    switch (Type)
                {
                    case "FirstName":
                        cs.get(i).con.setFirstname(New);
                        break;
                    case "LastName":
                        cs.get(i).con.setLastname(New);
                        break;
                    case "Email":
                        cs.get(i).con.setEmail(New);
                        break;
                    case "FaxNumber":
                        cs.get(i).con.setFaxNumber(New);
                        break;
                    case "PhoneNumber":
                        cs.get(i).con.setPhonenumber(New);
                        break;
                    case "City":
                        cs.get(i).address.setCityname(New);
                        break;
                    case "AddressOne":
                        cs.get(i).address.setAddressone(New);
                        break;
                    case "AddressTwo":
                        cs.get(i).address.setAddresstwo(New);
                        break;
                    case "State":
                        cs.get(i).address.setStatename(New);
                        break;
                    case "Street":
                        cs.get(i).address.setStreetnumber(New);
                        break;
                    case "BlockNumber":
                        cs.get(i).address.setBlockNumber(New);
                        break;
                    default:
                        return;
                }
                }catch (NotString | NotNumber e){
                    return;
                }
            }
        }
    }    
    
    @Override
    public CustomerServies search(String id) 
    {
        CustomerServies c = new CustomerServies();
        for (CustomerServies Ca : cs)
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
    public ArrayList list() 
    {
        return cs;
    }
}
