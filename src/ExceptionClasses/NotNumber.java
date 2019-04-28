
package ExceptionClasses;

import javax.swing.JOptionPane;

public class NotNumber extends Exception {
    public NotNumber()
    {
        super();
        JOptionPane.showMessageDialog(null, "You entered words in number filed", "Alert", JOptionPane.WARNING_MESSAGE);
    }
}
