package ExamenSemana9package.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Se genera Usuario Exception para la traza de errores del CRUD
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioException extends RuntimeException{

    public UsuarioException(String excepcion) {
        super(excepcion);
    }

    
}
