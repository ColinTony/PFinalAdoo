
package pfinaladoo.controladores;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author colin
 */
public class SecurityController {
    
    public String salt;
    
    
    public static String hashPass(String txtPass)
    {
        String passHash;
        passHash = BCrypt.hashpw(txtPass, BCrypt.gensalt(8));
        return passHash;
    }
    
    public static boolean checkPass(String passUser, String passDB)
    {
        boolean ok = false;
        
        if(BCrypt.checkpw(passUser, passDB))
            ok = true;
        
        return ok;
    }
    
    
}
