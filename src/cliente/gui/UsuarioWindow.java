package cliente.gui;

import cliente.datos.ApiException;
import cliente.datos.ApiUsuario;
import cliente.datos.HttpException;
import cliente.pojo.Usuario;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class UsuarioWindow extends javax.swing.JFrame {

    private ApiUsuario apiUsuario;
    
    public UsuarioWindow() {
        initComponents();
        this.apiUsuario = new ApiUsuario(this.server.getText());
        //cargarUsuarios();
        this.getContentPane().requestFocusInWindow();
    }
    
    private void cargarUsuarios() {
        try {
            this.apiUsuario.setServerUrl(this.server.getText());
            List<Usuario> usuarios = this.apiUsuario.getAllUsuarios();
            this.table.setModel(new UsuarioTableModel(usuarios));
        } catch (ApiException ex) {
            this.table.setModel(new UsuarioTableModel(null));
            showErrorMessage(ex);
        }
    }
    
    private void showErrorMessage(ApiException exception) {
        String errorText = "";
        Throwable cause = exception.getCause();
        if (cause instanceof UnknownHostException || cause instanceof ConnectException || cause instanceof SocketTimeoutException) {
            errorText = "Error: No es posible conectar al servidor";
        } else if (exception.getCause() instanceof HttpException) {
            errorText = "Error: Código de respuesta HTTP " + cause.getMessage();
        } else {
            errorText = "Error: No es posible realizar la operación";
        }
        
        JOptionPane.showMessageDialog(this, errorText, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JLabel jLabel1 = new JLabel();
        server = new JTextField();
        JScrollPane jScrollPane1 = new JScrollPane();
        table = new JTable();
        addNew = new JButton();
        refresh = new JButton();
        jLabel2 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Servidor:");
        jLabel1.setToolTipText("");

        server.setText("http://localhost:5000/");

        table.setModel(new UsuarioTableModel(null));
        table.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(table);

        addNew.setText("Agregar nuevo");
        addNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addNewActionPerformed(evt);
            }
        });

        refresh.setText("Actualizar");
        refresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        jLabel2.setText("Usuarios:");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(server))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(refresh)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addNew))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(server, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(addNew)
                    .addComponent(refresh))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void refreshActionPerformed(ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        cargarUsuarios();
    }//GEN-LAST:event_refreshActionPerformed

    private void addNewActionPerformed(ActionEvent evt) {//GEN-FIRST:event_addNewActionPerformed
        AddNewUsuario jdialog = new AddNewUsuario();
        jdialog.setLocationRelativeTo(this);
        jdialog.setVisible(true);
        
        if (jdialog.savePressed()) {
            try {
                this.apiUsuario.addUsuario(jdialog.getInput());
            } catch (ApiException ex) {
                showErrorMessage(ex);
            }
        }
        cargarUsuarios();
    }//GEN-LAST:event_addNewActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton addNew;
    private JLabel jLabel2;
    private JButton refresh;
    private JTextField server;
    private JTable table;
    // End of variables declaration//GEN-END:variables
}
