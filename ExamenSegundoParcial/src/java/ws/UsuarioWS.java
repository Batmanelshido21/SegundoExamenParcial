/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojos.Usuario;

/**
 * REST Web Service
 *
 * @author javie
 */
@Path("usuarios")
public class UsuarioWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsuarioWS
     */
    public UsuarioWS() {
    }

    /**
     * Retrieves representation of an instance of ws.UsuarioWS
     * @return an instance of java.lang.String
     */ 
    @Path("allbd")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getAllBd() {
        List<Usuario> list = null;
        SqlSession conexion = MyBatisUtil.getSession();
        if(conexion != null){
            try{
                list = conexion.selectList("Usuario.getAllUsuarios");
            }catch(Exception ex){
               ex.printStackTrace();
            }finally{
                String j = conexion.toString();
                conexion.close();
            }
        }
        return list;
    }
    
    @POST
    @Path("registro")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario registroUsuario(
            @FormParam("nombre") String nombre,
            @FormParam("apellidoP") String apellidoP,
            @FormParam("apellidoM") String apellidoM,
            @FormParam("numeroCelular") String numeroCelular,
            @FormParam("fechaNacimiento") Date fechaNacimiento,
            @FormParam("password") String password){
        
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellidoP(apellidoP);
        usuario.setApellidoM(apellidoM);
        usuario.setNumeroCelular(numeroCelular);
        usuario.setFechaNacimiento(fechaNacimiento);
        usuario.setPassword(password);
        SqlSession conexion = MyBatisUtil.getSession();
        
         if(conexion != null){
            try{
                conexion.insert("Usuario.registrarUsuario",usuario);
                conexion.commit();
            }finally{
                String j = conexion.toString();
                conexion.close();
            }
        }
        return usuario;
        
    }
    
    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario loginUsuario(
            @FormParam("numeroCelular") String numeroCelular,
            @FormParam("password") String password) {
        Usuario usuario = new Usuario();
        usuario.setNumeroCelular(numeroCelular);
        usuario.setPassword(password);
        SqlSession conexion = MyBatisUtil.getSession();
        if(conexion != null){
            try{
                usuario = conexion.selectOne("Usuario.loginUsuario",usuario);
            }catch(Exception ex){
               ex.printStackTrace();
            }finally{
                String j = conexion.toString();
                conexion.close();
            }
        }
        return usuario;
    }
}
