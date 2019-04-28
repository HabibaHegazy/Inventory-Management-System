package Users;

import ExceptionClasses.NotNumber;
import ExceptionClasses.NotString;
import static inventorysytem.InventorySytem.workers;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Workers extends UserInformation implements IUser<Workers>,Serializable
{
    String workType;
    
    @Override
    public void add(String first, String last, String Phone, String Email, String AddCity, String State, String addressone, String addresstwo, String street, String blockNumber) 
    {
       Workers worker = new Workers();
       worker.generateID("WORKERS", first);
       try{
           worker.con.setFirstname(first);
           worker.con.setLastname(last);
           worker.con.setPhonenumber(Phone);
           worker.con.setEmail(Email);
           worker.address.setCityname(AddCity);
           worker.address.setStatename(State);
           worker.address.setAddressone(addressone);
           worker.address.setAddresstwo(addresstwo);
           worker.address.setStreetnumber(street);
           worker.address.setBlockNumber(blockNumber);
       }catch (NotString e){
           JOptionPane.showMessageDialog(null, "You entered something else than words (number for example).", "Alert", JOptionPane.WARNING_MESSAGE);
           return;
       }catch (NotNumber ex){
           JOptionPane.showMessageDialog(null, "You entered words in number filed", "Alert", JOptionPane.WARNING_MESSAGE);
           return;
       }
       workers.add(worker);
    }

    public boolean del(String id) 
    {
        Workers worker = search(id);
        return workers.remove(worker);
    }

     public void modify(String id, String New, String Type)
    {
        for (int i = 0; i < workers.size(); i++) {
            if (workers.get(i).GetID().equals(id)) {
                try{
                    switch (Type)
                {
                    case "FirstName":
                        workers.get(i).con.setFirstname(New);
                        break;
                    case "LastName":
                        workers.get(i).con.setLastname(New);
                        break;
                    case "Email":
                        workers.get(i).con.setEmail(New);
                        break;
                    case "FaxNumber":
                        workers.get(i).con.setFaxNumber(New);
                        break;
                    case "PhoneNumber":
                        workers.get(i).con.setPhonenumber(New);
                        break;
                    case "City":
                        workers.get(i).address.setCityname(New);
                        break;
                    case "AddressOne":
                        workers.get(i).address.setAddressone(New);
                        break;
                    case "AddressTwo":
                        workers.get(i).address.setAddresstwo(New);
                        break;
                    case "State":
                        workers.get(i).address.setStatename(New);
                        break;
                    case "Street":
                        workers.get(i).address.setStreetnumber(New);
                        break;
                    case "BlockNumber":
                        workers.get(i).address.setBlockNumber(New);
                        break;
                    default:
                        break;
                }
                }catch (NotString e){
                    JOptionPane.showMessageDialog(null, "You entered something else than words (number for example).", "Alert", JOptionPane.WARNING_MESSAGE);
                    return;
                }catch (NotNumber ex){
                    JOptionPane.showMessageDialog(null, "You entered words in number filed", "Alert", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        }
    }
    
    public Workers search(String id) 
    {
        Workers wo = new Workers();
        for (Workers w : workers)
        {
            if (w.GetID().equals(id))
            {
                wo = w;
            }
        }
        return wo;
    }

    public ArrayList list() 
    {
        ArrayList<Workers> W = new ArrayList<>();
        /*try {
            WorkerIn = new FileInputStream("WorkersData\\Workers.bin");
            if (WorkerIn.available() > 0)
            {
                Inp = new ObjectInputStream(WorkerIn);
                W = (ArrayList<Workers>)Inp.readObject();
                Inp.close();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Workers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Workers.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        return W;
    }
}
