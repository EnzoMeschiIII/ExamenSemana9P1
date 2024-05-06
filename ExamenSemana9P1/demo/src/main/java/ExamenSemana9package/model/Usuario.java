package ExamenSemana9package.model;

import org.springframework.hateoas.RepresentationModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

//Se Modifico el modelo de Usuario con productos ya que se daba muchas vueltas
//Se dejo solo usuario para alcanzar a codificar todo, se agregaron Null not Allowed para valores nulos
@Entity
@Table(name = "Usuario")
public class Usuario extends RepresentationModel<Usuario>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nombre")
    @NotBlank(message = "Null not allowed")
    private String nombre;
    @Column(name = "password")
    @NotBlank(message = "Null not allowed")
    private String password;
    @Column(name = "correo")
    @NotBlank(message = "Null not allowed")
    private String correo;
    @Column(name = "rol")
    @NotBlank(message = "Null not allowed")
    private String rol;
    @NotBlank(message = "Null not allowed")
    @Column(name = "direccion")
    private String direccion;

    
    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo(){
        return correo;
    }

    public void setCorreo(String correo){
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


}
