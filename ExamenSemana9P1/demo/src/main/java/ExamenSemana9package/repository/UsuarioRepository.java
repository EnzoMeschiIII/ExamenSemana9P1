package ExamenSemana9package.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ExamenSemana9package.model.Usuario;

//Sirve de respositorio, no tiene mucha ciencia
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
