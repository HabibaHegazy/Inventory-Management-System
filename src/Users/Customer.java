
package Users;

import java.util.ArrayList;

public class Customer extends UserInformation implements IUser{
    private String TypeIC;

    @Override
    public void add(String first, String last, String Phone, String Email, String AddCity, String State,
            String addressone,String addresstwo, String street, String blockNumber)  {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean del(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
     public void modify(String id, String New, String Type)
    {
        
    }

    @Override
    public Object search(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
