
package Users;
 
import ExceptionClasses.NotNumber;
import ExceptionClasses.NotString;
import java.io.Serializable;

public class Contact implements Serializable{
    private String phoneNumber= new String();
    private String Email = new String();
    private String FaxNo = new String();
    private String FristName = new String();
    private String LastName = new String();
    
    public Contact ()
    {
        phoneNumber= new String();
        Email = new String();
        FaxNo = new String();
        FristName = new String();
        LastName = new String();
    }
    
    public void setFirstname(String firstname) throws NotString
    {
        for (int i = 0; i < firstname.length(); i++) {
            if (firstname.charAt(i) >= 48 && firstname.charAt(i) <= 57) {
                throw (new NotString());
            }
        }
        FristName = firstname;
    }
    
    public void setLastname(String lastname) throws NotString
    {
        for (int i = 0; i < lastname.length(); i++) {
            if (lastname.charAt(i) >= 48 && lastname.charAt(i) <= 57) {
                throw (new NotString());
            }
        }
        LastName = lastname;
    }
    
    public void setPhonenumber(String phonenumber)throws NotNumber
    {
        for (int i = 0; i < phonenumber.length(); i++) {
            if (phonenumber.charAt(i) < 48 || phonenumber.charAt(i) > 57) {
                throw (new NotNumber());
            }
        }
        phoneNumber = phonenumber;
    }
    
    public void setEmail(String email)
    {
        Email = email;
    }
    
    public void setFaxNumber(String faxno) throws NotNumber
    {
        for (int i = 0; i < faxno.length(); i++) {
            if (faxno.charAt(i) < 48 || faxno.charAt(i) > 57) {
                throw (new NotNumber());
            }
        }
        FaxNo = faxno;
    }
    
    public String getFirstname()
    {
        return FristName;
    }
    
    public String getLastname()
    {
        return LastName;
    }
    
    public String getPhonenumber()
    {
        return phoneNumber;
    }
    
    public String getEmail()
    {
        return Email;
    }
    
    public String getFaxNumber()
    {
        return FaxNo;
    }
    
    public void addContact()
    {}
    public void deleteContact()
    {}
    public void modifyContact()
    {}
    public String searchContact()
    {return null;}
    public String listAllContacts()
    {return null;}
        
    
    
}
