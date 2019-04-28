
package Orders;

import java.util.Date;

public interface IOrder {
    void MakeOrder(int x,int y,Date date);
    boolean CancelOrder(Cancel cancel, int amount);
}
