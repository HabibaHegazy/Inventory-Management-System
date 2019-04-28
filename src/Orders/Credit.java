
package Orders;

import java.util.Date;

public class Credit implements  Iauthorized{
    private String creditNO;
    private String creditType;
    private Date expDate;

    @Override
    public void authorized() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
