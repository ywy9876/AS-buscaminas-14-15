/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author alexmorral
 */

@Entity
@Table(name="jugador")
public class Jugador extends UsuariRegistrat implements Serializable{
    private static final long serialVersionUID = 1L;
	
    @Column(nullable=false)
    private String email;
    
    @OneToOne
    private Partida idPartida;
    
    public Jugador() {
    	idPartida = null;
    }
    public Jugador(String n, String c, String u, String p) {
    	super(n,c,u,p);
    }
    
    @Override
    public boolean isUsuariJugador() {
       return true;
    }
    
    public void assignarPartida(Partida p){
        idPartida = p;
    }
    
    public boolean tePartida() {
    	return (idPartida!=null);
    }
    
    public Partida getPartida() {
    	return idPartida;
    }
    
    public String getEmail() {
    	return email;
    }
}
