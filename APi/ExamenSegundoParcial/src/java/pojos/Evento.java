/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.sql.Timestamp;
import java.util.Date;


/**
 *
 * @author javie
 */
public class Evento {
    
    private String descripcion;
    
    private Timestamp fechaInicio;
    
    private Timestamp fechaTermino;
    
    private String Lugar;
    
    private Integer Usuario_idUsuario;
    
    private Integer idEvento;

    public Evento() {
    }

    public Evento(String descripcion, Timestamp fechaInicio, Timestamp fechaTermino, String Lugar, Integer Usuario_idUsuario, Integer idEvento) {
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.Lugar = Lugar;
        this.Usuario_idUsuario = Usuario_idUsuario;
        this.idEvento = idEvento;
    }



    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Timestamp getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Timestamp fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public String getLugar() {
        return Lugar;
    }

    public void setLugar(String Lugar) {
        this.Lugar = Lugar;
    }

    public Integer getUsuario_idUsuario() {
        return Usuario_idUsuario;
    }

    public void setUsuario_idUsuario(Integer Usuario_idUsuario) {
        this.Usuario_idUsuario = Usuario_idUsuario;
    }

    
}
