
package Orders;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

public class CurrencyConverter implements Serializable{
    public String[] currencyName = new String[53];
    HashMap<String,Double> currency = new HashMap<>();
    
    public void getCurrencyDatabase() throws MalformedURLException, IOException {
        URL url = new URL("http://www.x-rates.com/historical/?from=USD&amount=1&date=2017-12-15");
        Scanner scan = new Scanner(url.openStream());
        int lineCounter = 0;
        int number = 242;
        int number2 = 243;
        int ctr = 0;
        String omla = null,text;
        double qema;
        
        while(scan.hasNext()) {
            String Line = scan.nextLine();
            if (lineCounter < 507 && lineCounter > number) {
                omla = Line.substring(Line.indexOf("<td>")+4, Line.indexOf("</td>"));
                //System.out.println(omla);
                currencyName[ctr] = omla;
                ctr++;
                number+=5;
            }
           if (lineCounter < 507 && lineCounter > number2) {
               text = Line.substring(Line.indexOf("<td class='rtRates'><a href='/graph/?from=USD&amp;to=")+53, Line.indexOf("</a></td>"));
               qema = Double.parseDouble((text.substring(5, 11)));
               //System.out.println(qema);
               number2+=5;
               currency.put(omla,qema);
            }
           lineCounter++;
        }
    }
    
    public double calculateCurrency(double number,String currencyy) {
        double newNumber = number*(currency.get(currencyy));
        return newNumber;
    }
}
