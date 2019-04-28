
package Users;

import ExceptionClasses.NotNumber;
import ExceptionClasses.NotString;
import java.io.Serializable;

public class AddressDatabase implements Serializable {
    
    private String streetName;
    private String cityName;
    private String stateName;
    private String addressOne;
    private String addressTwo;
    private String blockNumber;
    
    public AddressDatabase()
    {
        streetName = new String();
        cityName = new String();
        stateName = new String();
        addressOne = new String();
        addressTwo = new String();
        blockNumber = new String();
    }
    
    public void setAddressone(String addressOne) {
        this.addressOne = addressOne;
    }
    public void setAddresstwo(String addressTwo) {
        this.addressTwo = addressTwo;
    }
    
    public void setStreetnumber (String streetName) throws NotNumber {
        for (int i = 0; i < streetName.length(); i++) {
            if (streetName.charAt(i) < 48 || streetName.charAt(i) > 57) {
                throw (new NotNumber());
            }
        }
        this.streetName = streetName;
    }
    
    public void setCityname (String cityName) throws NotString {
        for (int i = 0; i < cityName.length(); i++) {
            if (cityName.charAt(i) >= 48 && cityName.charAt(i) <= 57) {
                throw (new NotString());
            }
        }
        this.cityName = cityName;
    }
    
    public void setBlockNumber (String blockNumber) throws NotNumber {
        for (int i = 0; i < blockNumber.length(); i++) {
            if (blockNumber.charAt(i) < 48 || blockNumber.charAt(i) > 57) {
                throw (new NotNumber());
            }
        }
        this.blockNumber = blockNumber;
    }
    
    public void setStatename (String stateName) throws NotString {
        for (int i = 0; i < stateName.length(); i++) {
            if (stateName.charAt(i) >= 48 && stateName.charAt(i) <= 57) {
                throw (new NotString());
            }
        }
        this.stateName = stateName;
    }
    
    public String getAddressone() {
        return addressOne;
    }
    public String getAddresstwo() {
        return addressTwo;
    }
    public String getStreetnumber () {
        return streetName;
    }
    public String getCityname () {
        return cityName;
    }
    public String getBlockNumber () {
        return blockNumber;
    }
    public String getStatename () {
        return stateName;
    }
    
    public void addAddress()
    {}
    public void deleteUser()
    {}
    public void modifyAddress()
    {}
    public String searchAddress()
    {return null;}
    public String listAllAddresses()
    {return null;}
}
