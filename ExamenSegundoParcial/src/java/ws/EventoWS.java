/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojos.Evento;
import pojos.Usuario;

/**
 * REST Web Service
 *
 * @author javie
 */
@Path("eventos")
public class EventoWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EventoWS
     */
    public EventoWS() {
    }
    
    @Path("allbd")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Evento> getAllBd() {
        List<Evento> list = null;
        SqlSession conexion = MyBatisUtil.getSession();
        if(conexion != null){
            try{
                list = conexion.selectList("Evento.getAllEventos");
            }catch(Exception ex){
               ex.printStackTrace();
            }finally{
                String j = conexion.toString();
                conexion.close();
            }
        }
        return list;
    }
    
    @Path("getEventoUsuario/{Usuario_idUsuario}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Evento> buscarUsuarioTelefono(
            @PathParam("Usuario_idUsuario") int Usuario_idUsuario) {
          List<Evento> list = null;
          SqlSession conexion = MyBatisUtil.getSession();
        
         if(conexion != null){
            try{
                list = conexion.selectList("Evento.getEventosUsuario",Usuario_idUsuario);
            }finally{
                String j = conexion.toString();
                conexion.close();
            }
        }
        return list;
    }
    
    @POST
    @Path("registroEvento")
    @Produces(MediaType.APPLICATION_JSON)
    public Evento registroEvento(
            @FormParam("descripcion") String descripcion,
            @FormParam("fechaInicio") Timestamp fechaInicio,
            @FormParam("fechaTermino") Timestamp fechaTermino,
            @FormParam("lugar") String lugar,
            @FormParam("Usuario_idUsuario") Integer Usuario_idUsuario){
        
        Evento evento = new Evento();
        evento.setDescripcion(descripcion);
        evento.setFechaInicio(fechaInicio);
        evento.setFechaTermino(fechaTermino);
        evento.setLugar(lugar);
        evento.setUsuario_idUsuario(Usuario_idUsuario);
        
        SqlSession conexion = MyBatisUtil.getSession();
        
         if(conexion != null){
            try{
                conexion.insert("Evento.registrarEvento",evento);
                conexion.commit();
            }finally{
                String j = conexion.toString();
                conexion.close();
            }
        }
        return evento;
        
    }
}
