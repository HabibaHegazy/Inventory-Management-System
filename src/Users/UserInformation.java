package Users;

import java.io.Serializable;
import java.util.Random;

public abstract class UserInformation implements Serializable{
    public AddressDatabase address = new AddressDatabase();
    public Contact con = new Contact();
    private String ID;
    
    public UserInformation()
    {
        con = new Contact();
        address = new AddressDatabase();
    }
    
    public String GetID()
    {
        return ID;
    }
    
    public void generateID(String role, String Name) {
        role = role.toLowerCase();
        String theID = new String();
        
        switch(role.charAt(0)){
            case 'c':
                theID = "C";
                switch (role.charAt(1)) {
                    case 'a':
                        theID += "A";
                        break;
                    case 'u':
                        theID += "U";
                        break;
                    case 's':
                        theID += "S";
                        break;
                }
                break;
            case 'a':
                theID += "A";
                theID += "D";
                break;
            case 'o':
                theID += "O";
                theID += "W";
                break;
            case 's':
                theID += "S";
                theID += "U";
                break; 
            case 'd':
                theID += "D";
                theID += "M";
                break;
            case 'w':
                theID += "W";
                theID += "O";
                break;
        }
       
        //theID += Name.substring(0, 4);
        Random random = new Random();
        String iD = Integer.toString(1000 + random.nextInt(9000));
        
        theID += iD;
       
        ID = theID;
    }
}
