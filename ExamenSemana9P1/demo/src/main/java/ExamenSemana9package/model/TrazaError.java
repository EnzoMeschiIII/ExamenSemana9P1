package ExamenSemana9package.model;

//La clase trazaError basicmente sirve para guardar los errores por Log
public class TrazaError {
    
    private String error;
    
    public TrazaError(String error) {
        this.error = error;
    }
    
     // Getters y setters
    public String getError() {
        return error;
    }
    
     public void setError(String error) {
        this.error = error;
    }
    
}
