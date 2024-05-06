package ExamenSemana9package.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ExamenSemana9package.model.Usuario;
import ExamenSemana9package.service.UsuarioService;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @GetMapping
    public CollectionModel<EntityModel<Usuario>> getAllUsuarios()
    {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        List<EntityModel<Usuario>> usuarioResources = usuarios.stream().map(usuario-> EntityModel.of(usuario,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioByid(usuario.getId())).withSelfRel()))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios());
        CollectionModel<EntityModel<Usuario>> resources = CollectionModel.of(usuarioResources, linkTo.withRel("usuarios"));
        return resources;
    }
    
    //Get de Usuario por ID basicamente valida si existe, si esta se lo trae si no manda error
    @GetMapping("/{id}")
    public EntityModel<Usuario> getUsuarioByid(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);

        if (usuario.isPresent()) {
            return EntityModel.of(usuario.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioByid(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios()).withRel("all-usuarios"));
        } else {
            throw new UsuarioException("No se encuentra: " + id);
        }
    }
    //Create de usuario, valida las credenciales si no manda error, si el usuario ya esta lo valida con correo y no lo genera.
    @PostMapping ("/create")
    public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario,BindingResult result){

        List<Usuario> listaUsuarios = usuarioService.getAllUsuarios();
        for(Usuario u : listaUsuarios){
            if(usuario.getCorreo().equals(u.getCorreo())){
                StringBuilder errorMessage = new StringBuilder("Ya existe un usuario con ese correo: ");
                result.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("; "));
                ResponseMessage errorResponse = new ResponseMessage(errorMessage.toString());
                EntityModel<ResponseMessage> entityModel = EntityModel.of(errorResponse);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(entityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).createUsuario(usuario, result)).withSelfRel()));
            }
        }

        Usuario createdUsuario = usuarioService.createUsuario(usuario);
        EntityModel<Usuario> entityModel = EntityModel.of(createdUsuario,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioByid(createdUsuario.getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios()).withRel("all-usuarios"));
        return ResponseEntity.status(HttpStatus.CREATED).body(entityModel);
    }
    //Update de Usuario por ID, genera Log de error si falla la validacion
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUsuario(@PathVariable Long id, @Valid @RequestBody Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder stringError = new StringBuilder("Error: ");
            result.getAllErrors().forEach(error -> stringError.append(error.getDefaultMessage()).append("; "));
            ResponseMessage errorResponse = new ResponseMessage(stringError.toString());
            EntityModel<ResponseMessage> entityModel = EntityModel.of(errorResponse);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(entityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).updateUsuario(id, usuario, result)).withSelfRel()));
        }
        else
        {
        Usuario usuarioActualizado = usuarioService.updateUsuario(id, usuario);
        EntityModel<Usuario> entityModel = EntityModel.of(usuarioActualizado,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioByid(usuarioActualizado.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios()).withRel("all-usuarios"));
        return ResponseEntity.ok(entityModel);
        }
    }

    //Delete de usuario por ID
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    } 

    //Clase de mensages para las respuestas de error
    class ResponseMessage {
        private String message;
    
        public ResponseMessage(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
    }
}


