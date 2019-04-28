
package Orders;

import java.io.Serializable;
import java.util.Date;

public class Warranty implements Serializable{
    private Date startDate;
    private Date endDate;
    private int warrantyAmount;
        
    void addWarranty()
    {}
    String verificationStatus()
    { return ""; }
}
