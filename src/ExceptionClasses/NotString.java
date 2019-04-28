
package ExceptionClasses;

import javax.swing.JOptionPane;

public class NotString extends Exception{
    public NotString()
    {
        super();
        JOptionPane.showMessageDialog(null, "You entered something else than words (number for example).", "Alert", JOptionPane.WARNING_MESSAGE);
    }
}
