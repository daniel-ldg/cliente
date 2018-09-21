package cliente;

import cliente.gui.Login;
import cliente.gui.LoginResult;
import cliente.gui.UsuarioWindow;
import cliente.gui.UsuarioWindowResult;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) throws Exception {

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        boolean startApp = true;

        while (startApp) {

            LoginResult loginResult = new LoginResult();

            SwingUtilities.invokeAndWait(() -> {
                Login login = new Login();
                login.setLocationRelativeTo(null);
                login.setVisible(true);

                if (login.getUsuario() == null) {
                    System.exit(0);
                }

                loginResult.setServidor(login.getServidor());
                loginResult.setUsuario(login.getUsuario());

                System.out.println(login.getServidor());
            });

            JOptionPane.showConfirmDialog(
                    null,
                    "Bienvenido " + loginResult.getUsuario().getNombre() + " " + loginResult.getUsuario().getApellidos(),
                    "Inicio de sesión",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            UsuarioWindowResult uwr = new UsuarioWindowResult();

            SwingUtilities.invokeAndWait(() -> {
                UsuarioWindow uw = new UsuarioWindow(loginResult.getServidor());
                uw.setLocationRelativeTo(null);
                uw.setVisible(true);
                uwr.setStartAppAgain(uw.isStartAppAgain());
            });
            
            startApp = uwr.isStartAppAgain();
        }

        System.exit(0);
    }

}
