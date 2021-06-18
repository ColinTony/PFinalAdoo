package pfinaladoo.modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pfinaladoo.controladores.DBController;
import pfinaladoo.controladores.SecurityController;

/**
 *
 * @author colin
 */
public class ManejoDB 
{
    private boolean rpta = false;
    private ResultSet rusultSet;
    private PreparedStatement preparedSta1;
    private PreparedStatement preparedSta2;
    private final DBController CONTROLLER;
    
    
    public ManejoDB() throws SQLException, ClassNotFoundException
    {
        this.CONTROLLER = new DBController();
    }
    
    public boolean elimUsuarios(Usuario adm,boolean isADM) throws SQLException
    {
        this.rpta = false;
        Connection con = DBController.getConexion();
        if(isADM) // borramos adms o empleado
            this.preparedSta1 = con.prepareCall("call elimAdm(?)");
        else
            this.preparedSta1 = con.prepareCall("call elimEmp(?)");
        
        this.preparedSta1.setInt(1, Integer.parseInt(adm.getIdUsuario()));
        
        this.rpta = !this.preparedSta1.execute();
        return this.rpta;
    }

     // obtener informacion con ID
     public synchronized Usuario getInfoUser(int id,boolean isADM) throws SQLException
     {
         this.rusultSet = null;
         System.out.println(id);
         Connection con = DBController.getConexion();
         Usuario user = new Usuario();
         
         if(isADM)
             this.preparedSta1 = con.prepareStatement("select * from adms where adms.idAdm = ?;");
         else
              this.preparedSta1 = con.prepareStatement("select * from empleados where empleados.idEmp = ?;");
         
         this.preparedSta1.setInt(1, id);
         this.rusultSet = this.preparedSta1.executeQuery();
         
         if(rusultSet.next())
         {
             if(isADM)
                 user.setIdUsuario(String.valueOf(this.rusultSet.getString("idAdm")));
             else
                 user.setIdUsuario(String.valueOf(this.rusultSet.getString("idEmp")));
             
             user.setNombre(this.rusultSet.getString("nombre"));
             user.setApeP(this.rusultSet.getString("apeP"));
             user.setApeM(this.rusultSet.getString("apeM"));
             user.setPass(this.rusultSet.getString("pass"));
             user.setEmail(this.rusultSet.getString("email"));
             user.setTel(this.rusultSet.getString("numero"));
             
         }
         return user;
     }
     /*
        Modificar / Alta de usuario
     */
     public synchronized boolean addModUsers(Usuario usuario,boolean isMod,boolean isADM) throws SQLException
     {
         this.rusultSet = null;
         this.rpta = false;
         Connection con = DBController.getConexion();
         if(isMod)
         {
             // es modificacion
             if(isADM)// es adms
                 this.preparedSta1 = con.prepareCall("call modAdm(?,?,?,?,?,?,?)");
             else // mod empleado
                 this.preparedSta1 = con.prepareCall("call modEmp(?,?,?,?,?,?,?)");
             
             this.preparedSta1.setString(1, usuario.getNombre());
             this.preparedSta1.setString(2, usuario.getApeP());
             this.preparedSta1.setString(3, usuario.getApeM());
             this.preparedSta1.setString(4, usuario.getTel());
             this.preparedSta1.setString(5, usuario.getEmail());
             this.preparedSta1.setString(6, usuario.getPass());
             this.preparedSta1.setString(7, usuario.getIdUsuario());
             this.rpta = !this.preparedSta1.execute();
             
         }else
         {
             // es alta
             if(isADM)
                 this.preparedSta1 = con.prepareCall("call nuevoADM(?,?,?,?,?,?)");
             else
                 this.preparedSta1 = con.prepareCall("call nuevoEmp(?,?,?,?,?,?)");
             
             // llenamos el pStatment
             this.preparedSta1.setString(1, usuario.getNombre());
             this.preparedSta1.setString(2, usuario.getApeP());
             this.preparedSta1.setString(3, usuario.getApeM());
             this.preparedSta1.setString(4, usuario.getTel());
             this.preparedSta1.setString(5, usuario.getEmail());
             this.preparedSta1.setString(6, usuario.getPass());
             this.rpta = !this.preparedSta1.execute();
         }
        return this.rpta;
     }
     /*
         Ver Adminstradores de la DB
     */
     public synchronized ArrayList<Usuario> verUsuarios(boolean isAdms) throws SQLException
     {
        ArrayList<Usuario> users = new ArrayList<>();
        
        this.rusultSet = null;
        Connection con = DBController.getConexion();
        
        if(isAdms)
            this.preparedSta1 = con.prepareCall("call verAdms()");
        else
            this.preparedSta1 = con.prepareCall("call verEmp()");
        
        this.rusultSet = this.preparedSta1.executeQuery();
        
        while(this.rusultSet.next())
        {
            Usuario user = new Usuario();
            if(isAdms)
                user.setIdUsuario(String.valueOf(this.rusultSet.getInt("idAdm")));
            else
                user.setIdUsuario(String.valueOf(this.rusultSet.getInt("idEmp")));
            
            user.setEmail(this.rusultSet.getNString("email"));
            user.setNombre(this.rusultSet.getNString("nombre"));
            user.setTel(this.rusultSet.getNString("numero"));
            users.add(user);
        }
        return users;
     }


