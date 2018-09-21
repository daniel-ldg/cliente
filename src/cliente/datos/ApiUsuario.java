package cliente.datos;

import cliente.pojo.Usuario;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApiUsuario {
    
    private String serverUrl;
    private ObjectMapper mapper;

    public ApiUsuario(String serverUrl) {
        this.serverUrl = serverUrl;
        this.mapper = new ObjectMapper();
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }
    
    public Usuario getUsuarioById(int id) throws ApiException {
        try {
            String json = HttpClient.getRequestJson(serverUrl + "api/usuarios/" + id);
            
            return this.mapper.readValue(json, Usuario.class);
            
        } catch (IOException | HttpException ex) {
            throw new ApiException(ex);
        }
    }
        
    public List<Usuario> getAllUsuarios() throws ApiException {
        try {
            String json = HttpClient.getRequestJson(serverUrl + "api/usuarios");
            JsonNode jsonNode = this.mapper.readTree(json);
            List<Usuario> usuarios = new ArrayList<>();
            
            jsonNode.forEach((usuario) -> {
                try {
                    usuarios.add(this.mapper.readValue(usuario.toString(), Usuario.class));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            
            if (usuarios.isEmpty()) {
                throw new ApiException("No users found");
            }
            
            return usuarios;
            
        } catch (IOException ex) {
            throw new ApiException(ex);
        }
    }
    
    public Usuario addUsuario(Usuario usuario) throws ApiException {
        try {
            String json = this.mapper.writeValueAsString(usuario);
            
            String responseJson = HttpClient.postRequestJson(this.serverUrl + "api/usuarios", json);
            
            return this.mapper.readValue(responseJson, Usuario.class);
            
        } catch (IOException ex) {
            throw new ApiException(ex);
        }
    }
    
    public Usuario tryLogin(Usuario usuario) throws ApiException {
        try {
            String json = this.mapper.writeValueAsString(usuario);
            
            String responseJson = HttpClient.postRequestJson(this.serverUrl + "api/login", json);
            
            return this.mapper.readValue(responseJson, Usuario.class);
            
        } catch (HttpException ex) {
            if (ex.getMessage().equals("404")) {
                throw new AuthException(ex);
            } else {
                throw ex;
            }
        } catch (IOException ex) {
            throw new ApiException(ex);
        }
    }

}
