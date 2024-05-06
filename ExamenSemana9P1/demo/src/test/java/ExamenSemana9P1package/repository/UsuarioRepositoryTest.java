package ExamenSemana9P1package.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ExamenSemana9package.model.Usuario;
import ExamenSemana9package.repository.UsuarioRepository;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void nuevoUsuarioTest() {
        Usuario usuario = new Usuario();
        usuario.setNombre("testup");
        usuario.setPassword("testup");
        usuario.setCorreo("testup@testup.testup");
        usuario.setRol("testup");
        usuario.setDireccion("testup");

        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        assertNotNull(nuevoUsuario.getId());
        assertEquals("test", nuevoUsuario.getNombre());
    }
}
