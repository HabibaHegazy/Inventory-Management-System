
package Users;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ILogin {
    public boolean LoginLn(String user,String pass);
    public void inputAccounts();
    public void removeAccounts();
    public String searchAccount();
    public void saveData()throws FileNotFoundException, IOException;
    public void getData() throws FileNotFoundException, IOException, ClassNotFoundException;
    public boolean forgetPassword(String user,String pass,String newPass);
}
