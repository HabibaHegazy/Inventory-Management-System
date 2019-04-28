
package Orders;

import ExceptionClasses.NotNumber;
import ExceptionClasses.NotString;
import static inventorysytem.InventorySytem.cashiers;
import static inventorysytem.InventorySytem.products;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Product implements Serializable{
    private Date ExpDate;
    private Date MGDate;
    private String type;
    private String brand;
    private String productName;
    private int qty;
    private String barcode;
    private String haveWarrenty;
    private String parchuseNum;
    private String saleNum;
    private String parchusePrize;
    private String salePrize;
    
    public void setExpDate(Date ExpDate){
        this.ExpDate = ExpDate;
    }
    
    public Date getExpDate(){
        return ExpDate;
    }
    
    public void setMGDate(Date MGDate){
        this.MGDate = MGDate;
    }
    
    public Date getMGDate(){
        return MGDate;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public String getTtpe(){
        return type;
    }
    
    public void setBrand(String brand) throws NotString{
        for (int i = 0; i < brand.length(); i++) {
            if (brand.charAt(i) >= 48 && brand.charAt(i) <= 57) {
                throw (new NotString());
            }
        }
        this.brand = brand;
    }
    
    public String getBrand(){
        return brand;
    }
    
    public void setproductName(String productName) throws NotString{
        for (int i = 0; i < productName.length(); i++) {
            if (productName.charAt(i) >= 48 && productName.charAt(i) <= 57) {
                throw (new NotString());
            }
        }
        this.productName = productName;
    }
    
    public String GetProductname(){
        return productName;
    }
    
    public void setqty(int qty){
        this.qty = qty;
    }
    
    public int getqty(){
        return qty;
    }
    
    public void setBracode(String barcode) throws NotNumber{ 
        for (int i = 0; i < barcode.length(); i++) {
            if (barcode.charAt(i) < 48 || barcode.charAt(i) > 57) {
                throw (new NotNumber());
            }
        }
        this.barcode = barcode;
    }
    
     public String GetBracode(){
        return barcode;
    }
    
    public void setWarr(String haveWarrenty){
        this.haveWarrenty = haveWarrenty;
    }
    
     public String GetWarr(){
        return haveWarrenty;
    }
    
    public void setparchuseNum(String parchuseNum) throws NotNumber{
        for (int i = 0; i < parchuseNum.length(); i++) {
            if (parchuseNum.charAt(i) < 48 || parchuseNum.charAt(i) > 57) {
                throw (new NotNumber());
            }
        }
        this.parchuseNum = parchuseNum;
    }
    
    public String GetparchuseNum(){
    return parchuseNum;
    }
    
    public void setsaleNum(String saleNum) throws NotNumber{
        for (int i = 0; i < saleNum.length(); i++) {
            if (saleNum.charAt(i) < 48 || saleNum.charAt(i) > 57) {
                throw (new NotNumber());
            }
        }
        this.saleNum = saleNum;
    }
    
    public String GetsaleNum(){
    return saleNum;
    }
    
    public void setsalePrize(String salePrize) throws NotNumber{
        for (int i = 0; i < salePrize.length(); i++) {
            if (salePrize.charAt(i) < 48 || salePrize.charAt(i) > 57) {
                throw (new NotNumber());
            }
        }
        this.salePrize = salePrize;
    }
    
    public String GetsalePrize(){
    return salePrize;
    }
    
    public void setparchusePrize(String parchusePrize) throws NotNumber{
        for (int i = 0; i < parchusePrize.length(); i++) {
            if (parchusePrize.charAt(i) < 48 || parchusePrize.charAt(i) > 57) {
                throw (new NotNumber());
            }
        this.parchusePrize = parchusePrize;
        }
    }
    
    public String GetparchusePrize(){
    return parchusePrize;
    }
    
    public void addProduct(Date expdate, Date mgdate, String type,String brand,String productname,int quantity,String barcodes,String warrenty,
            String parchPrize, String saleprize,String parchNum, String salenum, Purchase purchase, Sale sale, Warranty war) {
        Product prod = new Product();
        try {  
        prod.setExpDate(expdate);
        prod.setMGDate(mgdate);
        prod.setExpDate(expdate);
        prod.setMGDate(mgdate);
        prod.setWarr(warrenty);
        prod.setproductName(productname);
        prod.setqty(quantity);
        prod.setBracode(barcodes);
        prod.setparchuseNum(parchNum);
        prod.setparchusePrize(parchPrize);
        prod.setsaleNum(salenum);
        prod.setsalePrize(saleprize);
        } catch (NotString | NotNumber e) {
            return;
        }
       
           products.add(prod);
    }
    
    public boolean deleteProduct(String productname) {
        Product worker = search(productname);
       return products.remove(worker);    
    }
    
    public Product search(String productname) {
        Product c = new Product();
        for (Product Ca : products)
        {
            if (Ca.GetProductname().equals(productname))
            {
                c = Ca;
            }
        }
        return c;
    }
    
    public ArrayList list() {
        return products;
    }
}
