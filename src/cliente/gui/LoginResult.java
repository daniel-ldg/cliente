package cliente.gui;

import cliente.pojo.Usuario;

public class LoginResult {
    
    private String servidor;
    
    private Usuario usuario;

    public String getServidor() {
        return servidor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
