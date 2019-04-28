
package Users;

import java.util.ArrayList;

public interface IUser <T>{
    public void add(String first, String last, String Phone, String Email, String AddCity, String State,
            String addressone, String addresstwo, String street, String blockNumber) ;
    public boolean del(String id);
    public void modify(String id, String New, String Type);
    public T search(String id);
    public ArrayList<T> list();
}