     // CRUD INVENTARIO
     
     /*
         Eliminar ADM de DB
     */
     public synchronized boolean eliminarProducto(Producto producto) throws SQLException
     {
         this.rpta = false;
         Connection con = DBController.getConexion();
         this.preparedSta1 = con.prepareStatement("delete from productos where productos.idProd=?");
         this.preparedSta1.setInt(1, Integer.parseInt(producto.getIdProducto()));
         this.rpta = !this.preparedSta1.execute();
         
        return this.rpta;
     }
     /*
         Modificar ADM en DB
     */
     public synchronized boolean modificarAddProducto(Producto producto,boolean isMod) throws SQLException
     {
        this.rpta = false;
        Connection con = DBController.getConexion();
        if(isMod)
        {
            this.preparedSta1 = con.prepareCall("call modProd(?,?,?,?,?)");
            this.preparedSta1.setInt(1, Integer.parseInt(producto.getIdProducto()));
            this.preparedSta1.setInt(2, Integer.parseInt(producto.getIdTipo()));
            this.preparedSta1.setString(3, producto.getNombre());
            this.preparedSta1.setFloat(4, Float.parseFloat(producto.getPrecio()));
            this.preparedSta1.setInt(5, Integer.parseInt(producto.getDisponibilidad()));
            
            
            this.rpta = !this.preparedSta1.execute();
        }
        else
        {
            this.preparedSta1 = con.prepareCall("call nuevoProducto(?,?,?,?)");
            this.preparedSta1.setInt(1, Integer.parseInt(producto.getIdTipo()));
            this.preparedSta1.setString(2, producto.getNombre());
            this.preparedSta1.setFloat(3, Float.parseFloat(producto.getPrecio()));
            this.preparedSta1.setInt(4, Integer.parseInt(producto.getDisponibilidad()));
            this.rpta = !this.preparedSta1.execute();
        }
        
        return this.rpta;
     }
     public synchronized Tipo getTipoByName(String name) throws SQLException
     {
         Tipo tipo = new Tipo();
         Connection con = DBController.getConexion();
         this.preparedSta1 = con.prepareStatement("select * from tipos where tipos.tipo = ?");
         this.preparedSta1.setString(1, name);
         this.rusultSet = this.preparedSta1.executeQuery();
         if(this.rusultSet.next())
         {
             tipo.setIdTipo(String.valueOf(this.rusultSet.getString("idTipo")));
             tipo.setTipo(this.rusultSet.getString("tipo"));
         }
         return tipo;
     }
     
