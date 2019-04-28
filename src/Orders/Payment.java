
package Orders;

public class Payment {
    private String PaymentType;
    private double BillAmount;
    private double moneyTaken;
    
    void makePayment(Cash cash)
    {}
    void makePayment(Check check)
    {}
    void makePayment(Credit credit)
    {}
    void converter(CurrencyConverter currencyCon)
    {}
}
