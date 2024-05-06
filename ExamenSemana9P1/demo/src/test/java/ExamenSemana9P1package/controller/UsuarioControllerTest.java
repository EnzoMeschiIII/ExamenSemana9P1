package ExamenSemana9P1package.controller;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ExamenSemana9package.controller.UsuarioController;
import ExamenSemana9package.model.Usuario;
import ExamenSemana9package.service.UsuarioService;


@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioServiceMock;

    //Prueba de ingreso de usuarios 1 y 2
    @Test
    public void getAllTest() throws Exception {

        Usuario usuario1 = new Usuario();
        
        usuario1.setNombre("JuanTest");
        usuario1.setPassword("test");
        usuario1.setCorreo("test@test.test");
        usuario1.setRol("tester");
        usuario1.setDireccion("test");
        
        Usuario usuario2 = new Usuario();
        
        //Se define ID para el segundo
        usuario2.setNombre("JuanTest2");
        usuario2.setPassword("test2");
        usuario2.setCorreo("test2@test2.test2");
        usuario2.setRol("tester2");
        usuario2.setDireccion("test2");
        usuario2.setId(10L);
        // Se genera lista
        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);
        when (usuarioServiceMock.getAllUsuarios()).thenReturn (usuarios);

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuarioList", Matchers.hasSize(2)))
        .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuarioList[0].nombre", Matchers.is("pruebas")))
        .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuarioList[1].nombre", Matchers.is("pruebas2")));
        }
}