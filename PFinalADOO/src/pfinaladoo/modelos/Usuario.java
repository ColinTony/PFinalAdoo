package pfinaladoo.modelos;

/**
 *
 * @author colin
 */
public class Usuario 
{
    private String idUsuario;
    private String email;
    private String pass;
    private String tel;
    private String apeP;
    private String apeM;
    private String nombre;

    
    public Usuario()
    {
        
    }

    public Usuario(String email, String pass, String tel, String apeP, String apeM, String nombre) {
        this.email = email;
        this.pass = pass;
        this.tel = tel;
        this.apeP = apeP;
        this.apeM = apeM;
        this.nombre = nombre;
    }
    
    

    // Getters And Setters
    public String getApeP() {
        return apeP;
    }

    public void setApeP(String apeP) {
        this.apeP = apeP;
    }

    public String getApeM() {
        return apeM;
    }

    public void setApeM(String apeM) {
        this.apeM = apeM;
    }
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