     public synchronized Tipo getTipoByID(String id) throws SQLException
     {
         Tipo tipo = new Tipo();
         Connection con = DBController.getConexion();
         this.preparedSta1 = con.prepareStatement("select * from tipos where tipos.idTipo = ?");
         this.preparedSta1.setString(1, id);
         this.rusultSet = this.preparedSta1.executeQuery();
         if(this.rusultSet.next())
         {
             tipo.setIdTipo(String.valueOf(this.rusultSet.getString("idTipo")));
             tipo.setTipo(this.rusultSet.getString("tipo"));
         }
         return tipo;
     }
     /*
         Darme inventario
     */
     public synchronized ArrayList<Producto> verInventario(boolean isForType, String tipo) throws SQLException
     {
         ArrayList<Producto> productos = new ArrayList<>();
         this.rusultSet = null;
         Connection con = DBController.getConexion();
         if(isForType && !tipo.equals("")){
             this.preparedSta1 = con.prepareStatement("select * from productos where productos.idTipo = ?");
             this.preparedSta2 = con.prepareStatement("select * from tipos where tipos.tipo = ?");
             this.preparedSta2.setString(1, tipo);
             this.rusultSet = this.preparedSta2.executeQuery();
             if(this.rusultSet.next())
                 this.preparedSta1.setInt(1, this.rusultSet.getInt("idTipo"));
         }else
             this.preparedSta1 = con.prepareStatement("select * from productos;");
         
         this.rusultSet = this.preparedSta1.executeQuery();
         while(this.rusultSet.next())
         {
             Producto prod = new Producto();
             prod.setIdProducto(String.valueOf(this.rusultSet.getString("idProd")));
             prod.setIdTipo(String.valueOf(this.rusultSet.getString("idTipo")));
             prod.setNombre(this.rusultSet.getString("nombre"));
             prod.setPrecio(String.valueOf(this.rusultSet.getFloat("precio")));
             prod.setDisponibilidad(String.valueOf(this.rusultSet.getInt("disponibilidad")));
             productos.add(prod);
         }
         return productos;
     }
     public synchronized ArrayList<Tipo> getTiposDB() throws SQLException
     {
         ArrayList<Tipo> tipos = new ArrayList<>();
         this.rusultSet = null;
         Connection con = DBController.getConexion();
         
         this.preparedSta1 = con.prepareStatement("select * from tipos;");
         this.rusultSet = this.preparedSta1.executeQuery();
         
         while(this.rusultSet.next())
         {
             Tipo tipo = new Tipo(this.rusultSet.getString("idTipo"), this.rusultSet.getString("tipo"));
             tipos.add(tipo);
         }
         return tipos;
     }


     // Devolvera un resulset con valores del usuario iniciado sesion
     public synchronized ResultSet login(Usuario user,boolean isAdm) throws SQLException
     {
        this.rusultSet = null;
        Connection conection = DBController.getConexion();
        if(isAdm)
        {
            this.preparedSta1 = conection.prepareCall("call loginADM(?,?)");
            this.preparedSta2 = conection.prepareStatement("select * from adms where adms.email = ?");
        }
        else{
            this.preparedSta1 = conection.prepareCall("call loginEmpleado(?,?)");
            this.preparedSta2 = conection.prepareStatement("select * from empleados where empleados.email=?");
        }
            
        // llenamos las llamadas
        this.preparedSta2.setString(1, user.getEmail());
        this.rusultSet = this.preparedSta2.executeQuery();
        if(this.rusultSet.next())
        {
            this.preparedSta1.setString(1, user.getEmail());
            this.preparedSta1.setString(2, this.rusultSet.getString("pass"));
            System.out.println(user.getEmail());
            System.out.println(user.getPass());
            System.out.println(isAdm);
            this.rusultSet = this.preparedSta1.executeQuery();
        }else{
            return this.rusultSet;
        }
        return this.rusultSet;
     }

     public synchronized boolean nuevoTipo(String tipo) throws SQLException
     {
         // Nota se pueden agregar los mismos tipos
         this.rpta = false;
         Connection con = DBController.getConexion();
         this.preparedSta1 = con.prepareCall("call nuevoTipo(?)");
         this.preparedSta1.setString(1, tipo);
         this.rpta = !this.preparedSta1.execute();
         return this.rpta;
     }
     /*
         Procesar Compra
         Regresa true si se realizo correctamente
     */
     public synchronized boolean procesarCompra(Venta venta)
     {
         return true;
     }

}