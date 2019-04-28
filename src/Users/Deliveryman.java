package Users;

import ExceptionClasses.NotNumber;
import ExceptionClasses.NotString;
import static inventorysytem.InventorySytem.dm;
import java.io.Serializable;
import java.util.ArrayList;

public class Deliveryman extends UserInformation implements IUser<Deliveryman>,Serializable{
    private String licenseType;
    String workType;
   
    @Override
    public void add(String first, String last, String Phone, String Email, String AddCity, String State,
            String addressone, String addresstwo, String street, String blockNumber) {
       Deliveryman deliveryman = new Deliveryman();
       deliveryman.generateID("DELIVERY", first);
       try{
           deliveryman.con.setFirstname(first);
           deliveryman.con.setLastname(last);
           deliveryman.con.setPhonenumber(Phone);
           deliveryman.con.setEmail(Email);
           deliveryman.address.setCityname(AddCity);
           deliveryman.address.setStatename(State);
           deliveryman.address.setAddressone(addressone);
           deliveryman.address.setAddresstwo(addresstwo);
           deliveryman.address.setStreetnumber(street);
           deliveryman.address.setBlockNumber(blockNumber);
       }catch (NotString | NotNumber e){
           return;
       }
       dm.add(deliveryman);
    }

    @Override
    public boolean del(String id) {
       Deliveryman deliveryman = search(id);
       return dm.remove(deliveryman);
    }

    @Override
     public void modify(String id, String New, String Type)
    {
        for (int i = 0; i < dm.size(); i++) {
            if (dm.get(i).GetID().equals(id)) {
                try{
                    switch (Type)
                {
                    case "FirstName":
                        dm.get(i).con.setFirstname(New);
                        break;
                    case "LastName":
                        dm.get(i).con.setLastname(New);
                        break;
                    case "Email":
                        dm.get(i).con.setEmail(New);
                        break;
                    case "FaxNumber":
                        dm.get(i).con.setFaxNumber(New);
                        break;
                    case "PhoneNumber":
                        dm.get(i).con.setPhonenumber(New);
                        break;
                    case "City":
                        dm.get(i).address.setCityname(New);
                        break;
                    case "AddressOne":
                        dm.get(i).address.setAddressone(New);
                        break;
                    case "AddressTwo":
                        dm.get(i).address.setAddresstwo(New);
                        break;
                    case "State":
                        dm.get(i).address.setStatename(New);
                        break;
                    case "Street":
                        dm.get(i).address.setStreetnumber(New);
                        break;
                    case "BlockNumber":
                        dm.get(i).address.setBlockNumber(New);
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
    public Deliveryman search(String id) 
    {
        Deliveryman deliveryman = new Deliveryman();
        for (Deliveryman Ca : dm)
        {
            if (Ca.GetID().equals(id))
            {
                deliveryman = Ca;
            }
        }
        return deliveryman;
    }

    //not used
    @Override
    public ArrayList list() 
    {        
        return dm;
    }
}
