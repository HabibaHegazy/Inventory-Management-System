
package Orders;

import java.io.Serializable;

public class Sale implements IPrize, Serializable{
    private int productSlaePrize;
    private int saleNo;
    private int saleTax;
    
    void calcFullSale(int x,int y)
    {}
    @Override
    public void enterPrice(int p)
    {}
    @Override
    public void modifyPrice(String s)
    {}
}
