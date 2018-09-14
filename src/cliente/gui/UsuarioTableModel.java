package cliente.gui;

import cliente.pojo.Usuario;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class UsuarioTableModel extends DefaultTableModel {

    private final String[] NOMBRES_COLUMNAS = {"ID", "Nombre", "Apellidos", "Email", "Contraseña"};
    private List<Usuario> usuarios;

    public UsuarioTableModel(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public int getColumnCount() {
        return NOMBRES_COLUMNAS.length;
    }

    @Override
    public int getRowCount() {
        if (this.usuarios == null) {
            return 0;
        } else {
            return usuarios.size();
        }
    }

    @Override
    public String getColumnName(int col) {
        return NOMBRES_COLUMNAS[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Usuario usuario = this.usuarios.get(row);
        switch (col) {
            case 0:
                return usuario.getId();
            case 1:
                return usuario.getNombre();
            case 2:
                return usuario.getApellidos();
            case 3:
                return usuario.getEmail();
            case 4:
                return usuario.getPassword();
            default:
                return null;
        }
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

}
