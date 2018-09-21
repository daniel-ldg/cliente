package cliente.hilos;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class Writer {
    
    JList<String> jList;

    public Writer(JList<String> jList) {
        this.jList = jList;
    }
    
    public void write(String msg) {
        DefaultListModel dlm = (DefaultListModel) this.jList.getModel();
        dlm.addElement(msg);
    }

}
