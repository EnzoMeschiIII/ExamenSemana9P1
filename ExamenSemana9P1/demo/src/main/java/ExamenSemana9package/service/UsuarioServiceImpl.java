package ExamenSemana9package.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ExamenSemana9package.model.Usuario;
import ExamenSemana9package.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

//Implementacion del servicio, se usa para definicion de los metodos CRUD
// Incluye getAll, get por ID, create, update, delete, y getUsuario segun correo y password
@Service
public class UsuarioServiceImpl implements UsuarioService{
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }
    
    @Override
    public Usuario createUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario updateUsuario(Long id, Usuario usuario){
        if(usuarioRepository.existsById(id)){
            usuario.setId(id);
            return usuarioRepository.save(usuario);
        }
        else{
            return null;
        }

    }

    @Override
    public void deleteUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

}
