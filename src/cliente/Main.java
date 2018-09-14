package cliente;

import cliente.gui.UsuarioWindow;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) throws Exception {
        
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        
        SwingUtilities.invokeLater(() -> {
            UsuarioWindow uw = new UsuarioWindow();
            uw.setLocationRelativeTo(null);
            uw.setVisible(true);
        });
    }
    
}
