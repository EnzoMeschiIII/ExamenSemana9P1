package ExamenSemana9package.service;

import java.util.List;
import java.util.Optional;

import ExamenSemana9package.model.Usuario;

//Servicio de usuario se agrego un getUsuario con correo y pass para validacion
public interface UsuarioService {
    List<Usuario> getAllUsuarios();
    Optional<Usuario> getUsuarioById(Long id);
    Usuario createUsuario(Usuario usuario);
    Usuario updateUsuario(Long id, Usuario usuario);
    void deleteUsuario(Long id);
    
}
